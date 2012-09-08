package twitter4s

object Configuration {
  type SpecificInfo = Either[twitter4j.conf.Configuration, String]
  
  def isSpecifiedBy(conf: twitter4j.conf.Configuration) = {
    require(conf != null)
    Left(conf)
  }
  
  def isSpecifiedBy(configTreePath: String) = {
    require(configTreePath != null)
    Right(configTreePath)
  }
}