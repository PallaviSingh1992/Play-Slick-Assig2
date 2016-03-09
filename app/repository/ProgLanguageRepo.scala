package repository

import com.google.inject.Inject
import com.google.inject.ImplementedBy
import models.ProgLanguage
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait ProgLanguageTable extends UserTable{ self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class ProgLanguageTable(tag:Tag) extends Table[ProgLanguage](tag,"proglanguage") {
    val id=column[Int]("id", O.AutoInc, O.PrimaryKey)
    val name= column[String]("name", O.SqlType("VARCHAR(200)"))

    def rel = foreignKey("user_id_fk",id, userTableQuery)(_.id)
    def * = (id, name) <>(ProgLanguage.tupled, ProgLanguage.unapply)
  }

  val progLanguageTableQuery = TableQuery[ProgLanguageTable]
}

//@ImplementedBy(classOf[ProgLanguageImpl])
class  ProgLanguageRepo @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends ProgLanguageTable with HasDatabaseConfigProvider[JdbcProfile] {

  import driver.api._

  def insert(id:Int,name:String): Future[Int] = db.run { progLanguageTableQuery += ProgLanguage(id,name) }

  def update(id:Int,name:String): Future[Int] = db.run { progLanguageTableQuery.filter(_.id === id).update(ProgLanguage(id,name)) }

  def delete(id: Int): Future[Int] = db.run { progLanguageTableQuery.filter(_.id === id).delete }

  def getAll(): Future[List[ProgLanguage]] = db.run { progLanguageTableQuery.to[List].result }

}

//class ProgLanguageImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends ProgLanguageTable with HasDatabaseConfigProvider[JdbcProfile]
