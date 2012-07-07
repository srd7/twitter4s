package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class LegalMethodsTest extends Specification {

  "getTermsOfService" should {
    "get terms of service resource" in {
      twitter1.getTermsOfService must not equalTo(null)
    }
  }
  
  "getPrivacyPolicy" should {
    "get privacy policy of service resource" in {
      twitter1.getPrivacyPolicy must not equalTo(null)
    }
  }
}