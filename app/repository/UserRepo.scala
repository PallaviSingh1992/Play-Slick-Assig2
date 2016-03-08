package repository


import javax.inject.Inject
import com.google.inject.ImplementedBy
import models.User
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import slick.driver.JdbcProfile
import scala.concurrent.Future

trait UserTable  { self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  class UserTable(tag:Tag) extends Table[User](tag,"user") {
    val id=column[Int]("id", O.AutoInc, O.PrimaryKey)
    val name= column[String]("name", O.SqlType("VARCHAR(200)"))
    val email=column[String]("email")
    val mobile=column[String]("mobile", O.SqlType("VARCHAR(200)"))
    val password=column[String]("password")

    def * = (id, name,email,mobile,password) <>(User.tupled,User.unapply)
  }

  val userTableQuery = TableQuery[UserTable]
}

@ImplementedBy(classOf[UserImpl])
trait  UserRepo extends UserTable {self: HasDatabaseConfigProvider[JdbcProfile] =>
  import driver.api._

  def insert(id:Int,name:String,email:String,mobile:String,password: String): Future[Int] = db.run { userTableQuery += User(id,name,email,mobile,password) }

  def update(id:Int,name:String,email:String,mobile:String,password:String):Future[Int] = db.run { userTableQuery.filter(_.id === id).update(User(id,name,email,mobile,password)) }

  def delete(id: Int): Future[Int] = db.run { userTableQuery.filter(_.id === id).delete }

  def getAll(): Future[List[User]] = db.run { userTableQuery.to[List].result }

}

class UserImpl @Inject() (protected val dbConfigProvider: DatabaseConfigProvider) extends UserTable with HasDatabaseConfigProvider[JdbcProfile]
