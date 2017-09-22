package tutorial.webapp.router

import japgolly.scalajs.react.extra.router.{BaseUrl, Redirect, Router, RouterConfigDsl}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom
import tutorial.webapp.react.ReactJsApp

import scala.scalajs.js.JSApp

object RouterApp extends JSApp {

  val config = RouterConfigDsl[AppPage].buildConfig {
    dsl =>
      import dsl._
      val cp = AppCircuit.connect(_.counter)
      (staticRoute(root, Home) ~> renderR(routerCtrl => {
        cp(p => HomePage(HomePage.Props(routerCtrl, p)))
      })
        | staticRoute("#home", Todo) ~> render(ReactJsApp.TodoApp())
        | staticRoute("#jointjs", JointJs) ~> render(JointJsPage())
        | dynamicRouteCT("#item" / int.caseClass[ItemPage]) ~> dynRender({ case ItemPage(i) => ItemPage(ItemPage.Props(i)) })
        | dynamicRouteCT("#page" ~ ("/" ~ int).option.caseClass[Page]) ~> render(<.h1("Page!"))
        | InnerPage.routes.prefixPath_/("module").pmap[AppPage](Nested) { case Nested(m) => m })
        .notFound(redirectToPage(Home)(Redirect.Replace))

  }

  val baseUrl: BaseUrl = BaseUrl.until_#

  val router: Router[AppPage] = Router(baseUrl, config)

  override def main(): Unit = {
    router().renderIntoDOM(dom.document.getElementById("playground"))
  }

}


