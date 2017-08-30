package tutorial.webapp.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object StateMonad {
  val TodoList = ScalaComponent.builder[List[String]]("TodoList")
    .render_P(items =>
      <.ul(items.map(<.li(_)): _*))
    .build

  case class State(items: List[String], text: String)

  class Backend($: BackendScope[_, State]) {
    def onTextChange(e: ReactEventFromInput): CallbackTo[Unit] = {
      val newValue = e.target.value
      $.modState(_.copy(text = newValue))
    }

    def handleSubmit(e: ReactEventFromInput): CallbackTo[Unit] =
      e.preventDefaultCB >>
        $.modState(s => s.copy(s.items :+ s.text, ""))

  }


  val Search = ScalaComponent.builder[(State, Backend)]("Search")
    .render_P { case (s, b) =>
      <.form(^.onSubmit ==> b.handleSubmit, // runState runs a state monad and applies the result.
        <.input( // runStateFn is similar but takes a function-to-a-state-monad.
          ^.onChange ==> b.onTextChange, // In these cases, the function will be fed the JS event.
          ^.value := s.text),
        <.button("Add #", s.items.length + 1))
    }.build

  val TodoApp = ScalaComponent.builder[Unit]("TodoApp")
    .initialState(State(Nil, ""))
    .backend(new Backend(_))
    .renderS(($, s) =>
      <.div(
        <.h3("TODO"),
        <.div(),
        TodoList(s.items),
        Search((s, $.backend)))
    ).build

}
