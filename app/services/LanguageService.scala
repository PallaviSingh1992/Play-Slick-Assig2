package services

import com.google.inject.Inject
import models.Language
import repository.LanguageRepo

import scala.concurrent.Future


class LanguageService @Inject()(language:LanguageRepo) {

  def insertLanguage(id:Int,name:String,fluency:String): Future[Int]={
    language.insert(id,name,fluency)
  }

  def updateLanguage(id:Int,name:String,fluency:String): Future[Int]={
    language.update(id,name,fluency)
  }

  def deleteLanguage(id:Int):Future[Int]={
    language.delete(id)
  }

  def getLanguage():Future[List[Language]]={
    language.getAll()
  }
}
