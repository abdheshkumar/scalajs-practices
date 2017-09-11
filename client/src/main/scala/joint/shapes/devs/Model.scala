package joint.shapes.devs

import joint.dia.PortProps

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@ScalaJSDefined
trait Size extends js.Object {
  val width: js.UndefOr[Int]
  val height: js.UndefOr[Int]
}

object Size {
  @inline
  def apply(width0: Int, height0: Int): Size = new Size {
    override val width: UndefOr[Int] = width0
    override val height: UndefOr[Int] = height0
  }
}

@ScalaJSDefined
trait Position extends js.Object {
  val x: js.UndefOr[Int]
  val y: js.UndefOr[Int]
}


object Position {
  @inline
  def apply(x0: Int, y0: Int): Position = new Position {
    override val x: UndefOr[Int] = x0
    override val y: UndefOr[Int] = y0
  }
}

@js.native
@JSGlobal("joint.shapes.devs.Model")
class Model(props: ModelProps) extends js.Object {
  def translate(x: Int, y: Int): js.native = js.native
}


@ScalaJSDefined
trait ModelProps extends js.Object {
  val position: js.UndefOr[Position]
  val size: js.UndefOr[Size]
  val outPorts: js.UndefOr[List[String]]
  val intPorts: js.UndefOr[List[String]]
  val ports: js.UndefOr[PortProps]
  val attrs: js.UndefOr[js.Dynamic]

}

object ModelProps {
  @inline
  def apply(position0: js.UndefOr[Position],
            size0: js.UndefOr[Size],
            outPorts0: js.UndefOr[List[String]],
            intPorts0: js.UndefOr[List[String]],
            ports0: js.UndefOr[PortProps],
            attrs0: js.UndefOr[js.Dynamic]): ModelProps = new ModelProps {
    override val ports: UndefOr[PortProps] = ports0
    override val outPorts: UndefOr[List[String]] = outPorts0
    override val attrs: UndefOr[js.Dynamic] = attrs0
    override val size: UndefOr[Size] = size0
    override val intPorts: UndefOr[List[String]] = intPorts0
    override val position: UndefOr[Position] = position0
  }

}