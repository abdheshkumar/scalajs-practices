package tutorial.webapp

import org.scalajs.dom
import dom.document
import org.scalajs.jquery.jQuery
import scala.scalajs.js.annotation.JSExportTopLevel

object TutorialApp {
  /*def main(args: Array[String]): Unit = {
    appendPar(document.body, "Hello, World")
  }*/

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage(): Unit = {
    //appendPar(document.body, "You clicked the button!")
    jQuery("body").append("<p>[message]</p>")
  }
}
