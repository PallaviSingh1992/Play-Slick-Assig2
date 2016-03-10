package repository
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class ProgLanguageRepoTest extends Specification{

  def plangRepo(implicit app:Application)=Application.instanceCache[ProgLanguageRepo].apply(app)

  "Programming language  Repository" should {
    "get programming language records" in new WithApplication {
      val res = plangRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert programming language records" in new WithApplication() {
      val res=plangRepo.insert(4,"c#")
      val response=Await.result(res,Duration.Inf)
      response===1
    }

    "delete programming language records" in new WithApplication() {

      val res=plangRepo.delete(1)
      val response=Await.result(res,Duration.Inf)
      response === 1
    }

    "update programming language records" in new WithApplication(){
      val res=plangRepo.update(1,"java")
      val response=Await.result(res,Duration.Inf)
      response === 1

    }

    "delete programming language record" in new WithApplication() {
      val res=plangRepo.delete(1)
      val response=Await.result(res,Duration.Inf)
      response===1
    }

  }

}
