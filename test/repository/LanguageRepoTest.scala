package repository

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication

import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class LanguageRepoTest extends Specification{

  def langRepo(implicit app:Application)=Application.instanceCache[LanguageRepo].apply(app)

  "Language Repository" should {
    "get language records" in new WithApplication {
      val res = langRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert language records" in new WithApplication() {
      val res=langRepo.insert(4,"french","basic")
      val response=Await.result(res,Duration.Inf)
      langRepo.delete(4)
      response===1
    }

  }

}
