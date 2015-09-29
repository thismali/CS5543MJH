package edu.umkc.cse.RTA

/**
 * Created by mali on 9/26/2015.
 */


case class SmsRequest(from: String,
                      to: String,
                      body: Option[String] = None,
                      mediaUrl: Option[String] = None,
                      statusCallBack: Option[String] = None)
  extends BaseRequest{

  override def httpMethod: HTTPMethod = POSTRequest

  override def fields: Seq[(String, String)] =
    Seq(
      Option("From" -> from),
      Option("To" -> to),
      body.map(x => "Body" -> x),
      mediaUrl.map(x => "MediaUrl" -> x),
      statusCallBack.map(x => "StatusCallback" -> x)
    ).flatMap(x => x)

  override def query: Option[String] = Option.empty
}
