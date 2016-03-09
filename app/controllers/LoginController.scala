package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.UserServiceApi

case class UserData(email:String,password:String)

class LoginController @Inject() (user:UserServiceApi)extends Controller {

  val userForm = Form(
    mapping(
      "email"->nonEmptyText,
      "password"->nonEmptyText
    )(UserData.apply)(UserData.unapply)
  )

  def getLogin =Action {implicit request=>
    Ok(views.html.login(userForm))
  }
  def login =Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.ApplicationController.getLogin())
      },
      userData => {
        val res= user.getUser.map{
            list=>Ok(""+list.filter(res=>res.email==userData.email && res.password==userData.password))
          }
        if(res==true)
          Redirect(routes.LanguageController.list()).withSession("email"->userData.email)
        else Redirect(routes.ApplicationController.getLogin())
      }
    )
  }

  def account=Action { request =>
    request.session.get("email").map { email => Ok(views.html.account(email)) }.getOrElse {
      Unauthorized("You are not Logged In.")
    }
  }

}
