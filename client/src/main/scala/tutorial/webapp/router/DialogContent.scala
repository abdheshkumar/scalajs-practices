package tutorial.webapp.router

import chandu0101.scalajs.react.components.materialui.MuiTextField
import com.chs.playNodeData.PlayNodeData
import com.trueaccord.scalapb.json.JsonFormat
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import joint.dia.CellView
import joint.shapes.devs.NodeMetadata

import scala.scalajs.js

object DialogContent {

  case class Props(cellView: js.UndefOr[CellView])

  case class State(v: PlayNodeData)

  class Backend($: BackendScope[Props, State]) {

    def onBlur(p: Props, S: State, fun: (PlayNodeData, String) => PlayNodeData) = (event: ReactFocusEventFromInput) => {
      val a = event.target.value
      val n = fun(S.v, a)
      p.cellView.map(_.model.attributes.nodeMetadata = new NodeMetadata(n.toByteString.toStringUtf8, ""))
      $.modState(s => s.copy(n))
    }

    def render(P: Props, S: State) = {
      <.div(
        MuiTextField(
          hintText = "Type name",
          defaultValue = S.v.name,
          onBlur = onBlur(P, S, (n, in) => n.withName(in)))(),
        MuiTextField(
          hintText = "Type number",
          defaultValue = S.v.calories.toString,
          onBlur = onBlur(P, S, (n, in) => n.withCalories(in.toInt)))(),
        MuiTextField(
          hintText = "Type number",
          defaultValue = S.v.measure.toString,
          onBlur = onBlur(P, S, (n, in) => n.withMeasure(in)))()
      )
    }
  }

  val component = ScalaComponent
    .builder[Props]("DialogContent")
    .initialStateFromProps {
      p =>

        val node = p.cellView.flatMap(_.model.attributes.nodeMetadata)
          .toOption
          .map(f => PlayNodeData.parseFrom(f.byteString.getBytes)).getOrElse(PlayNodeData())
        //println("::::::" + JsonFormat.toJsonString[PlayNodeData](PlayNodeData()))
        State(node)
    }
    .renderBackend[Backend]
    .build

  def apply(props: Props) = component(props)

}
