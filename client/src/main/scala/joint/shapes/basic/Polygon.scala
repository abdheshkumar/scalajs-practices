package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Polygon")
class Polygon(options: Options) extends Generic[Options, Polygon] {
  override def attributes: Options = js.native
}