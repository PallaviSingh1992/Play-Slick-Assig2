package controllers

import com.google.inject.Inject
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc.{Controller, Action}
import services.LanguageServiceApi
import play.api.libs.concurrent.Execution.defaultContext
import scala.concurrent.ExecutionContext.Implicits.global

case class Lang(id:Int,name:String,fluency:String)

class LanguageController @Inject()(service:LanguageServiceApi) extends Controller{


  val langForm=Form(
    mapping (
      "id"->Int,
      "name"->nonEmptyText,
      "fluency"->nonEmptyText
    )(Lang.apply)(Lang.unapply)
  )

  def list=Action.async{implicit request=>
    val list = service.getLanguage()

    list.map{
      list => Ok(""+list)
    }

  }






}

