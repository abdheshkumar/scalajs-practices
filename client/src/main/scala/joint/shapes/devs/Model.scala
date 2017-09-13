package joint.shapes.devs

import joint.dia.{Attrs, Link, PortOptions}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, JSName, ScalaJSDefined}

//you should use "facade" instead of "props", as that's the usual terminology
//props reminds everyone of React props

@ScalaJSDefined
trait Size extends js.Object {
  var width: js.UndefOr[Int] = js.undefined
  var height: js.UndefOr[Int] = js.undefined
}

@ScalaJSDefined
trait Position extends js.Object {
  var x: js.UndefOr[Int] = js.undefined
  var y: js.UndefOr[Int] = js.undefined
}

@js.native
@JSGlobal("joint.shapes.devs.Model")
class Model(props: ModelOptions) extends js.Object {
  def translate(x: Int, y: Int): js.native = js.native

  @JSName("clone")
  def copy(): Model = js.native

  def get(name: String): Link = js.native

  def attr(attrs: String, value: String): js.native = js.native
}


@ScalaJSDefined
trait ModelOptions extends js.Object {
  var position: js.UndefOr[Position] = js.undefined
  var size: js.UndefOr[Size] = js.undefined
  var outPorts: js.UndefOr[js.Array[String]] = js.undefined
  var inPorts: js.UndefOr[js.Array[String]] = js.undefined
  var ports: js.UndefOr[PortOptions] = js.undefined
  var attrs: js.UndefOr[js.Dictionary[Attrs]] = js.undefined
}