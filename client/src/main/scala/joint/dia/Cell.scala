package joint.dia

import japgolly.scalajs.react.vdom.html_<^
import joint.shapes.devs.Props

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}

@js.native
abstract trait Cell[OP <: Options, C <: Cell[OP, C]] extends js.Object {
  def attributes: OP

  def id: String = js.native

  @JSName("clone")
  def copy(): C = js.native

  def attr(attrs: String, value: js.UndefOr[String] = js.undefined): js.native = js.native
}

@ScalaJSDefined
trait PrototypeProperties extends js.Object {
  val dialog: js.Function1[Props, html_<^.VdomElement]
  val openDialog: js.Function1[CellView, Unit]
}

@ScalaJSDefined
trait DialogModelHandler extends js.Object {

}
@ScalaJSDefined
trait StaticProperties extends js.Object {

}