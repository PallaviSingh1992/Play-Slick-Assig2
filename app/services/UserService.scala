package services


import com.google.inject.{ImplementedBy, Inject}
import models.User
import repository.UserRepo

import scala.concurrent.Future

@ImplementedBy(classOf[UserService])
trait userServiceApi{
  def insertUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]
  def updateUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]
  def deleteUser(id:Int):Future[Int]
  def getUser:Future[List[User]]
}

class UserService @Inject()(user:UserRepo) {

  def insertUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]={
    user.insert(id,name,email,mobile,password)
  }

  def updateUser(id:Int,name:String,email:String,mobile:String,password:String):Future[Int]={
    user.update(id,name,email,mobile,password  )
  }

  def deleteUser(id:Int):Future[Int]={
    user.delete(id)
  }

  def getUser:Future[List[User]]={
    user.getAll()
  }
}