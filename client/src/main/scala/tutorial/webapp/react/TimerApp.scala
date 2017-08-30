package tutorial.webapp.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js

object TimerApp {

  case class State(secondsElapsed: Long)

  class Backend($: BackendScope[Unit, State]) {
    var interval: js.UndefOr[js.timers.SetIntervalHandle] = js.undefined

    def tick: CallbackTo[Unit] = $.modState(s => State(s.secondsElapsed + 1))

    def start: CallbackTo[Unit] = Callback {
      interval = js.timers.setInterval(100)(tick.runNow())
    }

    def clear: CallbackTo[Unit] = Callback {
      interval foreach js.timers.clearInterval
      interval = js.undefined
    }

    def render(s: State) =
      <.div("Seconds elapsed: ", s.secondsElapsed)
  }

  val Timer = ScalaComponent.builder[Unit]("Timer")
    .initialState(State(0))
    .renderBackend[Backend]
    .componentDidMount(_.backend.start)
    .componentWillUnmount(_.backend.clear)
    .build
}
