package tutorial.webapp.router

import chandu0101.scalajs.react.components.materialui.MuiTextField
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import joint.dia.CellView

import scala.scalajs.js

object DialogContent {

  case class Props(cellView: js.UndefOr[CellView])

  case class State(v: String)

  class Backend($: BackendScope[Props, State]) {
    def onChange = (event: ReactEventFromInput, _: String) => {
      val a = event.target.value
      $.modState(_.copy(v = a))
    }

    def onBlur(p: Props) = (event: ReactFocusEventFromInput) => Callback {
      val a = event.target.value
      p.cellView.map(_.model.addInPort(a))
      println("::::" + p.cellView.map(_.model.get("type")))
      //$.modState(_.copy(v = a))
    }

    val textFields = (portsOpt: js.UndefOr[js.Array[String]]) => portsOpt.map {
      ports =>
        ports.zipWithIndex.toVdomArray {
          case (v, k) =>
              MuiTextField(
                key = k.toString,
                hintText = "Hint Text",
                value = v,
                /*onBlur = onBlur(P),*/
                onChange = onChange)()
        }
    }.getOrElse(EmptyVdom)

    def render(P: Props, S: State) = {
      <.div(
        textFields(P.cellView.flatMap(_.model.attributes.inPorts)),
        textFields(P.cellView.flatMap(_.model.attributes.outPorts))/*,
        <.div(s"${P.cellView.map(f => JSON.stringify(f.model.toJSON())).getOrElse("")}")*/
      )
    }
  }

  val component = ScalaComponent
    .builder[Props]("DialogContent")
    .initialState(State("Hello"))
    .renderBackend[Backend]
    .build

  def apply(props: Props) = component(props)

}
