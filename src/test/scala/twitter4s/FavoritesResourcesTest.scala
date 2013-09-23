package twitter4s
import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.api.impl.FavoritesResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock

@RunWith(classOf[JUnitRunner])
class FavoritesResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = FavoritesResourcesImpl

  mockedTwitter4j.createFavorite(anyLong) returns FakeValuesUsedByMock.status
  mockedTwitter4j.getFavorites(anyString) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.getFavorites(anyString, any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.getFavorites(anyLong) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.getFavorites(anyLong, any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.getFavorites(any[twitter4j.Paging]) returns FakeValuesUsedByMock.responseList[twitter4j.Status]
  mockedTwitter4j.destroyFavorite(anyLong) returns FakeValuesUsedByMock.status

  val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType
  
  "createFavorite" should {
    "call twitter4j createFavorite method" in {
      twitter.createFavorite(1L).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).createFavorite(1L)
    }
  }

  "getFavorites" should {
    "call twitter4j getFavorites method by screen name without paging" in {
      twitter.getFavorites(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).size must equalTo(1)
      there was one(mockedTwitter4j).getFavorites(FakeValuesUsedByMock.userName)
    }

    "call twitter4j getFavorites method by screen name and paging" in {
      twitter.getFavorites(User.isSpecifiedBy(FakeValuesUsedByMock.userName), Paging(2)).size must equalTo(1)
      there was one(mockedTwitter4j).getFavorites(FakeValuesUsedByMock.userName, Paging(2))
    }

    "call twitter4j getFavorites method by user id without paging" in {
      twitter.getFavorites(User.isSpecifiedBy(3L)).size must equalTo(1)
      there was one(mockedTwitter4j).getFavorites(3L)
    }

    "call twitter4j getFavorites method by user id and paging" in {
      twitter.getFavorites(User.isSpecifiedBy(4L), Paging(5)).size must equalTo(1)
      there was one(mockedTwitter4j).getFavorites(4L, Paging(5))
    }

    "call twitter4j getFavorites method by paging only" in {
      twitter.getFavorites(paging= Paging(6)).size must equalTo(1)
      there was one(mockedTwitter4j).getFavorites(Paging(6))
    }
  }

  "destroyFavorites" should {
    "call twitter4j destroyFavorites method" in {
      twitter.destroyFavorite(7L).text must equalTo(FakeValuesUsedByMock.statusText)
      there was one(mockedTwitter4j).destroyFavorite(7L)
    }
  }
}