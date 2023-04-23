/*
 * Copyright (c) 2022 Lightricks. All rights reserved.
 */
import sbtassembly.AssemblyPlugin.autoImport.ShadeRule
import sbtrelease.ReleasePlugin.autoImport.ReleaseTransformations._

val sparkVersion = "3.3.1"
val gcsConnectorVersion = "hadoop3-2.2.9"
val googleCloudStorageVersion = "2.17.1"

val sparkCore             = "org.apache.spark"           %% "spark-core"        % sparkVersion % Provided
val sparkSql              = "org.apache.spark"            %% "spark-sql"        % sparkVersion % Provided
val gcsConnector          = "com.google.cloud.bigdataoss" % "gcs-connector"     % gcsConnectorVersion
val googleCloudStorage    = "com.google.cloud"            % "google-cloud-storage" % googleCloudStorageVersion
val sparkBigQuery         = "com.google.cloud.spark"      %% "spark-bigquery-with-dependencies" % "0.28.0"
val sparkAvro             = "org.apache.spark"            %% "spark-avro"         % sparkVersion


ThisBuild / resolvers ++= Seq(
  "confluent" at "https://packages.confluent.io/maven/"
)

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(BuildHelper.stdSettings)
  .settings(
    name := "braze-export",
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-library" % scalaVersion.value % Provided,
        sparkCore,
      sparkSql,
      gcsConnector,
      googleCloudStorage,
      sparkBigQuery,
      sparkAvro,
    )
  )
  .settings(
    Compile / run / mainClass := Option(
      "com.lightricks.isssue_115574.Main"
    )
  )

assembly / assemblyShadeRules := Seq(
  ShadeRule.rename("com.google.common.**" -> "repackaged.com.google.common.@1").inAll,
  ShadeRule.rename("com.google.protobuf.**" -> "repackaged.com.google.protobuf.@1").inAll
)
assembly / artifact := {
  val art = (assembly / artifact).value
  art.withClassifier(Some("assembly"))
}

assembly / assemblyMergeStrategy := {
  case "mime.types"                     => MergeStrategy.first
  case "mozilla/public-suffix-list.txt" => MergeStrategy.first
  // Java9 feature, safe to discard, as we aren't running java9, and the fat jar file is not
  // meant to be used as a module in any case.
  case "module-info.class" => MergeStrategy.discard
  // handle duplicated files that come from aws sdk core is different versions
  case y if y.startsWith("codegen-resources")  => MergeStrategy.last
  case "META-INF/native-image/io.netty/buffer/native-image.properties" => MergeStrategy.last
  case PathList("META-INF","native-image",_, _ @_*) => MergeStrategy.last
//  case PathList("META-INF","native-image","com.google.api",_, _ @_*) => MergeStrategy.last
  case PathList("codegen","templates",_) => MergeStrategy.last
//  case PathList("codegen","templates",_, _ @_*) => MergeStrategy.last
  case "dependencies.properties" => MergeStrategy.last
  case "META-INF/io.netty.versions.properties" => MergeStrategy.last

  case "META-INF/versions/9/module-info.class" => MergeStrategy.last
  case "arrow-git.properties" => MergeStrategy.last
  case x =>
    val old = (assembly / assemblyMergeStrategy).value
    old(x)
}

assembly / assemblyJarName := s"${name.value}-${version.value}.jar"
addArtifact(assembly / artifact, assembly)

