package services


import org.mockito.Mockito._
import org.specs2.mock.Mockito
import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import repository.AwardRepo
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

@RunWith(classOf[JUnitRunner])
class AwardServiceTest extends PlaySpecification with Mockito{

  "Award Service" should {

    val service=mock[AwardRepo]
    val controller=new AwardService(service)

    "get award" in new WithApplication() {

    }
  }

}
