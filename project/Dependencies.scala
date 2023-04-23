import sbt._

object Dependencies {
  val sparkVersion = "3.3.1"
  val gcsConnectorVersion = "hadoop3-2.2.9"
  val googleCloudStorageVersion = "2.17.1"
  val scalaTestVersion = "3.2.15"
  val googleSecretsManagerVersion = "2.5.0"
  val pureConfigVersion = "0.17.1"
  //  googleBigQueryVersion greater that 2.18 requires jackson 2.14 which is not compatible with scala 2.13.3
  val googleBigQueryVersion = "2.18.2"
  val scalaMockVersion = "5.2.0"

  val sparkCore             = "org.apache.spark"           %% "spark-core"        % sparkVersion % Provided
  val sparkSql              = "org.apache.spark"            %% "spark-sql"        % sparkVersion % Provided

  val gcsConnector          = "com.google.cloud.bigdataoss" % "gcs-connector"     % gcsConnectorVersion
  val googleCloudStorage    = "com.google.cloud"            % "google-cloud-storage" % googleCloudStorageVersion
  val googleSecretsManager  = "com.google.cloud"            % "google-cloud-secretmanager" % googleSecretsManagerVersion
  val googleBigQuery        = "com.google.cloud"            % "google-cloud-bigquery" % googleBigQueryVersion

  val sparkBigQuery         = "com.google.cloud.spark"      %% "spark-bigquery-with-dependencies" % "0.29.0"
  val sparkMllib            = "org.apache.spark"            %% "spark-mllib"        % sparkVersion % Provided

  val sparkAvro             = "org.apache.spark"            %% "spark-avro"         % sparkVersion
  val hadoopAWS             = "org.apache.hadoop"           % "hadoop-aws"          % sparkVersion

  val pureConfig            = "com.github.pureconfig"       %% "pureconfig"         % pureConfigVersion

  val scalaTest              = "org.scalatest"              %% "scalatest"          % scalaTestVersion % Test
  val scalaTestFunSuite     = "org.scalatest"               %% "scalatest-funsuite" % scalaTestVersion % Test
  val scalaMock =             "org.scalamock"                %% "scalamock"        % scalaMockVersion % Test



}
//
//  gcs-connector-hadoop3-2.2.11.jar