package controllers

import com.google.inject.Inject
import models.ProgLanguage
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller
import services.progLanguageApi


class ProgLanguageController @Inject()(service:progLanguageApi) extends Controller {
  val progLangForm = Form(
    mapping(
      "id" -> number,
      "name" ->nonEmptyText
    )(ProgLanguage.apply)(ProgLanguage.unapply)
  )

}