package joint

import chandu0101.scalajs.react.components.materialui._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import chandu0101.scalajs.react.components.Implicits._
import scala.scalajs.js

object MuiDialogDemo {

  case class State(isOpen: Boolean)

  class Backend($: BackendScope[_, State]) {
    val open = $.setState(State(true))
    val close = $.setState(State(false))

    def handleDialogCancel: TouchTapEvent => Callback =
      e => close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit: TouchTapEvent => Callback =
      e => close >> Callback.info("Submit Clicked")

    val openDialog: TouchTapEvent => Callback =
      e => open >> Callback.info("Opened")

    def render(S: State) = {
      val actions: VdomNode = js.Array(
        MuiFlatButton(key = "1",
          label = "Cancel",
          secondary = true,
          onTouchTap = handleDialogCancel)(),
        MuiFlatButton(key = "2",
          label = "Submit",
          secondary = true,
          onTouchTap = handleDialogSubmit)()
      ).toVdomArray
      <.div(
          <.div(
            MuiDialog(
              title = "Dialog With Actions",
              actions = actions,
              open = S.isOpen,
              onRequestClose = CallbackDebug.f1("onRequestClose")
            )(
              "Dialog example with floating buttons"
            ),
            MuiRaisedButton(label = "Dialog", onTouchTap = openDialog)()
        )
      )
    }
  }

  val component = ScalaComponent
    .builder[Unit]("MuiDialogDemo")
    .initialState(State(isOpen = false))
    .renderBackend[Backend]
    .build

  // EXAMPLE:END

  def apply() = component()

}
