package joint.dia

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSName, ScalaJSDefined}
import scala.scalajs.js.|

@ScalaJSDefined
trait Attrs extends js.Object {
  var d: js.UndefOr[String] = js.undefined
  @JSName(".label")
  var label: js.UndefOr[AttrStyle] = js.undefined
  @JSName(".port-body")
  var portBody: js.UndefOr[AttrStyle] = js.undefined
  var rect: js.UndefOr[AttrStyle] = js.undefined
  var text: js.UndefOr[AttrStyle] = js.undefined
  var circle: js.UndefOr[AttrStyle] = js.undefined
  var ellipse: js.UndefOr[AttrStyle] = js.undefined
  var polygon: js.UndefOr[AttrStyle] = js.undefined
  var polyline: js.UndefOr[AttrStyle] = js.undefined
  var path: js.UndefOr[AttrStyle] = js.undefined
}

@ScalaJSDefined
trait AttrStyle extends js.Object {
  var fill: js.UndefOr[String] = js.undefined
  var stroke: js.UndefOr[String] = js.undefined
  var r: js.UndefOr[Int] = js.undefined
  var magnet: js.UndefOr[Boolean | String] = js.undefined
  var text: js.UndefOr[String] = js.undefined
  var `ref-x`: js.UndefOr[Int | Double] = js.undefined
  var `ref-y`: js.UndefOr[Int | Double] = js.undefined
  var `ref-width`: js.UndefOr[Int | String] = js.undefined
  var `ref-height`: js.UndefOr[Int | String] = js.undefined
  var `text-anchor`: js.UndefOr[String] = js.undefined
  var `ref-dx`: js.UndefOr[Int | Double] = js.undefined
  var `ref-dy`: js.UndefOr[Int | Double] = js.undefined
  var `font-size`: js.UndefOr[Int] = js.undefined
  var `stroke-width`: js.UndefOr[Int] = js.undefined
  var cursor: js.UndefOr[String] = js.undefined
}