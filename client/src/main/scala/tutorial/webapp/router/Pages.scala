package tutorial.webapp.router

import chandu0101.scalajs.react.components.materialui.{MuiDialog, MuiFlatButton, MuiMuiThemeProvider, TouchTapEvent}
import diode.react.ModelProxy
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import japgolly.scalajs.react.vdom.TagOf
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, Callback, _}
import joint.CallbackDebug
import joint.dia._
import joint.shapes.basic.Rect
import joint.shapes.devs.{Model, ModelOptions}
import org.scalajs.dom
import org.scalajs.dom.html.Div
import org.scalajs.jquery._

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
      val paper = new Paper(new PaperOptions {
        el = jQuery("#paper")
        width = 800
        height = 600
        gridSize = 1
        model = graph
        defaultLink = new Link(new LinkOptions {
          attrs = js.Dictionary(".marker-target" -> new Attrs {
            d = "M 10 0 L 0 5 L 10 10 z"
          })
        })
        markAvailable = true
        linkPinning = false
        validateConnection = js.defined((cellViewS, magnetS, cellViewT, magnetT, end, linkView) => {
          val port = magnetS.getAttribute("port")
          val links = graph.getConnectedLinks(cellViewS.model, new BoundProps {
            outbound = true
          }).filter(_.get("source").port == port)

          if (!js.isUndefined(magnetS) && magnetS.getAttribute("port-group") == "in") false
          else if (links.length > 1) false
          else !js.isUndefined(magnetT) && magnetT.getAttribute("port-group") == "in"
        })
      })


      paper.on("cell:pointerclick", (cellView, event, _, _) => {
        open(cellView)
      })
      val in0 = new Attrs {
        portBody = new AttrStyle {
          fill = "#16A085"
          magnet = "passive"
        }
      }

      val out0 = new Attrs {
        portBody = new AttrStyle {
          fill = "#E74C3C"
        }
      }

      val attrs0 = new Attrs {
        label = new AttrStyle {
          text = "Model 1"
          `ref-x` = .5
          `ref-y` = .2
        }
        rect = new AttrStyle {
          fill = "#2ECC71"
        }
      }
      val m1 = new Model(new ModelOptions {
        position = new Position {
          x = 50
          y = 150
        }
        size = new Size {
          width = 200
          height = 100
        }
        outPorts = js.Array("out1", "out2", "out3", "out4", "out5")
        inPorts = js.Array[String]()
        ports = new PortOptions {
          groups = new GroupOptions {
            out = new Options {
              attrs = out0
            }
          }
        }
        attrs = attrs0
      })

      val m2 = new Model(new ModelOptions {
        position = new Position {
          x = 50
          y = 150
        }
        size = new Size {
          width = 90
          height = 90
        }
        outPorts = js.Array("out1", "out2", "out3")
        inPorts = js.Array[String]("in1")
        ports = new PortOptions {
          groups = new GroupOptions {
            out = new Options {
              attrs = out0
            }
            in = new Options {
              attrs = in0
            }
          }
        }
        attrs = attrs0
      })


      m2.attr(".label/text", "Model 2")
      m2.translate(300, 0)
      val m3 = m2.copy()
      m3.attr(".label/text", "Model 3")
      m3.translate(0, 200)

      val m4 = new Model(new ModelOptions {
        position = new Position {
          x = 50
          y = 150
        }
        size = new Size {
          width = 90
          height = 90
        }
        outPorts = js.Array[String]()
        inPorts = js.Array("in1")
        ports = new PortOptions {
          groups = new GroupOptions {
            in = new Options {
              attrs = in0
            }
          }
        }
        attrs = attrs0
      })

      val r = new Rect(new Options {
        attrs = new Attrs {
          text = new AttrStyle {
            text = "Rect Model"
          }
        }
      })

      m4.attr(".label/text", "Model 4")
      m4.translate(600, 100)
      graph.addCell[ModelOptions, Model](m1)
      graph.addCell[ModelOptions, Model](m2)
      graph.addCell[ModelOptions, Model](m3)
      graph.addCell[ModelOptions, Model](m4)
    }

    private def open(cellView: CellView) = {
      ($.setState(State(true, cellView)) >> Callback.log("Dialog Open")).runNow()
    }

    def getJson(cellView: js.UndefOr[CellView]): CallbackTo[Unit] = Callback {
      dom.console.log(JSON.stringify(cellView.map(_.model).getOrElse("")))
    }


    val close = $.modState(_.copy(isOpen = false))

    def handleDialogCancel: TouchTapEvent => Callback =
      e => close >> Callback.info("Cancel Clicked")

    def handleDialogSubmit(cellView: js.UndefOr[CellView]): TouchTapEvent => Callback =
      e => close >> Callback.info("Submit Clicked") >> Callback {
        cellView.map(_.model.addInPort("Added"))
      }

    def render(S: State): TagOf[Div] = {
      dom.console.log(S.cellView.map(_.model))
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
        <.button(^.onClick --> getJson(S.cellView), "Get Json")
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