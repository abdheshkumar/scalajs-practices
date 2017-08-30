enablePlugins(ScalaJSPlugin)

name := "scalajs-test"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
  "com.github.japgolly.scalajs-react" %%% "core" % "1.1.0",
  "com.github.japgolly.scalajs-react" %%% "extra" % "1.1.0",
  "com.lihaoyi" %%% "utest" % "0.4.4" % "test")

testFrameworks += new TestFramework("utest.runner.Framework")

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

skip in packageJSDependencies := false

jsDependencies ++= Seq(
  "org.webjars.bower" % "react" % "15.6.1" / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
  "org.webjars.bower" % "react" % "15.6.1" / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
  "org.webjars.bower" % "react" % "15.6.1" / "react-dom-server.js" minified  "react-dom-server.min.js" dependsOn "react-dom.js" commonJSName "ReactDOMServer"
)