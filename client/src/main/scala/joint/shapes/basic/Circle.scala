package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Circle")
class Circle(options: Options) extends Generic[Options, Circle] {
  override def attributes: Options = js.native
}