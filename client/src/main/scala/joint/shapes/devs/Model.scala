package joint.shapes.devs

import japgolly.scalajs.react.CallbackTo
import japgolly.scalajs.react.vdom.html_<^.VdomElement
import joint.dia._
import joint.shapes.basic.Generic
import joint.shapes.chs.nodes.{Extender, ExtenderOps}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait Model extends Generic[ModelOptions, Model] {

  def translate(x: Int, y: Int): js.native = js.native

  override def attributes: ModelOptions = js.native

  def get(name: String): Link = js.native

  def toJSON(): Model = js.native

  def addInPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def addOutPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def removeOutPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def removeInPort(port: String, opt: js.UndefOr[js.Any] = js.undefined): Model = js.native

  def openDialog(cellView: CellView): Unit = js.native

  def dialog(props: Props): VdomElement = js.native

  val markup: String = js.native
}


object Model extends ExtenderOps {
  override val extender: Extender = js.Dynamic.global.joint.shapes.devs.Model.asInstanceOf[Extender]

  def apply(props: ModelOptions): Model = js.Dynamic.newInstance(js.Dynamic.global.joint.shapes.devs.Model)(props).asInstanceOf[Model]
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

case class Props(isOpen: Boolean,
                 cellView: js.UndefOr[CellView],
                 close: CallbackTo[Unit])