package controllers

import com.google.inject.Inject
import models.{Award, Assignment}
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller
import services.AwardServiceApi


class AwardController @Inject()(service:AwardServiceApi) extends Controller {
  val awardForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText,
      "details"->nonEmptyText
    )(Award.apply)(Award.unapply)
  )

}