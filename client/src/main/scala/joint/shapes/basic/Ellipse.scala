package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Ellipse")
class Ellipse(options: Options) extends Generic[Options,Ellipse] {
  override def attributes: Options = js.native
}