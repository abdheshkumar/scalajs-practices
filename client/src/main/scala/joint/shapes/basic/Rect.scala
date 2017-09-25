package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Rect")
class Rect(options: Options) extends Generic[Options, Rect] {
  override def attributes: Options = js.native
}
