import Dependencies.*
import sbtghactions.GenerativePlugin.compileBranchPredicate
import sbtghactions.PermissionValue.Write

import scala.collection.immutable.ListMap

enablePlugins(ReleasePlugin, AssemblyPlugin)

lazy val jarName = "WarThunderLanguageModifier.jar"

inThisBuild(
  Seq(
    organization := "com.skyline.warlangmod",
    scalaVersion := "3.3.5",
    name := "War Thunder Language Modifier",
    logLevel := Level.Info,
    semanticdbEnabled := true,
    githubWorkflowJavaVersions := Seq(JavaSpec.temurin("21")),
    githubWorkflowSbtCommand := "sbt -mem 4096",
    githubWorkflowUseSbtThinClient := false,
    githubWorkflowIncludeClean := true,
    githubWorkflowTargetTags ++= Seq("v*"),
    githubWorkflowPublishTargetBranches += RefPredicate.StartsWith(
      Ref.Tag("v")
    ),
    githubWorkflowPermissions := Some(Permissions.Specify(Map(
      PermissionScope.RepositoryProjects -> Write,
      PermissionScope.Contents -> Write
    ))),
    githubWorkflowBuild := Seq(
      WorkflowStep.Sbt(List("coverage", "test", "coverageReport"))
    ),
    githubWorkflowBuildPostamble := Seq(
      WorkflowStep.Use(UseRef.Public("codecov", "codecov-action", "v4"), params = ListMap(
        "fail_ci_if_error" -> "true",
        "token" -> s"$${{ secrets.CODECOV_TOKEN }}"
      ))
    ),
    githubWorkflowPublishTargetBranches := Seq(RefPredicate.Equals(Ref.Branch("master"))),
    githubWorkflowPublishPreamble := Seq(
      WorkflowStep.Run(List(s"git config user.email '$${{ github.triggering_actor }}@users.noreply.github.com' ", s"git config user.name '$${{ github.triggering_actor }}' "), name=Some("Identify committer for release command"))
    ),
    githubWorkflowPublish := Seq(
      WorkflowStep.Sbt(List("release with-defaults"), name = Some("Update necessary versions etc.")),
      WorkflowStep.Sbt(List("assembly"), name = Some("Assembling JAR from sources")),
      WorkflowStep.Run(List(s"gh release create --verify-tag --generate-notes v${version.value.replace("-SNAPSHOT", "")} $jarName"), name = Some("Release step for generated jar"), env = ListMap(
        "GH_TOKEN" -> s"$${{ secrets.GITHUB_TOKEN }}"
      ))
    ),
    licenses += (
      "Apache 2.0",
      url("https://www.apache.org/licenses/LICENSE-2.0.html")
    ),
    publish / skip := true,
  )
)


lazy val root = (project in file("."))
  .settings(
    name := "WarThunder Language Updater",
    assembly / mainClass := Some("com.skyline.warlangmod.Main"),
    assembly / assemblyOutputPath := file(jarName),
    libraryDependencies ++= Seq(decline, munit),
  )
