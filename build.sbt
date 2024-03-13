ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

ThisBuild / organization := "com.skyline.warlangmod"

lazy val root = (project in file("."))
  .settings(
    name := "WarThunder Language Updater",
    assembly / mainClass := Some("com.skyline.warlangmod.Main"),
  )
