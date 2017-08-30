import sbt.Keys.version

lazy val root = (crossProject.crossType(CrossType.Pure) in (file("shared"))).
  settings(
    version := Settings.version,
    scalaVersion := Settings.versions.scala
  )

lazy val sharedJVM = root.jvm.settings(name := "sharedJVM")

lazy val sharedJS = root.js.settings(name := "sharedJS")

val scalajsOutputDir = Def.settingKey[File]("directory for javascript files output by scalajs")

lazy val client: Project = (project in file("client"))
  .settings(
    name := "client",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    scalajsOutputDir := sourceDirectory.value / "web-app" / "js",
    libraryDependencies ++= Settings.scalajsDependencies.value,
    jsDependencies ++= Settings.jsDependencies.value,
    skip in packageJSDependencies := false,
    // use Scala.js provided launcher code to start the client app
    scalaJSUseMainModuleInitializer := true,
    scalaJSUseMainModuleInitializer in Test := false,
    // use uTest framework for tests
    testFrameworks += new TestFramework("utest.runner.Framework"),
    artifactPath in(Compile, fastOptJS) := scalajsOutputDir.value
  )
  .enablePlugins(ScalaJSPlugin)
  .dependsOn(sharedJS)


lazy val server = (project in file("server"))
  .settings(
    name := "server",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value
  ).dependsOn(sharedJVM)
