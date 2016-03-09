package controllers

import com.google.inject.Inject
import models.ProgLanguage
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.ProgLanguageApi
import scala.concurrent.Future


class ProgLanguageController @Inject()(service:ProgLanguageApi) extends Controller {
  val progLangForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText
    )(ProgLanguage.apply)(ProgLanguage.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getProg()

    list.map {
      list => Ok("" + list)
    }
  }

  def listById(id:Int)=Action.async{implicit request=>
    service.getProg.map {
      list => Ok("" + list.filter(_.id==id))
    }
  }

  def add=Action.async{implicit request =>
    progLangForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.insertProg(data.id,data.name).map(res =>
          Redirect(routes.ProgLanguageController.list())
        )
      })

  }

  def update=Action.async{implicit request =>
    progLangForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.updateProg(data.id,data.name).map(res =>
          Redirect(routes.ProgLanguageController.list())
        )
      })

  }

  def delete(id: Int) = Action.async { implicit request =>
    service.deleteProg(id: Int) map { res =>
      Redirect(routes.ProgLanguageController.list())
    }
  }



  }
