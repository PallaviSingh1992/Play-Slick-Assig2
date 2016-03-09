package repository

import com.google.inject.Inject
import com.google.inject.ImplementedBy
import models.Language
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait LanguageTable extends UserTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class LanguageTable(tag:Tag) extends Table[Language](tag,"language") {
    val id=column[Int]("id", O.AutoInc, O.PrimaryKey)
    val name= column[String]("name", O.SqlType("VARCHAR(200)"))
    val fluency=column[String]("fluency", O.SqlType("VARCHAR(200)"))

    def rel = foreignKey("user_id_fk",id, userTableQuery)(_.id)
    def * = (id, name,fluency) <>(Language.tupled, Language.unapply)
  }

  val languageTableQuery = TableQuery[LanguageTable]
}

@ImplementedBy(classOf[LanguageImpl])
trait  LanguageRepo extends LanguageTable {self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  def insert(id:Int,name:String,fluency:String): Future[Int] = db.run { languageTableQuery += Language(id,name,fluency) }

  def update(id:Int,name:String,fluency:String): Future[Int] = db.run { languageTableQuery.filter(_.id === id).update(Language(id,name,fluency)) }

  def delete(id: Int): Future[Int] = db.run { languageTableQuery.filter(_.id === id).delete }

  def getAll(): Future[List[Language]] = db.run { languageTableQuery.to[List].result }

}

class LanguageImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends LanguageTable with HasDatabaseConfigProvider[JdbcProfile]
