package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Text")
class Text(options: Options) extends Generic[Options, Text] {
  override def attributes: Options = js.native
}
