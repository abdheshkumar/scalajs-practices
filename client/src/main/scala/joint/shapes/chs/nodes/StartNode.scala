package joint.shapes.chs.nodes

import japgolly.scalajs.react.vdom.html_<^
import joint.dia._
import joint.shapes.chs.dialogs.StartNodeDialog
import joint.shapes.devs.{ModelOptions, Props}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait StartNode extends BaseNode

object StartNode extends ExtenderOps {

  @ScalaJSDefined
  class StartNodePrototypeProperties() extends PrototypeProperties {
    val dialog: js.Function1[Props, html_<^.VdomElement] = (props) => StartNodeDialog(props)

    val openDialog: js.Function1[CellView, Unit] = (cellview) => ()
  }

  private val options = new ModelOptions {
    inPorts = js.Array[String]()
    outPorts = js.Array("out1")
    attrs = js.Dictionary(".label" -> new AttrStyle {
      text = "Start Node"
      `ref-x` = .5
      `ref-y` = .2
    })
  }
  private val startClass = BaseNode.extender.define("chs.StartNode", options, new StartNodePrototypeProperties)

  override val extender: Extender = startClass.asInstanceOf[Extender]

  /**
    *
    * @param options
    * @param prototypeProperties
    * @param staticProperties
    * @return
    */
  def apply(options: js.UndefOr[ModelOptions] = js.undefined,
            prototypeProperties: js.UndefOr[PrototypeProperties] = js.undefined,
            staticProperties: js.UndefOr[StaticProperties] = js.undefined): StartNode =
    js.Dynamic.newInstance(startClass)(options, prototypeProperties, staticProperties).asInstanceOf[StartNode]
}