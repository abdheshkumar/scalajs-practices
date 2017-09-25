package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Path")
class Path(options: Options) extends Generic[Options, Path] {
  override def attributes: Options = js.native
}