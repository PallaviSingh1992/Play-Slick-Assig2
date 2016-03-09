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
  /* def addUser() = Action.async { implicit request =>
    UserForm.form.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok(views.html.index(errorForm, Seq.empty[User]))),
      data => {
        val newUser = User(0, data.name, data.password, data.email)
        UserService.addUser(newUser).map(res =>
          Redirect(routes.ApplicationController.getLogin())

        )
      })
  }
 */
    langForm.bindFromRequest.fold(
      errorform => Future.successful(Ok(views.html.award()))
    )
  }
}
