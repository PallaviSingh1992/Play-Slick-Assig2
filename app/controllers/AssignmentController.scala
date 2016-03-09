package controllers

import com.google.inject.Inject
import models.Assignment
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller
import services.AssignmentServiceApi


class AssignmentController @Inject()(service:AssignmentServiceApi) extends Controller {
  val assigForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "marks"->number(),
      "remarks"->nonEmptyText
    )(Assignment.apply)(Assignment.unapply)
  )

}
