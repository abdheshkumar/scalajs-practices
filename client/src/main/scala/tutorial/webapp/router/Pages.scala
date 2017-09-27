package tutorial.webapp.router

import chandu0101.scalajs.react.components.materialui.{MuiDialog, MuiFlatButton, MuiMuiThemeProvider, TouchTapEvent}
import diode.react.ModelProxy
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, _}
import joint.CallbackDebug
import joint.dia._
import joint.shapes.devs.{Model, ModelOptions}
import org.scalajs.dom
import org.scalajs.dom.html.Div

import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.util.Random


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
        //cellView.model.attributes.a =  "custom-field"
        open(cellView)
      })


      val m1 = DiagramUtility.callNode(js.Array[String](), js.Array("1", "2", "3", "4", "5"), "Model 1")
      val m2 = DiagramUtility.callNode(js.Array("in1"), js.Array("out1", "out2", "out3"), "Model 2")
      val m3 = m2.copy()
      val m4 = DiagramUtility.callNode(js.Array("in1"), js.Array[String](), "Model 4")

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


    private def open(cellView: CellView) = {
      ($.setState(State(true, cellView)) >> Callback.log("Dialog Open")).runNow()
    }

    def getJson(): CallbackTo[Unit] = Callback {
      /*val graph1 = new Graph()
      val paper = DiagramUtility.createPaperLayout("#paper1", graph1)
      val st = """{"cells":[{"type":"devs.Model","inPorts":[],"outPorts":["1","2","3","4","5"],"size":{"width":90,"height":90},"ports":{"groups":{"in":{"position":{"name":"left"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#16A085","stroke":"#000","r":10,"magnet":"passive"}},"label":{"position":{"name":"left","args":{"y":10}}}},"out":{"position":{"name":"right"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#E74C3C","stroke":"#000","r":10,"magnet":true}},"label":{"position":{"name":"right","args":{"y":10}}}}},"items":[{"id":"1","group":"out","attrs":{".port-label":{"text":"1"}}},{"id":"2","group":"out","attrs":{".port-label":{"text":"2"}}},{"id":"3","group":"out","attrs":{".port-label":{"text":"3"}}},{"id":"4","group":"out","attrs":{".port-label":{"text":"4"}}},{"id":"5","group":"out","attrs":{".port-label":{"text":"5"}}}]},"position":{"x":50,"y":150},"angle":0,"id":"6b2d6e9b-30c8-4e51-a54f-e5fdb7925c7d","z":1,"attrs":{".label":{"text":"Model 1","ref-y":0.2},"rect":{"fill":"#2ECC71"}}},{"type":"devs.Model","inPorts":["in1"],"outPorts":["out1","out2","out3"],"size":{"width":90,"height":90},"ports":{"groups":{"in":{"position":{"name":"left"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#16A085","stroke":"#000","r":10,"magnet":"passive"}},"label":{"position":{"name":"left","args":{"y":10}}}},"out":{"position":{"name":"right"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#E74C3C","stroke":"#000","r":10,"magnet":true}},"label":{"position":{"name":"right","args":{"y":10}}}}},"items":[{"id":"in1","group":"in","attrs":{".port-label":{"text":"in1"}}},{"id":"out1","group":"out","attrs":{".port-label":{"text":"out1"}}},{"id":"out2","group":"out","attrs":{".port-label":{"text":"out2"}}},{"id":"out3","group":"out","attrs":{".port-label":{"text":"out3"}}}]},"position":{"x":350,"y":150},"angle":0,"id":"8cfee16c-7130-4b80-b46e-9040d609258b","z":2,"a":{"id$1":"Test data","hello$1":"Name:Test data","number$1":1,"list$1":{"u":["Test data","Test data","Test data"]},"config$1":{"name$1":"Test data"}},"attrs":{".label":{"text":"Model 2","ref-y":0.2},"rect":{"fill":"#2ECC71"}}},{"type":"devs.Model","inPorts":["in1"],"outPorts":["out1","out2","out3"],"size":{"width":90,"height":90},"ports":{"groups":{"in":{"position":{"name":"left"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#16A085","stroke":"#000","r":10,"magnet":"passive"}},"label":{"position":{"name":"left","args":{"y":10}}}},"out":{"position":{"name":"right"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#E74C3C","stroke":"#000","r":10,"magnet":true}},"label":{"position":{"name":"right","args":{"y":10}}}}},"items":[{"id":"in1","group":"in","attrs":{".port-label":{"text":"in1"}}},{"id":"out1","group":"out","attrs":{".port-label":{"text":"out1"}}},{"id":"out2","group":"out","attrs":{".port-label":{"text":"out2"}}},{"id":"out3","group":"out","attrs":{".port-label":{"text":"out3"}}}]},"position":{"x":50,"y":350},"angle":0,"id":"fe66c047-bdb4-4f59-9fcc-045aa41690bc","z":3,"attrs":{".label":{"text":"Model 3","ref-y":0.2},"rect":{"fill":"#2ECC71"}}},{"type":"devs.Model","inPorts":["in1"],"outPorts":[],"size":{"width":90,"height":90},"ports":{"groups":{"in":{"position":{"name":"left"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#16A085","stroke":"#000","r":10,"magnet":"passive"}},"label":{"position":{"name":"left","args":{"y":10}}}},"out":{"position":{"name":"right"},"attrs":{".port-label":{"fill":"#000"},".port-body":{"fill":"#E74C3C","stroke":"#000","r":10,"magnet":true}},"label":{"position":{"name":"right","args":{"y":10}}}}},"items":[{"id":"in1","group":"in","attrs":{".port-label":{"text":"in1"}}}]},"position":{"x":650,"y":250},"angle":0,"id":"853ddf9d-c306-409d-9342-c8dfb1e2818f","z":4,"attrs":{".label":{"text":"Model 4","ref-y":0.2},"rect":{"fill":"#2ECC71"}}},{"type":"link","source":{"id":"6b2d6e9b-30c8-4e51-a54f-e5fdb7925c7d","selector":"g:nth-child(1) > g:nth-child(4) > circle:nth-child(1)","port":"2"},"target":{"id":"8cfee16c-7130-4b80-b46e-9040d609258b","port":"in1","selector":"g:nth-child(1) > g:nth-child(3) > circle:nth-child(1)"},"id":"39473b6f-0d7f-4d8d-a613-246c2fe9523b","z":5,"attrs":{".marker-target":{"d":"M 10 0 L 0 5 L 10 10 z"}}}]}"""
      graph1.fromJSON(JSON.parse(st))
      paper.on("cell:pointerclick", (cellView, event, _, _) => {
        //cellView.model.attributes.a =  "custom-field"
        //dom.console.log(cellView.model.attributes.a.map(_.name))
      })*/
      dom.console.log(JSON.stringify(graph.toJSON()))
    }


    val close = $.modState(_.copy(isOpen = false))

    def handleDialogCancel: TouchTapEvent => Callback =
      e => close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit(cellView: js.UndefOr[CellView]): TouchTapEvent => Callback =
      e => close >> Callback.info("Submit Clicked") >> Callback {

        //cellView.map(_.model.addInPort("Added"))
      }

    def render(S: State): TagOf[Div] = {
      dom.console.log(S.cellView.map(_.model))
      //dom.console.log(S.cellView.flatMap(_.model.attributes.a.map(_.id)))
      val actions: VdomNode = js.Array(
        MuiFlatButton(key = "1",
          label = "Cancel",
          secondary = true,
          onTouchTap = handleDialogCancel)(),
        MuiFlatButton(key = "2",
          label = "Submit",
          secondary = true,
          onTouchTap = handleDialogSubmit(S.cellView))()
      ).toVdomArray
      val r = Random.nextInt()
      <.div(
        <.div(s"JointJs-React Template ${r}"),
        <.div(^.id := "paper", ""),
        <.div(^.id := "paper1", ""),
        MuiMuiThemeProvider()(
          <.div(
            MuiDialog(
              title = "Dialog With Actions",
              actions = actions,
              open = S.isOpen,
              onRequestClose = CallbackDebug.f1("onRequestClose")
            )(
              DialogContent(DialogContent.Props(S.cellView))
            )
          )
        ),
        <.button(^.onClick --> getJson(), "Get Json")
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