package services

import javax.inject.Singleton
import models.User

////@Singleton
////class UserRepo @Inject() {
////
////}
//
//trait UserTable{
//
//  class UserTable(tag: Tag) extends Table[User](tag, "user") {
//    val id = column[Int]("id", O.PrimaryKey, O.AutoInc)
//    val username = column[String]("username", O.SqlType("VARCHAR(100)"))
//    val password = column[String]("password", O.SqlType("VARCHAR(20)"))
//    def * = (id, username, password) <> ((User.apply _).tupled, User.unapply)
//  }
//
//
//
//
//}
