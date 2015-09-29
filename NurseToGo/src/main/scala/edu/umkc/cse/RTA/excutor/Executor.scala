package edu.umkc.cse.RTA.excutor

import akka.actor.ActorSystem
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import edu.umkc.cse.RTA.{GETRequest, BaseRequest, BaseResponse, TwilioConfig}
import spray.can.Http
import spray.client.pipelining._
import spray.http._
import spray.json.DefaultJsonProtocol

import scala.concurrent.Future
import scala.concurrent.duration._


/**
 * Created by mali on 9/26/2015.
 */

trait Executor[A <: BaseRequest, B <: BaseResponse] extends TwilioConfig with DefaultJsonProtocol {


  implicit val system = ActorSystem("NurseToGo-scala")
  import system.dispatcher
  implicit val timeout = Timeout(10.seconds)

  val pipeline: Future[SendReceive] =
    for (
      Http.HostConnectorInfo(connector, _) <-
      IO(Http) ? Http.HostConnectorSetup(endpoint, port = 8095, sslEncryption = true)
    ) yield sendReceive(connector)

  val headers: (Auth) => RequestTransformer = { authentication =>
    addHeader("X-Twilio-Client", s"scala-$version") ~>
      addHeader("User-Agent", s"NurseToGo-scala-$version") ~>
      addHeader("Accept-Charset", "utf-8") ~>
      addCredentials(BasicHttpCredentials(authentication.sid, authentication.authToken))
  }

  def execute(request: A)(authentication: Auth): Future[B]

  def performRequest(request: A, authentication: Auth)(implicit locator: (A, Auth) => Endpoint): HttpRequest = {
    val endpoint = locator(request, authentication)
    val url = request.query.map(query => s"/$TWILIO_DEFAULT_VERSION${endpoint.url}$query").getOrElse(s"/$TWILIO_DEFAULT_VERSION${endpoint.url}")

    request.httpMethod match {
      case GETRequest => Get(url) ~> headers(authentication)
      case _ => Post(url, FormData(request.fields)) ~> headers(authentication)
    }

  }
}
