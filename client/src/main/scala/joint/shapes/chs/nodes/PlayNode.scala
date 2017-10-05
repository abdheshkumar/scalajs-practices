package joint.shapes.chs.nodes

import japgolly.scalajs.react.vdom.html_<^
import joint.dia._
import joint.shapes.chs.dialogs.PlayNodeDialog
import joint.shapes.devs.{ModelOptions, Props}

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined

@js.native
trait PlayNode extends BaseNode

object PlayNode extends ExtenderOps {

  @ScalaJSDefined
  class PlayNodePrototypeProperties() extends PrototypeProperties {
    val dialog: js.Function1[Props, html_<^.VdomElement] = (props) => PlayNodeDialog(props)

    val openDialog: js.Function1[CellView, Unit] = (cellview) => ()
  }

  private val options = new ModelOptions {
    outPorts = js.Array("out1", "out2", "out3")
    inPorts = js.Array("in")
    attrs = js.Dictionary(".label" -> new AttrStyle {
      text = "Play Node"
      `ref-x` = .5
      `ref-y` = .2
    })
  }
  private val playClass = BaseNode.extender.define("chs.PlayNode", options, new PlayNodePrototypeProperties)

  override val extender: Extender = playClass.asInstanceOf[Extender]

  /**
    *
    * @param options
    * @param prototypeProperties
    * @param staticProperties
    * @return
    */
  def apply(options: js.UndefOr[ModelOptions] = js.undefined,
            prototypeProperties: js.UndefOr[PrototypeProperties] = js.undefined,
            staticProperties: js.UndefOr[StaticProperties] = js.undefined): PlayNode =
    js.Dynamic.newInstance(playClass)(options, prototypeProperties, staticProperties).asInstanceOf[PlayNode]
}