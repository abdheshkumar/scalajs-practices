package tutorial.webapp

import org.scalajs.dom.document
import org.scalajs.jquery.jQuery
import tutorial.webapp.TutorialApp.appendPar

import scala.scalajs.js

object JqueryApp {
  def main(args: Array[String]): Unit = {
    //jQuery("body").append("<p>[message]</p>")
    jQuery(() => {
      visualize(JRSProps("http://localhost:8080/jasperserver-pro", LoginWithPlainText("superuser", "superuser")))
    })
  }

  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }


  val f: js.Function1[JRSClient, Unit] = (v: JRSClient) => {
    v("#container").report(ReportProps("http://localhost:8080/jasperserver-pro",
      "/public/Samples/Reports/Call_Handling_Account_Details_Report"))
    println("User logged-in")
  }

  def setupUI(): Unit = {
    jQuery("#click-me-button").click(() => TutorialApp.addClickedMessage())
    /*  jQuery("""<button type="button">Click me!</button>""")
        .click(addClickedMessage _)
        .appendTo(jQuery("body"))*/
    jQuery("body").append("<p>Hello World</p>")
  }
}
