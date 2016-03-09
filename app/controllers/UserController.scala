package controllers

import com.google.inject.Inject
import play.api.mvc.{Controller, Action}
import services.LanguageServiceApi
import play.api.libs.concurrent.Execution.defaultContext
import scala.concurrent.ExecutionContext.Implicits.global


class UserController @Inject()(service:LanguageServiceApi) extends Controller{

  /*  def index = Action.async { implicit request =>
    UserService.listAllUsers map { users =>
      Ok(views.html.index(UserForm.form, users))
    }
  }*/

 def list=Action.async{implicit request=>
  val list = service.getLanguage()

   list.map{
     list => Ok(""+list)
   }

 }



}
