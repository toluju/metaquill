package org.metaquill.text

import scala.collection.mutable.ListBuffer

class Tokenizer {
  def tokenize(document:Document) = {
    var characters = document.content.toCharArray
    var tokens = new ListBuffer[Token]
    var offset = 0

    for (i <- 0 until characters.length) {
      var c = characters(i)

      if (splitChar(c)) {
        if (i > offset) {
          new Token(offset, i, document).attach
        }

        if (skipChar(c)) {
          offset = i + 1
        }
        else {
          offset = i
        }
      }
    }

    if (offset < characters.length) {
      new Token(offset, characters.length, document).attach
    }
  }

  val puncChars = Array(',', '.', '\'', '"', '!', '?', '-') 

  def splitChar(c:Char) = {
    if (Character.isWhitespace(c)) {
      true
    }
    else if (puncChars.contains(c)) {
      true
    }
    else {
      false
    }
  }

  def skipChar(c:Char) = {
    Character.isWhitespace(c)
  }
}
