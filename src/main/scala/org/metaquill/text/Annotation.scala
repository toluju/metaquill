package org.metaquill.text

class Annotation(var _startOffset:Int, var _endOffset:Int, var _document:Document) extends Text {
  def attach = _document._spans += this
}
