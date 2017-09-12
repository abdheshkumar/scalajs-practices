package tutorial.webapp.router


import diode.react.ModelProxy
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaComponent}
import joint.dia._
import joint.shapes.devs.{Model, ModelProps, Position, Size}

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
      new Paper(PaperProps("#paper", 800, 600, 1,
        graph,
        new Link(LinkProps(
          js.Dynamic.literal(attrs = js.Dynamic.literal(".marker-target" -> js.Dynamic.literal("d" -> "M 10 0 L 0 5 L 10 10 z")))))))

      val in = AttributesStyle(js.Dynamic.literal(".port-body" -> js.Dynamic.literal("fill" -> "#16A085", "magnet" -> "passive")))
      val out = AttributesStyle(js.Dynamic.literal(".port-body" -> js.Dynamic.literal("fill" -> "#E74C3C")))
      val attrs = js.Dynamic.literal(".label" -> js.Dynamic.literal("text" -> "Model 1", "ref-x" -> .5, "ref-y" -> .2),
        "rect" -> js.Dynamic.literal("fill" -> "#2ECC71"))

      val m1 = new Model(ModelProps(Position(50, 150), Size(90, 90),
        outPorts0 = js.Array("out1", "out2"),
        inPorts0 = js.Array[String](),
        PortProps(Group(out0 = out)),
        attrs))

      val m2 = new Model(ModelProps(Position(50, 150), Size(90, 90),
        outPorts0 = js.Array("out1", "out2", "out3"),
        inPorts0 = js.Array[String]("in1"),
        PortProps(Group(in, out)),
        attrs))
      //val m2 = m1.copy()
      m2.attr(".label/text", "Model 2")
      m2.translate(300, 0)
      val m3 = m2.copy()
      m3.attr(".label/text", "Model 3")
      m3.translate(0, 200)

      val m4 = new Model(ModelProps(Position(50, 150), Size(90, 90),
        outPorts0 = js.Array[String](),
        inPorts0 = js.Array[String]("in1"),
        PortProps(Group(in0 = in)),
        attrs))
      m4.translate(600, 100);
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