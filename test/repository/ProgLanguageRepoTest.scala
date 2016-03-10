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
    "get award records" in new WithApplication {
      val res = plangRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert assignment records" in new WithApplication() {
      val res=plangRepo.insert(4,"c#")
      val response=Await.result(res,Duration.Inf)
      plangRepo.delete(4)
      response===1
    }

  }

}
