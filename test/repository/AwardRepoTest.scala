package repository

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class AwardRepoTest extends Specification{

  def awardRepo(implicit app:Application)=Application.instanceCache[AwardRepo].apply(app)

  "Award Repository" should {
    "get award records" in new WithApplication {
      val res = awardRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert award records" in new WithApplication() {
      val res=awardRepo.insert(4,"best orator","school level")
      val response=Await.result(res,Duration.Inf)
      response===1
    }


  }
}
