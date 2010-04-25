package org.metaquill.text

import java.net.URI
import scala.collection.mutable.ListBuffer

class Document(var _uri:URI, var _content:String) extends Text {
  var _startOffset = 0
  var _endOffset = _content.length
  var _document = this
  var _spans = new ListBuffer[Annotation]()

  def content = _content
  def uri = _uri
  def spans:Seq[Annotation] = _spans.toList
  def spans[T<:Annotation](cls:Class[T]):Seq[T] = 
    _spans.filter{ ann:Annotation => cls.isInstance(ann) }.map{ ann:Annotation => ann.asInstanceOf[T] }
  
}
