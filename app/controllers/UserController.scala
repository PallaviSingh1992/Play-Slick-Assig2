package controllers

import com.google.inject.Inject
import models.User
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.Controller
import services.UserServiceApi

class UserController @Inject()(service:UserServiceApi) extends Controller {

  val userForm = Form(
    mapping(
      "id" ->number,
      "name" ->nonEmptyText,
      "email"->nonEmptyText,
      "mobile"->nonEmptyText,
      "password"->nonEmptyText
    )(User.apply)(User.unapply)
  )

}