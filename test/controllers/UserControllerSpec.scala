package controllers

import models.User
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import services.UserServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class UserControllerSpec extends PlaySpecification with Mockito {

  "User Controller " should{

    val service=mock[UserServiceApi]
    val controller=new UserController(service)

    "list users" in new WithApplication() {

      when(service.getUser).thenReturn(Future(List(User(1,"himani","himani@knoldus.in","98765432","himani"))))

      val res=call(controller.list,FakeRequest(GET,"/list"))

      status(res) must equalTo(OK)
    }


  }
}