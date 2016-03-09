package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Controller, Action}
import services.LanguageServiceApi
import scala.concurrent.ExecutionContext.Implicits.global
import models.Language

import scala.concurrent.Future

class LanguageController @Inject()(service:LanguageServiceApi) extends Controller {

  val langForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "fluency"->nonEmptyText
    )(Language.apply)(Language.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getLanguage()

    list.map {
      list => Ok("" + list)
    }
  }

    def add=Action.async{implicit request =>
      langForm.bindFromRequest.fold(
        // if any error in submitted data
        errorForm => Future.successful(Ok("success")),
      data => {
        service.insertLanguage(data.id,data.name,data.fluency).map(res =>
          Redirect(routes.LanguageController.list())
        )
      })

    }

  def delete(id: Int) = Action.async { implicit request =>
    service.deleteLanguage(id) map { res =>
      Redirect(routes.LanguageController.list())
    }

    def update(id: Int,name:String,fluency:String) = Action.async { implicit request =>
      service.updateLanguage(id, name, fluency) map { res =>
        Redirect(routes.LanguageController.list())
      }

    }


}
