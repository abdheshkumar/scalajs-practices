package joint.dia

import joint.V
import joint.shapes.devs.Model

import scala.scalajs.js

@js.native
trait CellView extends js.Object {
  val id: String = js.native
  var model: Model = js.native
  val vel: V = js.native

  def get(`type`: String): String = js.native
}
