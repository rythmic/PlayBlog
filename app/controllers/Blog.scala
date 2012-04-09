package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import views._

import models._
import java.util.Date
import anorm.NotAssigned

/**
 * Author: mange
 * Created: 2012-04-09
 */

object Blog extends Controller {

  /**
   * Contact Form definition.
   */
  val entryForm: Form[(String, String)] = Form(
    tuple(
      "title" -> nonEmptyText,
      "content" -> nonEmptyText
    )
  )


  /**
   * Display an empty form.
   */
  def newEntry = Action {
    Ok(html.blog.newEntry(entryForm));
  }

  def show(id:Long, title:String) = Action {
    BlogEntry.findById(id).map { entry =>
      Ok(html.blog.show(entry))
    }.getOrElse(NotFound)
  }

  def create = Action { implicit request =>
    entryForm.bindFromRequest.fold(
      errors => BadRequest(html.blog.newEntry(errors)),
      {
        case (title, content) =>
          val date = new Date()
          val entry =  BlogEntry.create(
            BlogEntry(NotAssigned, title, content, date)
          )
          Ok(html.blog.show(entry))
        }
    )
  }

  /**
   * Display a form pre-filled with an existing Contact.
   */
  /*
  def editForm = Action {
    val existingContact = Contact(
      "Fake", "Contact", Some("Fake company"), informations = List(
        ContactInformation(
          "Personal", Some("fakecontact@gmail.com"), List("01.23.45.67.89", "98.76.54.32.10")
        ),
        ContactInformation(
          "Professional", Some("fakecontact@company.com"), List("01.23.45.67.89")
        ),
        ContactInformation(
          "Previous", Some("fakecontact@oldcompany.com"), List()
        )
      )
    )
    Ok(html.contact.form(contactForm.fill(existingContact)))
  }
*/
  /**
   * Handle form submission.
   */
  /*
  def submit = Action { implicit request =>
    contactForm.bindFromRequest.fold(
      errors => BadRequest(html.contact.form(errors)),
      contact => Ok(html.contact.summary(contact))
    )
  }
  */

}
