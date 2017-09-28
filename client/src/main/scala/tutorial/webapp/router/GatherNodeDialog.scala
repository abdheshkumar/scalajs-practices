package tutorial.webapp.router

import java.util.Base64

import chandu0101.scalajs.react.components.materialui.{MuiDialog, MuiFlatButton, MuiTextField, TouchTapEvent}
import com.chs.playNodeData.PlayNodeData
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import joint.CallbackDebug
import joint.dia.CellView
import joint.shapes.devs.NodeMetadata

import scala.scalajs.js

object GatherNodeDialog {

  case class Props(isOpen: Boolean,
                   cellView: js.UndefOr[CellView],
                   close: CallbackTo[Unit])

  case class State(isOpen: Boolean, nodeData: PlayNodeData)

  class Backend($: BackendScope[Props, State]) {

    def onBlur(p: Props, S: State, fun: (PlayNodeData, String) => PlayNodeData) = (event: ReactFocusEventFromInput) => {
      val a = event.target.value
      val n = fun(S.nodeData, a)
      p.cellView.map(_.model.attributes.nodeMetadata = new NodeMetadata(Base64.getEncoder.encodeToString(n.toByteArray), ""))
      $.modState(s => s.copy(nodeData = n))
    }

    val close = $.modState(_.copy(isOpen = false)) >> $.props.flatMap(_.close)

    def handleDialogCancel: TouchTapEvent => Callback =
      e => close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit(cellView: js.UndefOr[CellView]): TouchTapEvent => Callback =
      e => close >> Callback.info("Submit Clicked") >> Callback {
        //cellView.map(_.model.addInPort("Added"))
      }

    val actions = (cellView: js.UndefOr[CellView]) => js.Array(
      MuiFlatButton(key = "1",
        label = "Cancel",
        secondary = true,
        onTouchTap = handleDialogCancel)(),
      MuiFlatButton(key = "2",
        label = "Submit",
        secondary = true,
        onTouchTap = handleDialogSubmit(cellView))()
    ).toVdomArray

    def render(P: Props, S: State) = {
      <.div(
        MuiDialog(
          title = "Gather Node Dialog",
          actions = actions(P.cellView),
          open = S.isOpen,
          onRequestClose = CallbackDebug.f1("onRequestClose")
        )(
          <.div(
            MuiTextField(
              hintText = "Type name",
              defaultValue = S.nodeData.name,
              onBlur = onBlur(P, S, (n, in) => n.withName(in)))(),
            MuiTextField(
              hintText = "Type number",
              defaultValue = S.nodeData.calories.toString,
              onBlur = onBlur(P, S, (n, in) => n.withCalories(in.toInt)))(),
            MuiTextField(
              hintText = "Type number",
              defaultValue = S.nodeData.measure.toString,
              onBlur = onBlur(P, S, (n, in) => n.withMeasure(in)))(),
            <.div(s"Node's Configuration:${S.nodeData}")
          )

        )
      )

    }
  }

  /**
    * It will re-render on each time
    *
    * @return
    */
  def component = ScalaComponent
    .builder[Props]("DialogContent")
    .initialStateFromProps {
      p =>
        val node = p.cellView.flatMap(_.model.attributes.nodeMetadata)
          .toOption
          .map { f =>
            PlayNodeData.parseFrom(Base64.getDecoder().decode(f.byteString))
          }.getOrElse(PlayNodeData())
        State(p.isOpen, node)
    }
    .renderBackend[Backend]
    .build

  def apply(props: Props): VdomElement = component(props)

}
