package joint.shapes.devs

import chandu0101.macros.tojs.JSMacro
import joint.dia.{Link, Options, PortOptions}
import joint.shapes.basic.Generic

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}
import scala.scalajs.js.|

@js.native
@JSGlobal("joint.shapes.devs.Model")
class Model(props: ModelOptions) extends Generic[ModelOptions, Model] {
  def translate(x: Int, y: Int): js.native = js.native

  override def attributes: ModelOptions = js.native

  def get(name: String): Link = js.native

  def toJSON(): Model = js.native

  def addInPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def addOutPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def removeOutPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def removeInPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

}

@ScalaJSDefined
trait ModelOptions extends Options {
  var markup: js.UndefOr[String] = js.undefined
  var portMarkup: js.UndefOr[String] = js.undefined
  var portLabelMarkup: js.UndefOr[String] = js.undefined
  var outPorts: js.UndefOr[js.Array[String]] = js.undefined
  var inPorts: js.UndefOr[js.Array[String]] = js.undefined
  var ports: js.UndefOr[PortOptions] = js.undefined
  var nodeMetadata: js.UndefOr[NodeMetadata] = js.undefined
}

@ScalaJSDefined
class NodeMetadata(val byteString: String, val json: String) extends js.Object