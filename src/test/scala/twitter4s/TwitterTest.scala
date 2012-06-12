package twitter4s

import org.specs2.mutable.Specification
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner
import twitter4j.TwitterResponse
import twitter4j.conf.ConfigurationBuilder
import twitter4s.Twitter4sTestHelper._

@RunWith(classOf[JUnitRunner])
class TwitterTest extends Specification {
	"getAccessLevel" should {
	  "unauthenticated access level is None" in {
	    val response = unauthenticated.getDailyTrends()
	    response.accessLevel must equalTo(TwitterResponse.NONE)
	  }
	  
	  "application has read and write access level is READ_WRITE" in {
	    val response = twitter1.verifyCredentials
	    response.getAccessLevel() must equalTo(TwitterResponse.READ_WRITE)
	  }
	  
	  "application has all access level is READ_WRITE_DIRECTMESSAGES" in {
	    val response = rwPrivateMessage.verifyCredentials
	    response.getAccessLevel() must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
	  }
	}
}