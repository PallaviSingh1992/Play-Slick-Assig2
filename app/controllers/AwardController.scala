package controllers

import com.google.inject.Inject
import models.Award
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.AwardServiceApi
import play.api.i18n.Messages.Implicits._
import play.api.Play.current

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class AwardController @Inject()(service:AwardServiceApi) extends Controller {
  val awardForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "details"->nonEmptyText
    )(Award.apply)(Award.unapply)
  )


  def list = Action.async { implicit request =>
    val list = service.getAward
    list.map {
      list => Ok(views.html.awards(list,awardForm))
    }
  }
  def listById(id:Int)=Action.async{implicit request=>
    service.getAwardById(id).map{list=>Ok(views.html.awards(list.toList,awardForm))}
  }

  def add=Action.async{implicit request =>
    awardForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        val id:Int=request.session.get("id").get.toInt
        service.insertAward(id,data.name,data.details).map(res =>
          Redirect(routes.AwardController.listById(id))
        )
      })

  }

  def delete(id: Int) = Action.async { implicit request =>
    val id:Int=request.session.get("id").get.toInt
    service.deleteAward(id) map { res =>
      Redirect(routes.AwardController.listById(id))
    }
  }

  def update=Action.async{implicit request =>
    awardForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        val id:Int=request.session.get("id").get.toInt
        service.updateAward(data.id,data.name,data.details).map(res =>
          Redirect(routes.AwardController.listById(id))
        )
      })

  }


}