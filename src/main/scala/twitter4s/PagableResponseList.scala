package twitter4s

trait PagableResponseList[T] extends ResponseList[T] {
  def hasPrevious:Boolean
  
  def previousCursor:Long
  
  def hasNext:Boolean
  
  def nextCursor:Long
}