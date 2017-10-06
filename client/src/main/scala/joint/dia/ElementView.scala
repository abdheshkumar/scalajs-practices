package joint.dia

import joint.shapes.chs.views.{ViewExtender, ViewExtenderOps}

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}

@js.native
@JSGlobal("joint.dia.ElementView")
class ElementView extends CellView {}

object ElementView extends ViewExtenderOps {
  override val extender: ViewExtender = js.Dynamic.global.joint.dia.ElementView.asInstanceOf[ViewExtender]
}

@ScalaJSDefined
trait ElementViewPrototype extends js.Object {
  var events: js.UndefOr[js.Dictionary[String]] = js.undefined
  var init: js.UndefOr[js.ThisFunction0[CellView, Unit]] = js.undefined
  var renderMarkup: js.UndefOr[js.ThisFunction0[CellView, Unit]] = js.undefined
}

@ScalaJSDefined
trait ElementViewStatic extends js.Object {
  var markup: js.UndefOr[String] = js.undefined
}