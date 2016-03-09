package services


import com.google.inject.Inject
import models.Award
import repository.AwardRepo

import scala.concurrent.Future

class AwardService @Inject()(award:AwardRepo) {

  def insertAssignment(id:Int,name:String,details:String):Future[Int]={
    award.insert(id,name,details)
  }

  def updateAssignment(id:Int,name:String,details:String):Future[Int]={
    award.update(id,name,details)
  }

  def deleteAssignment(id:Int):Future[Int]={
    award.delete(id)
  }

  def getAssignment:Future[List[Award]]={
    award.getAll()
  }
}
