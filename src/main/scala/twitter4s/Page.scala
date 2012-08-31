package twitter4s

object Page {
  type PageSpecific = Either[Integer, twitter4j.Paging]
  
  def isSetOn(pageNumber: Int) = {
    Left(pageNumber)
  }
  
  def isControledBy(paging: twitter4j.Paging) = {
    require(paging != null)
    
    Right(paging)
  }
}