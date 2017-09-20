import sbt.Keys.version

lazy val root = (crossProject.crossType(CrossType.Pure) in (file("shared"))).
  settings(
    version := Settings.version,
    scalaVersion := Settings.versions.scala
  )

lazy val sharedJVM = root.jvm.settings(name := "sharedJVM")

lazy val sharedJS = root.js.settings(name := "sharedJS")

lazy val client: Project = (project in file("client"))
  .settings(
    name := "client",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.scalajsDependencies.value,
    jsDependencies ++= Settings.jsDependencies.value,
    skip in packageJSDependencies := false,
    // use Scala.js provided launcher code to start the client app
    scalaJSUseMainModuleInitializer := true,
    scalaJSUseMainModuleInitializer in Test := false,
    // use uTest framework for tests
    testFrameworks += new TestFramework("utest.runner.Framework"),
    artifactPath in(Compile, fastOptJS) := file("web-app/js/app.js"),
    artifactPath in(Compile, packageJSDependencies) := file("web-app/js/app.dep.js"),
    artifactPath in(Compile, packageMinifiedJSDependencies) := file("web-app/js/deps.min.js"),
    artifactPath in(Test, fastOptJS) := file("web-app/js/app.test.js"),
    artifactPath in(Test, fullOptJS) := file("web-app/js/app.min.test.js"),
    artifactPath in(Test, packageJSDependencies) := file("web-app/js/deps.test.js"),
    artifactPath in(Test, packageMinifiedJSDependencies) := file("web-app/js/deps.min.test.js"),
    npmDependencies in Compile ++= Seq(
      "material-ui" -> "0.19.2",
      "react" -> "15.6.0",
      "react-dom" -> "15.6.0"
    )
  )
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .dependsOn(sharedJS)


lazy val server = (project in file("server"))
  .settings(
    name := "server",
    version := Settings.version,
    scalaVersion := Settings.versions.scala,
    scalacOptions ++= Settings.scalacOptions,
    libraryDependencies ++= Settings.jvmDependencies.value
  ).dependsOn(sharedJVM)
