package tutorial.webapp

import org.scalajs.dom.document
import org.scalajs.jquery.jQuery
import tutorial.webapp.TutorialApp.appendPar

object JqueryApp {
  def main(args: Array[String]): Unit = {
    //jQuery("body").append("<p>[message]</p>")
    jQuery(() => setupUI())
  }

  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(() => TutorialApp.addClickedMessage())
    /*  jQuery("""<button type="button">Click me!</button>""")
        .click(addClickedMessage _)
        .appendTo(jQuery("body"))*/
    jQuery("body").append("<p>Hello World</p>")
  }
}
