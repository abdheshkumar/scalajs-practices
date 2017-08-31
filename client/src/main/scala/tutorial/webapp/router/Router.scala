package tutorial.webapp.router

import japgolly.scalajs.react.extra.router.{BaseUrl, Redirect, Router, RouterConfigDsl}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import tutorial.webapp.react.ReactJsApp
import tutorial.webapp.router.RouterApp.AppPage

import scala.scalajs.js.JSApp

object RouterApp extends JSApp {

  sealed trait AppPage

  case object Home extends AppPage

  case object Todo extends AppPage

  case class Nested(m: InnerPage) extends AppPage

  case class Page(value: Option[Int]) extends AppPage

  val config = RouterConfigDsl[AppPage].buildConfig {
    dsl =>
      import dsl._
      (trimSlashes
        | staticRoute(root, Home) ~> render(HomePage())
        | staticRoute("#home", Todo) ~> render(ReactJsApp.TodoApp())
        | dynamicRouteCT("#item" / int.caseClass[ItemPage]) ~> dynRender({ case ItemPage(i) => ItemPage(Props(i)) })
        | dynamicRouteCT("#page" ~ ("/" ~ int).option.caseClass[Page]) ~> render(<.h1("Page!"))
        | InnerPage.routes.prefixPath_/("module").pmap[AppPage](Nested) { case Nested(m) => m })
        .notFound(redirectToPage(Home)(Redirect.Replace))

  }

  val baseUrl: BaseUrl = BaseUrl.fromWindowOrigin / "web-app"

  val router: Router[AppPage] = Router(baseUrl, config)

  override def main(): Unit = {
    router().renderIntoDOM(dom.document.body)
  }

  sealed trait InnerPage

  object InnerPage {

    case object InnerPageRoot extends InnerPage

    case object InnerPageDetail extends InnerPage

    val routes = RouterConfigDsl[InnerPage].buildRule { dsl =>
      import dsl._
      (emptyRule
        | staticRoute(root, InnerPageRoot) ~> render(<.h1("Module Root page"))
        | staticRoute("detail", InnerPageDetail) ~> render(<.h1("Module Detail"))
        )
    }
  }

}

object HomePage {
  val component =
    ScalaComponent.builder
      .static("HomePage")(<.div("ScalaJS-React Template "))
      .build

  def apply() = component()
}

case class ItemPage(id: Int) extends AppPage

object ItemPage {
  val itemPage = ScalaComponent.builder[Props]("Item page")
    .render_P(p => <.div(s"Info for item #${p.id}"))
    .build

  def apply(props: Props): VdomElement = itemPage(props)
}

case class Props(id: Int)
