package org.metaquill.text

abstract class Text {
  var _document:Document
  var _startOffset:Int
  var _endOffset:Int
 
  def startOffset = _startOffset
  def endOffset = _endOffset
  def document = _document

  def asString:String = _document.content.substring(_startOffset, _endOffset)

  def genericSpanFilter(check:Annotation => Boolean):Seq[Annotation] = _document.spans.filter(check)

  def genericSpanFilter[T<:Annotation](cls:Class[T], check:Annotation => Boolean):Seq[T] = _document.spans.filter { 
    ann:Annotation => cls.isInstance(ann) && check(ann)
  }.map { ann:Annotation => ann.asInstanceOf[T] }

  def intersectingSpan(ann:Annotation) = { 
    (ann._startOffset >= _startOffset && ann._startOffset <= _endOffset) ||
    (ann._endOffset >= _endOffset && ann._endOffset <= _endOffset)
  }
  
  def intersectingSpans = genericSpanFilter(intersectingSpan)
  def intersectingSpans[T<:Annotation](cls:Class[T]) = genericSpanFilter(cls, intersectingSpan)

  def congruentSpan(ann:Annotation) = ann._startOffset == _startOffset && ann._endOffset == _endOffset

  def congruentSpans = genericSpanFilter(congruentSpan)
  def congruentSpans[T<:Annotation](cls:Class[T]) = genericSpanFilter(cls, congruentSpan)

  def superSpan(ann:Annotation) = ann._startOffset <= _startOffset && ann._endOffset >= _endOffset

  def superSpans = genericSpanFilter(superSpan)
  def superSpans[T<:Annotation](cls:Class[T]) = genericSpanFilter(cls, superSpan)

  def subSpan(ann:Annotation) = ann._startOffset >= _startOffset && ann._endOffset <= _endOffset

  def subSpans = genericSpanFilter(subSpan)
  def subSpans[T<:Annotation](cls:Class[T]) = genericSpanFilter(cls, subSpan)
}
