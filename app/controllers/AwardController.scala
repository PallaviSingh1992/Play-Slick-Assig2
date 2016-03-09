package controllers

import com.google.inject.Inject
import models.Award
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.AwardServiceApi

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
      list => Ok("" + list)
    }
  }
  def listById(id:Int)=Action.async{implicit request=>
    service.getAward.map {
      list => Ok("" + list.filter(_.id==id))
    }
  }

  def add=Action.async{implicit request =>
    awardForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.insertAward(data.id,data.name,data.details).map(res =>
          Redirect(routes.AwardController.list())
        )
      })

  }

  def delete(id: Int) = Action.async { implicit request =>
    service.deleteAward(id) map { res =>
      Redirect(routes.AwardController.list())
    }
  }

  def update=Action.async{implicit request =>
    awardForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.updateAward(data.id,data.name,data.details).map(res =>
          Redirect(routes.AwardController.list())
        )
      })

  }


}