package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
abstract trait Cell[OP <: Options, C <: Cell[OP, C]] extends js.Object {
  def attributes: OP

  @JSName("clone")
  def copy(): C = js.native

  def attr(attrs: String, value: String): js.native = js.native
}
