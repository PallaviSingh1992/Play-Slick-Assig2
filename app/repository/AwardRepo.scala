package repository


import javax.inject.Inject
import javax.xml.soap.Detail
import com.google.inject.ImplementedBy
import models.Award
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait AwardTable extends UserTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class AwardTable(tag:Tag) extends Table[Award](tag,"award") {
    val id=column[Int]("id", O.AutoInc, O.PrimaryKey)
    val name= column[String]("name", O.SqlType("VARCHAR(200)"))
    val details= column[String]("details", O.SqlType("VARCHAR(200)"))

    def rel = foreignKey("user_id_fk",id, userTableQuery)(_.id)
    def * = (id, name,details) <>(Award.tupled, Award.unapply)
  }

  val awardTableQuery = TableQuery[AwardTable]
}

@ImplementedBy(classOf[AwardImpl])
trait  AwardRepo extends AwardTable {self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  def insert(id:Int,name:String,details:String): Future[Int] = db.run { awardTableQuery += Award(id,name,details) }

  def update(id:Int,name:String,details:String): Future[Int] = db.run { awardTableQuery.filter(_.id === id).update(Award(id,name,details)) }

  def delete(id: Int): Future[Int] = db.run { awardTableQuery.filter(_.id === id).delete }

  def getAll(): Future[List[Award]] = db.run { awardTableQuery.to[List].result }

}

class AwardImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends AwardTable with HasDatabaseConfigProvider[JdbcProfile]

