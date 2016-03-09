package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Action, Controller}
import services.UserServiceApi
import play.api.i18n.Messages.Implicits._
import play.api.Play.current
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

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
  /*def login =Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        Redirect(routes.LoginController.getLogin())
      },
      userData => {
        val res= user.getUser.map{
          list=>list.filter(res=>res.email==userData.email && res.password==userData.password)
        }
        val id=user.getId(userData.email).map{value=>value}
        println(id)
        //user.getId(userData.email).map{det=>Redirect(routes.LanguageController.list()).withSession("email"->userData.email)}
       if(res==true)
          Redirect(routes.LanguageController.list()).withSession("email"->userData.email)
       else Redirect(routes.AwardController.list())
      }
    )
  }*/


  def login = Action.async { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
       Future{ Redirect(routes.LoginController.getLogin())}
      },
      userData => {
        val isUser = user.getUserByEmail(userData.email)
        isUser.map{ isuser =>
          println(""+isuser)
          if(isuser.isDefined)
            {
              println(""+isuser.get.id)
              if(isuser.get.password == userData.password)
                {
                  println(""+isuser.get.id)
                Redirect(routes.AwardController.listById(isuser.get.id)).withSession("id"->(isuser.get.id).toString,"email"->isuser.get.email)
                }
              else {
                  Redirect(routes.LoginController.getLogin)
              }

            }
          else{
            Redirect(routes.LoginController.getLogin)
          }
        }
      }
    )
  }

//  def account=Action { request =>
//    request.session.get("email").map { email => Ok(views.html.account(email)) }.getOrElse {
//      Unauthorized("You are not Logged In.")
//    }
//  }

}
