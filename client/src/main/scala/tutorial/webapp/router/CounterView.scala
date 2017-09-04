package tutorial.webapp.router

import diode.Action
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.BackendScope
import japgolly.scalajs.react.vdom.html_<^._

object CounterView {

  case class Props(proxy: ModelProxy[Counter])

  class Backend($: BackendScope[Props, Unit]) {
    def render(p: Props) = {
      val proxy = p.proxy()
      val dispatch: Action => Callback = p.proxy.dispatchCB
      <.div(
        <.h3("Counter"),
        <.p("Value = ", <.b(proxy.i)),
        <.button(^.onClick --> dispatch(Increase(2)), "Increase"),
        <.button(^.onClick --> dispatch(Decrease(1)), "Decrease"),
        <.button(^.onClick --> dispatch(Reset), "Reset")
      )
    }
  }

  private val component = ScalaComponent
    .builder[Props]("TodoList")
    .renderBackend[Backend]
    .build

  def apply(mp: ModelProxy[Counter]): VdomElement = component(Props(mp))

}