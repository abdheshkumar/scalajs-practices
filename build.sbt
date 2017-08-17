enablePlugins(ScalaJSPlugin)

name := "scalajs-test"

version := "1.0"

scalaVersion := "2.12.3"

libraryDependencies ++= Seq(
  "org.scala-js" %%% "scalajs-dom" % "0.9.1",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.1",
  "com.lihaoyi" %%% "utest" % "0.4.4" % "test")

testFrameworks += new TestFramework("utest.runner.Framework")

// This is an application with a main method
scalaJSUseMainModuleInitializer := true

skip in packageJSDependencies := false
jsDependencies += "org.webjars" % "jquery" % "2.1.4" / "2.1.4/jquery.js"