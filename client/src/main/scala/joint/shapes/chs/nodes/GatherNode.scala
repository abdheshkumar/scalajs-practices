package joint.shapes.chs.nodes

import japgolly.scalajs.react.vdom.html_<^
import joint.dia._
import joint.shapes.chs.dialogs.GatherNodeDialog
import joint.shapes.devs.{ModelOptions, Props}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait GatherNode extends BaseNode

object GatherNode extends ExtenderOps {

  @ScalaJSDefined
  class GatherNodePrototypeProperties() extends PrototypeProperties {
    val dialog: js.Function1[Props, html_<^.VdomElement] = (props) => GatherNodeDialog(props)

    val openDialog: js.Function1[CellView, Unit] = (cellview) => ()
  }

  private val options = new ModelOptions {
    inPorts = js.Array[String]("in")
    outPorts = js.Array("out1")
    ports = new PortOptions {
      groups = new GroupOptions {
        out = new Options {
          attrs = new Attrs {
            portBody = new AttrStyle {
              fill = "#E74C3C"
            }
          }
        }
      }
    }
    attrs = new Attrs {
      label = new AttrStyle {
        text = "Gather Node"
        `ref-x` = .5
        `ref-y` = .2
      }
    }
  }
  private val gatherClass = BaseNode.extender.define("chs.GatherNode", options, new GatherNodePrototypeProperties)

  override val extender: Extender = gatherClass.asInstanceOf[Extender]

  /**
    *
    * @param options
    * @param prototypeProperties
    * @param staticProperties
    * @return
    */
  def apply(options: js.UndefOr[ModelOptions] = js.undefined,
            prototypeProperties: js.UndefOr[PrototypeProperties] = js.undefined,
            staticProperties: js.UndefOr[StaticProperties] = js.undefined): GatherNode =
    js.Dynamic.newInstance(gatherClass)(options, prototypeProperties, staticProperties).asInstanceOf[GatherNode]
}