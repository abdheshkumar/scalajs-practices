package joint.shapes.devs

import japgolly.scalajs.react.CallbackTo
import japgolly.scalajs.react.vdom.html_<^.VdomElement
import joint.dia._
import joint.shapes.basic.Generic
import joint.shapes.devs.Node.nodeClass

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@js.native
trait Model extends Generic[ModelOptions, Model] with PrototypeProperties {

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
}


@js.native
trait Extender extends js.Object {
  def define(`type`: String, props: js.UndefOr[ModelOptions] = js.undefined,
             prototypeProperties: js.UndefOr[PrototypeProperties] = js.undefined,
             staticProperties: js.UndefOr[StaticProperties]= js.undefined): js.Dynamic = js.native
}



trait ExtenderOps {

  val extender:Extender
}
object Model  extends ExtenderOps {
  override val extender = js.Dynamic.global.joint.shapes.devs.Model.asInstanceOf[Extender]

  def apply(props: ModelOptions) = js.Dynamic.newInstance(js.Dynamic.global.joint.shapes.devs.Model)(props).asInstanceOf[Model]


}



@js.native
trait Node extends Model

object Node extends ExtenderOps {


  private val nodeClass = {
    val attrs0 = new Attrs {
      label = new AttrStyle {
        text = "Node Name"
        `ref-x` = .5
        `ref-y` = .2
      }
      rect = new AttrStyle {
        fill = "#2ECC71"
      }
    }

    val in0 = new Attrs {
      portBody = new AttrStyle {
        fill = "#16A085"
        magnet = "passive"
      }
    }

    val out0 = new Attrs {
      portBody = new AttrStyle {
        fill = "#E74C3C"
      }
    }
    val options = new ModelOptions {
      position = new Position {
        x = 50
        y = 150
      }
      size = new Size {
        width = 90
        height = 90
      }
      inPorts = js.Array("in")
      ports = new PortOptions {
        groups = new GroupOptions {
          in = new Options {
            attrs = in0
          }
          out = new Options {
            attrs = out0
          }
        }
      }
      attrs = attrs0
    }

    Model.extender.define("Node", options)
  }

  override val extender = nodeClass.asInstanceOf[Extender]

}

@js.native
trait PlayNode extends Node

object PlayNode extends ExtenderOps {
  private val playClass = Node.extender.define("chs.PlayNode", new ModelOptions {
    outPorts = js.Array("out1", "out2", "out3")
  })

  override val extender = playClass.asInstanceOf[Extender]

  def apply(props: js.UndefOr[ModelOptions] = js.undefined) = js.Dynamic.newInstance(playClass)(props).asInstanceOf[PlayNode]
}

@js.native
trait StartNode extends Node
object StartNode extends ExtenderOps {
  private val startClass = Node.extender.define("chs.StartNode", new ModelOptions { })

  override val extender = startClass.asInstanceOf[Extender]

  def apply(props: js.UndefOr[ModelOptions] = js.undefined) = js.Dynamic.newInstance(startClass)(props).asInstanceOf[StartNode]
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