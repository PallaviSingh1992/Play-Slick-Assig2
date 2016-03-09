package services

import com.google.inject.Inject
import models.Assignment
import repository.AssignmentRepo

import scala.concurrent.Future


class AssignmentService @Inject()(assignment:AssignmentRepo) {

  def insertAssignment(id:Int,name:String,marks:Int,remarks:String):Future[Int]={
    assignment.insert(id,name,marks,remarks)
  }

  def updateAssignment(id:Int,name:String,marks:Int,remarks:String):Future[Int]={
    assignment.update(id,name,marks,remarks)
  }

  def deleteAssignment(id:Int):Future[Int]={
    assignment.delete(id)
  }

  def getAssignment:Future[List[Assignment]]={
    assignment.getAll()
  }
}
