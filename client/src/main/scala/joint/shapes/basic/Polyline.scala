package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Polyline")
class Polyline(options: Options) extends Generic[Options, Polyline] {
  override def attributes: Options = js.native
}