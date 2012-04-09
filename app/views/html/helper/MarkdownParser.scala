package views.html.helper

import eu.henkelmann.actuarius.ActuariusTransformer
import play.api.templates.Html

/**
 * Author: mange
 * Created: 2012-04-09
 */

package object MarkdownParser {

  val transformer = new ActuariusTransformer()

  def parseMarkdown(string:String) = {
    Html(transformer(string))
  }

}