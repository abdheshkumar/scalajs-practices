package joint.shapes.basic

import joint.dia.Options

import scala.scalajs.js
import scala.scalajs.js.annotation.JSGlobal

@js.native
@JSGlobal("joint.shapes.basic.Image")
class Image(options: Options) extends Generic[Options, Image] {
  override def attributes: Options = js.native
}