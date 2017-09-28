package tutorial.webapp.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object ReactJsApp /*extends JSApp*/ {
  val TodoList = ScalaComponent.builder[List[String]]("TodoList")
    .render_P { props =>
      def createItem(itemText: String) = <.li(itemText)

      <.ul(props map createItem: _*)
    }
    .build

  case class State(items: List[String], text: String)

  class Backend($: BackendScope[Unit, State]) {

    def onChange(e: ReactEventFromInput): CallbackTo[Unit] = {
      val newValue = e.target.value
      $.modState(_.copy(text = newValue))
    }

    def handleSubmit(e: ReactEventFromInput): CallbackTo[Unit] =
      e.preventDefaultCB >>
        $.modState(s => s.copy(s.items :+ s.text, ""))

    def render(state: State): VdomElement =
      <.div(
        <.h3("TODO"),
        TodoList(state.items),
        <.form(^.onSubmit ==> handleSubmit,
          <.input(^.`type` := "text", ^.onChange ==> onChange, ^.value := state.text),
          <.button("Add #", state.items.length + 1)
        ),
        FocusAndReset.App(),
        ProductTable.com,
        StateMonad.TodoApp(),
        AnimationApp.App(),
        TimerApp.Timer(),
        EventListenerExample.Listener(),
        WebSocketsExample.WebSocketsApp()
      )
  }

  val TodoApp = ScalaComponent.builder[Unit]("TodoApp")
    .initialState(State(Nil, ""))
    .renderBackend[Backend]
    .build

  /*override def main(): Unit = {
    TodoApp().renderIntoDOM(document.getElementById("playground"))
  }*/

}
