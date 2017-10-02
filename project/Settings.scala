import sbt._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import com.trueaccord.scalapb.compiler.Version.scalapbVersion

object Settings {

  /** The version of your application */
  val version = "1.0"

  /** Options for the scala compiler */
  val scalacOptions = Seq(
    "-Xlint",
    "-unchecked",
    "-deprecation",
    "-feature"
  )

  /** Declare global dependency versions here to avoid mismatches in multi part dependencies */
  object versions {
    val scala = "2.12.3"
    val scalaDom = "0.9.2"
    val scalajsReact = "1.1.0"
    val scalaTags = "0.6.5"
    val diode = "1.1.2"
    val uTest = "0.4.7"

    val react = "15.6.1"
    val akkaHttp = "10.0.9"
  }

  /** Dependencies for external JS libs that are bundled into a single .js file according to dependency order */
  val jsDependencies = Def.setting(Seq(
    //"org.webjars.bower" % "react" % versions.react / "react-with-addons.js" minified "react-with-addons.min.js" commonJSName "React",
    //"org.webjars.bower" % "react" % versions.react / "react-dom.js" minified "react-dom.min.js" dependsOn "react-with-addons.js" commonJSName "ReactDOM",
    //"org.webjars.bower" % "react" % versions.react / "react-dom-server.js" minified "react-dom-server.min.js" dependsOn "react-dom.js" commonJSName "ReactDOMServer"
  ))

  /** Dependencies only used by the JVM project */
  val scalajsDependencies = Def.setting(Seq(
    "org.scala-js" %%% "scalajs-dom" % versions.scalaDom,
    "be.doeraene" %%% "scalajs-jquery" % versions.scalaDom,
    "com.github.japgolly.scalajs-react" %%% "core" % versions.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % versions.scalajsReact,
    "io.suzaku" %%% "diode" % versions.diode,
    "io.suzaku" %%% "diode-react" % versions.diode,
    "com.lihaoyi" %%% "scalatags" % versions.scalaTags,
    "com.olvind" %%% "scalajs-react-components" % "0.8.0",
    "com.lihaoyi" %%% "utest" % versions.uTest % "test",
    "com.chs" %%% "com.chs.protocols" % "0.01",
    "com.trueaccord.scalapb" %%% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion,
    "com.trueaccord.scalapb" %%% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion % "protobuf"
  ))

  val circeVersion = "0.8.0"

  val circle = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion)

  /** Dependencies only used by the JVM project */
  val jvmDependencies = Def.setting(Seq(
    "com.typesafe.akka" %% "akka-http" % versions.akkaHttp,
    "com.chs" %% "com.chs.protocols" % "0.01"
  ) ++ circle)
}
