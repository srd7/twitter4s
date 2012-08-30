package twitter4s
import java.io.File
import java.io.InputStream

object ImageResource {
  type SpecificResource = Either[InputStream, File]
  
  def isAssigned(imageStream: InputStream): SpecificResource = {
    require(imageStream != null)
    
    Left(imageStream)
  }
  
  def isAssigned(imageFile: File): SpecificResource = {
    require(imageFile != null)
    
    Right(imageFile)
  }
}