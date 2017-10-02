package tutorial.webapp.router

import chandu0101.scalajs.react.components.materialui.MuiMuiThemeProvider
import diode.react.ModelProxy
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, _}
import joint.dia._
import joint.shapes.devs.{Model, ModelOptions, PlayNode}
import org.scalajs.dom
import org.scalajs.dom.html.Div

import scala.scalajs.js
import scala.scalajs.js.JSON


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
      val paper = DiagramUtility.createPaperLayout("#paper", graph)
      paper.on("cell:pointerclick", (cellView, event, _, _) => {
        //cellView.model.openDialog(cellView)
        open(cellView)
      })


      val m1 = PlayNode()
      val m2 = DiagramUtility.callNode("gatherNode", js.Array("in1"), js.Array("out1", "out2", "out3"), "Model 2")
      val m3 = m2.copy()
      val m4 = DiagramUtility.callNode("playNode", js.Array("in1"), js.Array[String](), "Model 4")

      m2.translate(300, 0)
      m3.attr(".label/text", "Model 3")
      m3.translate(0, 200)
      m4.translate(600, 100)
      graph.addCell[ModelOptions, Model](m1)
      graph.addCell[ModelOptions, Model](m2)
      graph.addCell[ModelOptions, Model](m3)
      graph.addCell[ModelOptions, Model](m4)
      //graph.addCell[Options, Rect](r)
    }

    val close = $.modState(_.copy(isOpen = false))

    private def open(cellView: CellView) = {
      ($.setState(State(true, cellView)) >> Callback.log("Dialog Open")).runNow()
    }

    def getJson(S: State): CallbackTo[Unit] = Callback {
      dom.console.log(JSON.stringify(graph.toJSON()))
    }

    def render(S: State): TagOf[Div] = {
      val empty: VdomElement = ScalaComponent.static("EmptyComponent")(<.div(""))()
      val component = S.cellView.map(_.model).toOption match {
        case Some(n) => //n.dialog(Props(S.isOpen, S.cellView, close))
        /* if (n == PlayNode) PlayNodeDialog(PlayNodeDialog.Props(S.isOpen, S.cellView, close))
         else if (n == GatherNode) GatherNodeDialog(GatherNodeDialog.Props(S.isOpen, S.cellView, close))
         else empty*/
        case _ => empty
      }
      <.div(
        <.div(s"JointJs-React Template"),
        <.div(^.id := "paper", ""),
        <.div(^.id := "paper1", ""),
        MuiMuiThemeProvider()(<.div(empty)),
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