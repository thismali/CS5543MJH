package edu.umkc.cse.RTA.excutor

/**
 * Created by mali on 9/26/2015.
 */


import edu.umkc.cse.RTA.{SmsRequest, BaseRequest}





class EndpointLocator[A <: BaseRequest, B <: Endpoint]{

  def endpoint(request: BaseRequest, credential: Auth)(implicit f:  (BaseRequest, Auth) => B): B = f(request, credential)

}

case class EndpointImpl(url: String) extends Endpoint


object Endpoints{


  implicit def SendSmsEndpoint(r: SmsRequest, authentication: Auth)
                : Endpoint = EndpointImpl(s"/Accounts/${authentication.sid}/Messages.json")


}

