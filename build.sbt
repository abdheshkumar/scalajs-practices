import sbt.Keys.version

lazy val commonSettings = Seq(
  version := Settings.version,
  organization := "com.abtechsoft",
  scalaVersion := Settings.versions.scala,
  scalacOptions ++= Settings.scalacOptions)

lazy val shared = (crossProject.crossType(CrossType.Pure) in (file("shared")))
  .settings(commonSettings)
  .settings(
    name := "shared",
    publish := {},
    publishLocal := {},
    libraryDependencies ++= Seq(
      "com.trueaccord.scalapb" %% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion % "protobuf"
    ),
    PB.targets in Compile := Seq(
      scalapb.gen() -> (sourceManaged in Compile).value
    ),
    PB.protoSources in Compile := Seq(
      baseDirectory.value.getParentFile / "src/main/protobuf"
    )
  )

lazy val sharedJVM = shared.jvm.settings(name := "sharedJVM")

lazy val sharedJS = shared.js.settings(name := "sharedJS")


lazy val client: Project = (project in file("client"))
  .settings(commonSettings)
  .settings(
    name := "client",
    libraryDependencies ++= Settings.scalajsDependencies.value,
    jsDependencies ++= Settings.jsDependencies.value,
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
    artifactPath in(Test, packageMinifiedJSDependencies) := file("web-app/js/deps.min.test.js") /*,
    webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack.config.js"),
    crossTarget in npmUpdate in Compile := file("web-app/js"),
    npmDevDependencies in Compile ++= Seq(
      "bootstrap" -> "3.3.7",
      "clean-webpack-plugin" -> "0.1.16",
      "css-loader" -> "0.28.7",
      "image-webpack-loader" -> "^3.4.2",
      "file-loader" -> "0.11.2",
      "imports-loader" -> "0.7.1",
      "less-vars-loader" -> "^1.1.0",
      "resolve-url-loader" -> "2.1.0",
      "style-loader" -> "0.18.2",
      "url-loader" -> "0.5.9",
      "expose-loader" -> "0.7.1"
    ),
    npmDependencies in Compile ++= Seq(
      "react" -> "15.6.1",
      "react-dom" -> "15.6.1",
      "react-split-pane" -> "0.1.66",
      "jquery" -> "2.1.4",
      "material-ui" -> "0.19.2",
      "react-addons-create-fragment" -> "^15.4.2",
      "react-addons-css-transition-group" -> "^15.4.2",
      "react-addons-pure-render-mixin" -> "^15.4.2",
      "react-addons-transition-group" -> "^15.4.2",
      "react-addons-update" -> "^15.4.2",
      "react-geomicons" -> "^2.0.4",
      "react-infinite" -> "^0.10.0",
      "react-select" -> "^1.0.0-rc.3",
      "react-slick" -> "^0.14.6",
      "react-spinner" -> "^0.2.3",
      "react-split-pane" -> "0.1.66",
      "react-tagsinput" -> "^3.15.1",
      "react-tap-event-plugin" -> "^2.0.1"
    )*/
  )
  .enablePlugins(ScalaJSPlugin)
  //.enablePlugins(ScalaJSBundlerPlugin)
  .dependsOn(sharedJS)


lazy val server = (project in file("server"))
  .settings(commonSettings)
  .settings(
    name := "server",
    libraryDependencies ++= Settings.jvmDependencies.value
  ).dependsOn(sharedJVM)
