package services

import com.google.inject.{ImplementedBy, Inject}
import models.Language
import repository.LanguageRepo

import scala.concurrent.Future

@ImplementedBy(classOf[LanguageService])
trait LanguageServiceApi {

  def insertLanguage(id: Int, name: String, fluency: String): Future[Int]
  def updateLanguage(id: Int, name: String, fluency: String): Future[Int]
  def deleteLanguage(id: Int): Future[Int]
  def getLanguage(): Future[List[Language]]
}

class LanguageService @Inject()(language: LanguageRepo) extends LanguageServiceApi{

  def insertLanguage(id: Int, name: String, fluency: String): Future[Int] = {
    language.insert(id, name, fluency)
  }

  def updateLanguage(id: Int, name: String, fluency: String): Future[Int] = {
    language.update(id, name, fluency)
  }

  def deleteLanguage(id: Int): Future[Int] = {
    language.delete(id)
  }

  def getLanguage(): Future[List[Language]] = {
    language.getAll()
  }
}
