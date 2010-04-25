package org.metaquill.text

object Main { 
  def main(args:Array[String]) = {
    var content = new StringBuilder
    scala.io.Source.fromFile(args(0)).foreach { c => content.append(c) }
    var tokenizer = new Tokenizer
    var document = new Document(null, content.toString)
    tokenizer.tokenize(document)

    document.spans(classOf[Token]).foreach { t => println("\"" + t.asString + "\"") }
  }
}
