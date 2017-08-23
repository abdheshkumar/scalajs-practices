package tutorial.webapp

import scala.scalajs.js
import scala.scalajs.js.annotation.{JSGlobal, ScalaJSDefined}


@js.native
trait JRSClient extends js.Object {
  /**
    * Perform authentication with provided auth object
    *
    * @param authType {object} - auth properties
    */
  def login(authType: AuthType): Unit = js.native

  /**
    * Destroy current auth session
    */
  def logout(): Unit = js.native

  /**
    * Create and run report component with provided properties
    *
    * @param props {object} - report properties
    * @return {Report} report - instance of Report
    */
  def report(props: ReportProps): Report = js.native

  def apply(container: String): JRSClient = js.native
}

@ScalaJSDefined
class JRSProps private(server: String, auth: AuthType) extends js.Object

object JRSProps {
  def apply(server0: String,
            authType0: AuthType): JRSProps =
    new JRSProps(server0, authType0)
}


@ScalaJSDefined
class ReportProps private(server: js.UndefOr[String],
                          resource: js.UndefOr[String],
                          container: js.UndefOr[String]) extends js.Object

object ReportProps {
  def apply(server: js.UndefOr[String],
            resource: js.UndefOr[String],
            container: js.UndefOr[String] = js.undefined): ReportProps =
    new ReportProps(server, resource, container)
}

@js.native
trait Report extends js.Object

@js.native
@JSGlobal("visualize")
object visualize extends js.Object{
  def apply(props: JRSProps,
            callback: js.UndefOr[js.Function1[JRSClient, Unit]] = js.undefined,
            errorback: js.UndefOr[js.Function1[JRSClient, Unit]] = js.undefined,
            always: js.UndefOr[js.Function1[JRSClient, Unit]] = js.undefined): Unit = js.native
}