package edu.umkc.cse.RTA

/**
 * Created by mali on 9/26/2015.
 */
trait HTTPMethod {

  def method: String


}

case object POSTRequest extends HTTPMethod{
  val method: String = "POST"
}

case object GETRequest extends HTTPMethod{
  val method: String = "GET"
}
