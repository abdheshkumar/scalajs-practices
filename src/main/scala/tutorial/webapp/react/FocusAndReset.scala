package tutorial.webapp.react

import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, _}
import org.scalajs.dom._
import org.scalajs.dom.html.Div

object FocusAndReset {

  class Backend($: BackendScope[Unit, String]) {

    var theInput: html.Input = _

    def handleChange(e: ReactEventFromInput): CallbackTo[Unit] =
      $.setState(e.target.value)

    def clearAndFocusInput(): CallbackTo[Unit] =
      $.setState("", Callback(theInput.focus()))

    def render(state: String): TagOf[Div] =
      <.div(
        <.div(
          ^.onClick --> clearAndFocusInput,
          "Click to Focus and Reset"),
        <.input(
          ^.value := state,
          ^.onChange ==> handleChange)
          .ref(theInput = _)
      )
  }

  val App = ScalaComponent.builder[Unit]("App")
    .initialState("")
    .renderBackend[Backend]
    .build

}
