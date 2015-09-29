package edu.umkc.cse.RTA

/**
 * Created by mali on 9/26/2015.
 */

  import java.net.URLEncoder


  trait BaseRequest {

    def httpMethod: HTTPMethod
    def fields: Seq[(String, String)]
    def query: Option[String]

    val concat: (Option[String], Option[String]) => Option[String] = { (q1, q2) =>
      val l = List(q1, q2).flatMap( x => x)
      if(l.length == 2) Option(s"${l(0)}&${l(1)}")
      else if(l.length == 1) Option(s"${l(0)}")
      else Option.empty

    }

    val buildQuery: (String, Option[String]) => Option[String] = { (param, value) =>
      value.map(value => s"$param=${URLEncoder.encode(value, "UTF-8")}")
    }


}
