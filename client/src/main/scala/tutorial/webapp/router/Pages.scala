package tutorial.webapp.router

import chandu0101.scalajs.react.components.materialui.MuiMuiThemeProvider
import diode.react.ModelProxy
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import japgolly.scalajs.react.vdom.{TagOf, html_<^}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, _}
import joint.dia._
import joint.shapes.chs.dialogs.PlayNodeDialog
import joint.shapes.chs.nodes.BaseNode.BaseNodePrototypeProperties
import joint.shapes.chs.nodes.{BaseNode, GatherNode, PlayNode, StartNode}
import joint.shapes.devs._
import org.scalajs.dom
import org.scalajs.dom.html.Div

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.scalajs.js.annotation.ScalaJSDefined


sealed trait AppPage

case object Home extends AppPage

case object Todo extends AppPage

case object JointJs extends AppPage

case class Nested(m: InnerPage) extends AppPage

case class Page(value: Option[Int]) extends AppPage

//Inner pages
sealed trait InnerPage

object InnerPage {

  case object InnerPageRoot extends InnerPage

  case object InnerPageDetail extends InnerPage

  val routes = RouterConfigDsl[InnerPage].buildRule { dsl =>
    import dsl._
    (emptyRule
      | staticRoute(root, InnerPageRoot) ~> render(<.h1("Module Root page"))
      | staticRoute("#detail", InnerPageDetail) ~> render(<.h1("Module Detail"))
      )
  }
}


object JointJsPage {

  case class State(isOpen: Boolean, cellView: js.UndefOr[CellView])

  class Backend($: BackendScope[Unit, State]) {

    val graph = new Graph()

    def buildGraph() = {


      val m1 = BaseNode()
      val paper = DiagramUtility.createPaperLayout("#paper", graph)
      /* paper.on("element:pointerup", (cellView, event, _, _) => {

         dom.console.log(event.target)
         open(cellView)
       })*/


      val m2 = PlayNode()
      //val m3 = GatherNode()
      m2.translate(300, 0)
      //m3.translate(0, 200)
      graph.addCell[ModelOptions, Model](m1)
      graph.addCell[ModelOptions, Model](m2)
      //graph.addCell[ModelOptions, Model](m3)
    }

    val close = $.modState(_.copy(isOpen = false))

    private def open(cellView: CellView): Unit = {
      ($.setState(State(true, cellView)) >> Callback.log("Dialog Open")).runNow()
    }

    def getJson(S: State): CallbackTo[Unit] = Callback {
      dom.console.log(JSON.stringify(graph.toJSON()))
    }

    def render(S: State): TagOf[Div] = {
      val empty: VdomElement = ScalaComponent.static("EmptyComponent")(<.div(""))()
      val component = S.cellView.map(_.model).toOption.fold(empty)(_.dialog(Props(S.isOpen, S.cellView, close)))
      <.div(
        <.div(s"JointJs-React Template"),
        <.div(^.id := "paper", ""),
        <.div(^.id := "paper1", ""),
        MuiMuiThemeProvider()(<.div(component)),
        <.button(^.onClick --> getJson(S), "Get Json")
      )
    }
  }

  private val component = ScalaComponent.builder[Unit]("JointJsPage")
    .initialState(State(isOpen = false, js.undefined))
    .renderBackend[Backend]
    .componentDidMount(_f => Callback {
      _f.backend.buildGraph()
    }).build

  def apply(): VdomElement = component()
}

object HomePage {

  case class Props(ctr: RouterCtl[AppPage], rp: ModelProxy[Counter])

  private val component =
    ScalaComponent.builder[Props]("HomePage")
      .render_P(p => {
        <.div(
          <.div(s"ScalaJS-React Template"),
          p.ctr.link(ItemPage(1))("Goto Item 1", ^.color := "red"),
          CounterView(p.rp)
        )
      }
      ).build

  def apply(props: Props): VdomElement = component(props)
}

case class ItemPage(id: Int) extends AppPage

object ItemPage {

  case class Props(id: Int)

  private val itemPage = ScalaComponent.builder[Props]("Item page")
    .render_P(p => <.div(s"Info for item #${p.id}"))
    .build

  def apply(props: Props): VdomElement = itemPage(props)
}