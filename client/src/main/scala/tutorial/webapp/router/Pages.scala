package tutorial.webapp.router


import diode.react.ModelProxy
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaComponent}
import joint.dia._
import joint.shapes.devs.{Model, ModelOptions, Position, Size}
import org.scalajs.jquery._
import scala.scalajs.js


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
      | staticRoute("detail", InnerPageDetail) ~> render(<.h1("Module Detail"))
      )
  }
}

object JointJsPage {
  private val component =
    ScalaComponent.builder[Unit]("JointJsPage")
      .render_({
        <.div(
          <.div(s"JointJs-React Template"),
          <.div(^.id := "paper", "")
        )
      }
      ).componentDidMount(_ => Callback {


      val graph = new Graph()
      new Paper(new PaperOptions {
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

      val in0 = new AttributesStyle {
        attrs = js.Dictionary(".port-body" -> new Attrs {
          fill = "#16A085"
          magnet = "passive"
        })
      }
      val out0 = new AttributesStyle {
        attrs = js.Dictionary(".port-body" -> new Attrs {
          fill = "#E74C3C"
        })
      }
      val attrs0 = js.Dictionary(
        ".label" -> new Attrs {
          text = "Model 1"
          `ref-x` = .5
          `ref-y` = .2
        },
        "rect" -> new Attrs {
          fill = "#2ECC71"
        })

      val m1 = new Model(new ModelOptions {
        position = new Position {
          x = 50
          y = 150
        }
        size = new Size {
          width = 90
          height = 90
        }
        outPorts = js.Array("out1", "out2")
        inPorts = js.Array[String]()
        ports = new PortOptions {
          groups = new Group {
            out = out0
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
          groups = new Group {
            out = out0
            in = in0
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
          groups = new Group {
            in = in0
          }
        }
        attrs = attrs0
      })

      m4.attr(".label/text", "Model 4")
      m4.translate(600, 100)
      graph.addCell(m1)
      graph.addCell(m2)
      graph.addCell(m3)
      graph.addCell(m4)
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