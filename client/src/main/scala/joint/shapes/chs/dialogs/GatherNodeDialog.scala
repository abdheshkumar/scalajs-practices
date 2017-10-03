package joint.shapes.chs.dialogs

import java.util.Base64

import chandu0101.scalajs.react.components.materialui.{MuiDialog, MuiFlatButton, MuiTextField, TouchTapEvent}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import joint.CallbackDebug
import joint.shapes.devs.{NodeMetadata, Props}
import ngage.sdk.graph.node.PlayNodeData

import scala.scalajs.js

object GatherNodeDialog {

  case class State(isOpen: Boolean, nodeData: PlayNodeData)

  class Backend($: BackendScope[Props, State]) {

    def onBlur(p: Props, S: State, fun: (PlayNodeData, String) => PlayNodeData) = (event: ReactFocusEventFromInput) => {
      val a = event.target.value
      val n = fun(S.nodeData, a)
      $.modState(s => s.copy(nodeData = n))
    }

    val close = $.modState(_.copy(isOpen = false)) >> $.props.flatMap(_.close)

    def handleDialogCancel: TouchTapEvent => Callback =
      e => close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit(p: Props, S: State): TouchTapEvent => Callback =
      e => close >> Callback {
        p.cellView.map(_.model.attributes.nodeMetadata = new NodeMetadata(Base64.getEncoder.encodeToString(S.nodeData.toByteArray), ""))
      } >> Callback.info("Submit Clicked")


    val actions = (p: Props, S: State) => js.Array(
      MuiFlatButton(key = "1",
        label = "Cancel",
        secondary = true,
        onTouchTap = handleDialogCancel)(),
      MuiFlatButton(key = "2",
        label = "Submit",
        secondary = true,
        onTouchTap = handleDialogSubmit(p, S))()
    ).toVdomArray

    def render(P: Props, S: State) = {
      <.div(
        MuiDialog(
          title = "Gather Node Dialog",
          actions = actions(P, S),
          open = S.isOpen,
          onRequestClose = CallbackDebug.f1("onRequestClose")
        )(
          <.div(
            MuiTextField(
              hintText = "Type name",
              defaultValue = S.nodeData.nodeName,
              onBlur = onBlur(P, S, (n, in) => n.withNodeName(in)))(),
            MuiTextField(
              hintText = "Type number",
              defaultValue = S.nodeData.nodeId.toString,
              onBlur = onBlur(P, S, (n, in) => n.withNodeId(in)))(),
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
