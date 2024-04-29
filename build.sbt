ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.13"

ThisBuild / organization := "com.skyline.warlangmod"

lazy val root = (project in file("."))
  .settings(
    name := "WarThunder Language Updater",
    assembly / mainClass := Some("com.skyline.warlangmod.Main"),
    libraryDependencies ++= Seq(
      "com.monovore" %% "decline" % "2.4.1",
      "org.scalameta" %% "munit" % "0.7.29" % Test,
    ),
  )
