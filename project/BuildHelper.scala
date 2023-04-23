/*
 * Copyright (c) 2022 Lightricks. All rights reserved.
 */
import sbt.Keys._
import sbt._
import scalafix.sbt.ScalafixPlugin.autoImport._

object BuildHelper {
  val ScalaVersion = "2.12.14"

  def commonSettings(scalaVersion: String) = CrossVersion.partialVersion(scalaVersion) match {
    case Some((3, _))                  => Seq.empty
    case Some((2, 12)) | Some((2, 13)) => Seq("-Ywarn-unused:params")
    case _                             => Seq.empty
  }
  def stdSettings = Seq(
    ThisBuild / organization := "com.lightricks",
    // See https://www.scala-sbt.org/1.x/docs/Forking.html
    ThisBuild / fork := true,
    ThisBuild / scalaVersion := ScalaVersion,
    ThisBuild / scalacOptions := commonSettings(scalaVersion.value),
    // See https://scalameta.org/docs/semanticdb/guide.html
    ThisBuild / semanticdbEnabled := true,
    ThisBuild / semanticdbVersion := scalafixSemanticdb.revision,
    // See https://github.com/liancheng/scalafix-organize-imports
    ThisBuild / scalafixDependencies ++=
      List(
        "com.github.liancheng" %% "organize-imports" % "0.6.0"
      )
  )
}
