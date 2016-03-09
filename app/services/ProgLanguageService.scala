package services

import com.google.inject.{ImplementedBy, Inject}
import models.ProgLanguage
import repository.ProgLanguageRepo

import scala.concurrent.Future

@ImplementedBy(classOf[ProgLanguageService])
trait ProgLanguageApi{
  def insertProg(id:Int,name:String): Future[Int]
  def updateProg(id:Int,name:String): Future[Int]
  def deleteProg(id:Int):Future[Int]
  def getProg():Future[List[ProgLanguage]]
}

class ProgLanguageService @Inject()(progLanguage:ProgLanguageRepo) {

  def insertProg(id:Int,name:String): Future[Int]={
    progLanguage.insert(id,name)
  }

  def updateProg(id:Int,name:String): Future[Int]={
    progLanguage.update(id,name)
  }

  def deleteProg(id:Int):Future[Int]={
    progLanguage.delete(id)
  }

  def getProg():Future[List[ProgLanguage]]={
    progLanguage.getAll()
  }

}
