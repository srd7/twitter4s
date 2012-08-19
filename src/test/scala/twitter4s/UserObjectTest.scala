package twitter4s
import twitter4s._
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class UserObjectTest extends Specification {

  "isSpecifiedBy" should {
    "throw exception when null screenName is set" in {
      User.isSpecifiedBy(null) must
      throwA[IllegalArgumentException]
    }
    
    "get Left(screenName) when screenName is set" in {
      User.isSpecifiedBy(id1.screenName) must
      equalTo(Left(id1.screenName))
    }
    
    "get Right(userId) when userId is set" in {
      User.isSpecifiedBy(id1.id) must
      equalTo(Right(id1.id))
    }
  }
}