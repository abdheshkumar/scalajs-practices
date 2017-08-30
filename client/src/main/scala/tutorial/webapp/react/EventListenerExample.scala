package tutorial.webapp.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import org.scalajs.dom.MouseEvent

object EventListenerExample {
  val Listener = ScalaComponent.builder[Unit]("EventListener Example")
    .initialState("Local mouseenter events + local/global click events will appear here.")
    .renderBackend[Backend]
    .configure(

      // Listen to mouseenter events within the component
      EventListener[MouseEvent].install("mouseenter", _.backend.logMouseEnter),

      // Listen to click events
      EventListener.install("click", _.backend.logLocalClick),
      EventListener.install("click", _.backend.logWindowClick, _ => dom.window)
    )
    .build

  class Backend($: BackendScope[Unit, String]) extends OnUnmount {
    def logEvent(desc: String) = $.modState(_ + "\n" + desc)

    def logMouseEnter(e: MouseEvent) = logEvent(s"Mouse enter @ ${e.pageX},${e.pageY}")

    val logWindowClick = logEvent("Window clicked.")
    val logLocalClick = logEvent("Component clicked.")

    def render(state: String) =
      <.pre(
        ^.border := "solid 1px black",
        ^.width := "90ex",
        ^.height := "20em",
        ^.padding := "2px 6px",
        state)
  }

}
