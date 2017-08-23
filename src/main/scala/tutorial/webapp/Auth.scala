package tutorial.webapp

import scala.scalajs.js
import scala.scalajs.js.annotation.ScalaJSDefined


@ScalaJSDefined
sealed trait AuthType extends js.Object {
  //loginFn: function (properties, request)
  //logoutFn: function (properties, request)
}

@js.native
trait LoginWithPlainText extends AuthType {
  val name: String = js.native
  val password: String = js.native
  val organization: js.UndefOr[String] = js.undefined
  val timezone: js.UndefOr[String] = js.undefined
}

object LoginWithPlainText {
  def apply(name: String,
            password: String,
            organization: js.UndefOr[String] = js.undefined,
            timezone: js.UndefOr[String] = js.undefined): LoginWithPlainText =
    js.Dynamic.literal(name = name, password = password, organization = organization, timezone = timezone)
      .asInstanceOf[LoginWithPlainText]
}

@js.native
trait LoginWithSSOToken extends AuthType {
  val token: String = js.native
  val preAuth: js.UndefOr[Boolean] = js.undefined
  val tokenName: js.UndefOr[String] = js.undefined
}

object LoginWithSSOToken {
  def apply(token: String,
            preAuth: js.UndefOr[Boolean] = js.undefined,
            tokenName: js.UndefOr[String] = js.undefined): LoginWithSSOToken =
    js.Dynamic.literal(token = token, preAuth = preAuth, tokenName = tokenName)
      .asInstanceOf[LoginWithSSOToken]
}