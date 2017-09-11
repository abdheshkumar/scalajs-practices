package joint.dia

import org.scalajs.jquery.{jQuery, _}

import scala.scalajs.js
import scala.scalajs.js.UndefOr
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@ScalaJSDefined
trait PaperProps extends js.Object {
  val selector: js.UndefOr[String]
  val el: js.UndefOr[JQuery]
  val width: js.UndefOr[Int]
  val height: js.UndefOr[Int]
  val gridSize: js.UndefOr[Int]
  val model: js.UndefOr[Graph]
  val defaultLink: js.UndefOr[Link]
}

object PaperProps {
  @inline
  def apply(selector0: js.UndefOr[String],
            width0: js.UndefOr[Int],
            height0: js.UndefOr[Int],
            gridSize0: js.UndefOr[Int],
            model0: js.UndefOr[Graph],
            defaultLink0: js.UndefOr[Link]): PaperProps = new PaperProps {
    override val defaultLink: UndefOr[Link] = defaultLink0
    override val gridSize: UndefOr[Int] = gridSize0
    override val width: UndefOr[Int] = width0
    override val model: UndefOr[Graph] = model0
    override val selector: UndefOr[String] = selector0
    override val height: UndefOr[Int] = height0
    override val el: UndefOr[JQuery] = jQuery(selector)
  }
}


@js.native
@JSGlobal("joint.dia.Paper")
class Paper(props: PaperProps) extends js.Object
