package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class UserObjectTest extends Specification {

  "isSpecifiedBy" should {
    "throw exception when null screenName is set" in {
      User.isSpecifiedBy(null) must
      throwA[IllegalArgumentException]
    }
  }
}