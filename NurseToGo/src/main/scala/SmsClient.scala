import edu.umkc.cse.RTA.{BaseRequest, BaseResponse}
import edu.umkc.cse.RTA.excutor.{Executor, Auth}

import scala.concurrent.Future

/**
 * Created by mali on 9/26/2015.
 */
object SmsClient {

  def apply(sid: String, authToken: String) = new SmsClient(Auth(sid, authToken))
}


class SmsClient(authentication: Auth) {

  def execute[A <: BaseRequest, B <: BaseResponse] (request: A) (implicit executor: Executor[A, B]): Future[B] = {
    executor.execute(request) (authentication)
  }


}
