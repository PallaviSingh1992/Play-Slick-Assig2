package controllers

import com.google.inject.Inject
import models.Assignment
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.AssignmentServiceApi

import scala.concurrent.Future


class AssignmentController @Inject()(service:AssignmentServiceApi) extends Controller {
  val assigForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "marks"->number(),
      "remarks"->nonEmptyText
    )(Assignment.apply)(Assignment.unapply)
  )

  def list = Action.async { implicit request =>
    val list = service.getAssignment

    list.map {
      list => Ok("" + list)
    }
  }

  def add=Action.async{implicit request =>
    assigForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.insertAssignment(data.id,data.name,data.marks,data.remarks).map(res =>
          Redirect(routes.AssignmentController.list())
        )
      })

  }

  def update=Action.async{implicit request =>
    assigForm.bindFromRequest.fold(
      // if any error in submitted data
      errorForm => Future.successful(Ok("success")),
      data => {
        service.updateAssignment(data.id,data.name,data.marks,data.remarks).map(res =>
          Redirect(routes.AssignmentController.list())
        )
      })

  }

  def delete(id: Int) = Action.async { implicit request =>
    service.deleteAssignment(id: Int) map { res =>
      Redirect(routes.AssignmentController.list())
    }
  }


}
