package repository

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import play.api.Application
import play.api.test.WithApplication
import scala.concurrent.Await
import scala.concurrent.duration.Duration

@RunWith(classOf[JUnitRunner])
class AssignmentRepoTest extends Specification{

  def assigRepo(implicit app:Application)=Application.instanceCache[AssignmentRepo].apply(app)

  "Assignment Repository" should {

    "get assignment records" in new WithApplication {
      val res = assigRepo.getAll()
      val response = Await.result(res, Duration.Inf)
      response.head.id ===1
    }

    "insert assignment records" in new WithApplication() {
      val res=assigRepo.insert(4,"play",5,"no remark")
      val response=Await.result(res,Duration.Inf)
      assigRepo.delete(4)
      response===1
    }

    "delete assignment record" in new WithApplication() {
      assigRepo.insert(2,"play",5,"no remark")
      val res=assigRepo.delete(2)
      val response=Await.result(res,Duration.Inf)
      response===1
    }
  }

}
