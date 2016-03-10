package controllers

import models.{Award, User}
import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import services.AwardServiceApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.concurrent.Future

@RunWith(classOf[JUnitRunner])
class AwardControllerSpec extends  PlaySpecification with Mockito{

  "Award Controller" should{
    val service=mock[AwardServiceApi]
    val controller=new AwardController(service)

    "list all awards" in new WithApplication() {

      when(service.getAward).thenReturn(Future(List(Award(1,"best coder","zonal level"))))
      val res=call(controller.list,FakeRequest(GET,"/list"))
      status(res) must equalTo(OK)
    }


    "add awards" in new WithApplication() {

      when(service.insertAward(1,"best speaker","zonal level")).thenReturn(Future(1))
      val res=call(controller.add,FakeRequest(GET,"/addaward"))
      status(res) must equalTo(OK)
    }


    "Update awards" in new WithApplication() {

      when(service.updateAward(1,"best speaker","zonal")).thenReturn(Future(1))
      val res=call(controller.update,FakeRequest(GET,"/update"))
      status(res) must equalTo(OK)
    }
  }
}

