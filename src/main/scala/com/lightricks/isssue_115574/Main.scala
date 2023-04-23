/*
 * Copyright (c) 2022 Lightricks. All rights reserved.
 */
package com.lightricks.isssue_115574

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{Metadata, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SaveMode, SparkSession}

object Main extends App {

  val env = args(0)
  val my_bucket = args(1)
  val my_table = args(2)
    val spark = env match {
      case "local" =>
        SparkSession
          .builder()
          .config("temporaryGcsBucket", my_bucket)
          .master("local")
          .appName("isssue_115574")
          .getOrCreate()
      case _ =>
        SparkSession
          .builder()
          .config("temporaryGcsBucket", my_bucket)
          .appName("isssue_115574")
          .getOrCreate()
    }

  // create DF with some data
  val someData = Seq(
    Row("""{"name":"name1", "age": 10 }""", "id1"),
    Row("""{"name":"name2", "age": 20 }""", "id2")
  )
  val schema = StructType(
    Seq(
      StructField("user_age", StringType, true),
      StructField("id", StringType, true)
    )
  )
  val someDF = spark.createDataFrame(spark.sparkContext.parallelize(someData), schema)
  // set metadata
  val dfJSON = someDF
    .withColumn("user_age_no_metadata", col("user_age"))
    .withMetadata("user_age", Metadata.fromJson("""{"sqlType":"JSON"}"""))

  dfJSON.show()

  // write to BigQuery
  dfJSON.write.format("bigquery")
    .mode(SaveMode.Append)
    .option("writeMethod", "indirect")
    .option("intermediateFormat", "avro")
    .option("useAvroLogicalTypes", "true")
    .option("table",my_table)
    .save()
}
