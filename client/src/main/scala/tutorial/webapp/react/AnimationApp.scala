package tutorial.webapp.react

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom._

object AnimationApp {

  import ReactAddons._

  class Backend($: BackendScope[Unit, Vector[String]]) {
    def handleAdd =
      $.modState(_ :+ window.prompt("Enter some text"))

    def handleRemove(i: Int) =
      $.modState(_.zipWithIndex.filterNot(_._2 == i).map(_._1))

    def render(state: Vector[String]) = {
      val items = state.zipWithIndex.map { case (item, i) =>
        <.div(^.key := item, ^.`class` := item, ^.onClick --> handleRemove(i),
          item)
      }

      val p = CSSTransitionGroupProps()
      p.transitionName = "example"
      p.transitionEnterTimeout = 500
      p.transitionLeaveTimeout = 300

      <.div(
        <.button(^.onClick --> handleAdd, "Add Item")/*,
        CSSTransitionGroup(p)(items.toVdomArray)*/)
    }
  }

  val App = ScalaComponent.builder[Unit]("TodoList")
    .initialState(Vector("hello", "world", "click", "me"))
    .renderBackend[Backend]
    .build

}
