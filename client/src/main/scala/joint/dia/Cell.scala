package joint.dia

import japgolly.scalajs.react.vdom.html_<^.VdomElement
import joint.shapes.devs.Props

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

@js.native
abstract trait Cell[OP <: Options, C <: Cell[OP, C]] extends js.Object {
  def attributes: OP

  @JSName("clone")
  def copy(): C = js.native

  def attr(attrs: String, value: String): js.native = js.native
}

@ScalaJSDefined
trait PrototypeProperties extends js.Object {
  def openDialog(cellView: CellView): Unit

  def dialog(props: Props): VdomElement
}

@ScalaJSDefined
trait StaticProperties extends js.Object {

}