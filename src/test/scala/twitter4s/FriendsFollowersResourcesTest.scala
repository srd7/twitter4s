package twitter4s

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner
import twitter4s.api.impl.FriendsFollowersResourcesImpl
import twitter4s.mocked.FakeValuesUsedByMock


@RunWith(classOf[JUnitRunner])
class FriendsFollowersResourcesTest extends Specification with TwitterResourcesTestBase {
  type TargetResourcesType = FriendsFollowersResourcesImpl

  mockedTwitter4j.getFriendsIDs(anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getFriendsIDs(anyString, anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getFriendsIDs(anyLong, anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getFollowersIDs(anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getFollowersIDs(anyString, anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getFollowersIDs(anyLong, anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.destroyFriendship(anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.destroyFriendship(anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createFriendship(anyString) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createFriendship(anyLong) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createFriendship(anyString, any[java.lang.Boolean]) returns FakeValuesUsedByMock.user
  mockedTwitter4j.createFriendship(anyLong, any[java.lang.Boolean]) returns FakeValuesUsedByMock.user
  mockedTwitter4j.showFriendship(anyString, anyString) returns FakeValuesUsedByMock.relationShip
  mockedTwitter4j.showFriendship(anyLong, anyLong) returns FakeValuesUsedByMock.relationShip
  mockedTwitter4j.lookupFriendships(any[Array[String]]) returns FakeValuesUsedByMock.responseList[twitter4j.Friendship]
  mockedTwitter4j.lookupFriendships(any[Array[Long]]) returns FakeValuesUsedByMock.responseList[twitter4j.Friendship]
  mockedTwitter4j.updateFriendship(anyString, any[java.lang.Boolean], any[java.lang.Boolean]) returns FakeValuesUsedByMock.relationShip
  mockedTwitter4j.updateFriendship(anyLong, any[java.lang.Boolean], any[java.lang.Boolean]) returns FakeValuesUsedByMock.relationShip
  mockedTwitter4j.getIncomingFriendships(anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getOutgoingFriendships(anyLong) returns FakeValuesUsedByMock.ids
  mockedTwitter4j.getFriendsList(anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getFriendsList(anyLong, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getFollowersList(anyString, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]
  mockedTwitter4j.getFollowersList(anyLong, anyLong) returns FakeValuesUsedByMock.pagableResponseList[twitter4j.User]

  override val twitter = new Twitter(mockedTwitter4j) with TargetResourcesType

  "getFriendsIDs" should {
    "call twitter4j getFriendsIDs method by cursor only" in {
      twitter.friendsFollowers.getFriendsIDs(1L).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getFriendsIDs(1L)
    }

    "call twitter4j getFriendsIDs method by cursor and screen name" in {
      twitter.friendsFollowers.getFriendsIDs(2L, User.isSpecifiedBy(FakeValuesUsedByMock.userName)).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getFriendsIDs(FakeValuesUsedByMock.userName, 2L)
    }

    "call twitter4j getFriendsIDs method by cursor and user id" in {
      twitter.friendsFollowers.getFriendsIDs(4L, User.isSpecifiedBy(3L)).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getFriendsIDs(3L, 4L)
    }
  }
  
  "getFollowersIDs" should {
    "call twitter4j getFollowersIDs method by cursor only" in {
      twitter.friendsFollowers.getFollowersIDs(5L).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getFollowersIDs(5L)
    }

    "call twitter4j getFollowersIDs method by cursor and screen name" in {
      twitter.friendsFollowers.getFollowersIDs(6L, User.isSpecifiedBy(FakeValuesUsedByMock.userName)).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getFollowersIDs(FakeValuesUsedByMock.userName, 6L)
    }

    "call twitter4j getFollowersIDs method by cursor and user id" in {
      twitter.friendsFollowers.getFollowersIDs(8L, User.isSpecifiedBy(7L)).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getFollowersIDs(7L, 8L)
    }
  }
  
  "destroyFriendship" should {
    "call twitter4j destroyFriendship method by screen name" in {
      twitter.friendsFollowers.destroyFriendship(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).destroyFriendship(FakeValuesUsedByMock.userName)
    }

    "call twitter4j destroyFriendship method by user id" in {
      twitter.friendsFollowers.destroyFriendship(User.isSpecifiedBy(9L)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).destroyFriendship(9L)
    }
    
    "throw exception with no target user specific information" in {
      twitter.friendsFollowers.destroyFriendship(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "createFriendship" should {
    "call twitter4j createFriendship method by screen name without follow flag" in {
      twitter.friendsFollowers.createFriendship(User.isSpecifiedBy(FakeValuesUsedByMock.userName)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).createFriendship(FakeValuesUsedByMock.userName)
    }

    "call twitter4j createFriendship method by user id without follow flag" in {
      twitter.friendsFollowers.createFriendship(User.isSpecifiedBy(10L)).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).createFriendship(10L)
    }

    "call twitter4j createFriendship method by screen name and follow flag" in {
      twitter.friendsFollowers.createFriendship(User.isSpecifiedBy(FakeValuesUsedByMock.userName), false).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).createFriendship(FakeValuesUsedByMock.userName, false)
    }

    "call twitter4j createFriendship method by user id and follow flag" in {
      twitter.friendsFollowers.createFriendship(User.isSpecifiedBy(11L), true).screenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).createFriendship(11L, true)
    }
    
    "throw exception when specificUser is set null" in {
      twitter.friendsFollowers.createFriendship(null, null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "showFriendship" should {
    "call twitter4j showFriendship method by screen name" in {
      twitter.friendsFollowers.showFriendship(
        User.isSpecifiedBy(FakeValuesUsedByMock.userName),
        User.isSpecifiedBy(FakeValuesUsedByMock.friendName)).targetUserScreenName must equalTo(FakeValuesUsedByMock.friendName)
      there was one(mockedTwitter4j).showFriendship(FakeValuesUsedByMock.userName, FakeValuesUsedByMock.friendName)
    }

    "call twitter4j showFriendship method by user id" in {
      twitter.friendsFollowers.showFriendship(
        User.isSpecifiedBy(12L),
        User.isSpecifiedBy(13L)
      ).sourceUserScreenName must equalTo(FakeValuesUsedByMock.userName)
      there was one(mockedTwitter4j).showFriendship(12L, 13L)
    }
    
    "throw exception with no source user specific information" in {
      twitter.friendsFollowers.showFriendship(null, User.isSpecifiedBy(14L)) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception with no target user specific information" in {
      twitter.friendsFollowers.showFriendship(User.isSpecifiedBy(15L), null) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception with no parameter" in {
      twitter.friendsFollowers.showFriendship(null, null) must
      throwA[IllegalArgumentException]
    }
    
    "throw exception different user specific information between source and target" in {
      twitter.friendsFollowers.showFriendship(
          User.isSpecifiedBy(FakeValuesUsedByMock.userName),
          User.isSpecifiedBy(16L)) must
      throwA[IllegalArgumentException]
    }
  }
  
  "lookupFriendships" should {
    "call twitter4j lookupFriendships method by screen names list" in {
      twitter.friendsFollowers.lookupFriendships(Users.areSpecifiedBy(Array("user1", "user2"))).size must equalTo(1)
      there was one(mockedTwitter4j).lookupFriendships(Array("user1", "user2"))
    }

    "call twitter4j lookupFriendships method by user id" in {
      twitter.friendsFollowers.lookupFriendships(Users.areSpecifiedBy(Array(17L, 18L, 19L))).size must equalTo(1)
      there was one(mockedTwitter4j).lookupFriendships(Array(17L, 18L, 19L))
    }

    "throw exception when specificUsers are set null" in {
      twitter.friendsFollowers.lookupFriendships(null) must
      throwA[IllegalArgumentException]
    }
  }
  
  "updateFriendship" should {
    "call twitter4j updateFriendship method by screen name" in {
      twitter.friendsFollowers.updateFriendship(
        User.isSpecifiedBy(FakeValuesUsedByMock.friendName),
        true,
        true).targetUserScreenName must equalTo(FakeValuesUsedByMock.friendName)
      there was one(mockedTwitter4j).updateFriendship(FakeValuesUsedByMock.friendName, true, true)
    }

    "call twitter4j updateFriendship method by user id" in {
      twitter.friendsFollowers.updateFriendship(User.isSpecifiedBy(20L), false, false).targetUserScreenName must equalTo(FakeValuesUsedByMock.friendName)
      there was one(mockedTwitter4j).updateFriendship(20L, false, false)
    }
    
    "throw exception when user specific information is null" in {
      twitter.friendsFollowers.updateFriendship(null, true, true) must
      throwA[IllegalArgumentException]
    }
  }
  
  "getIncomingFriendships" should {
    "call twitter4j getIncomingFriendships method" in {
      twitter.friendsFollowers.getIncomingFriendships(21L).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getIncomingFriendships(21L)
    }
  }
  
  "getOutcomingFriendships" should {
    "call twitter4j getOutcomingFriendships method" in {
      twitter.friendsFollowers.getOutgoingFriendships(22L).accessLevel must equalTo(TwitterResponse.READ_WRITE_DIRECTMESSAGES)
      there was one(mockedTwitter4j).getOutgoingFriendships(22L)
    }
  }
  
  "getFriendsList" should {
    "call twitter4j getFriendsList method by screen name" in {
      twitter.friendsFollowers.getFriendsList(User.isSpecifiedBy(FakeValuesUsedByMock.userName), 23L).size must equalTo(50)
      there was one(mockedTwitter4j).getFriendsList(FakeValuesUsedByMock.userName, 23L)
    }

    "call twitter4j getFriendsList method by user id" in {
      twitter.friendsFollowers.getFriendsList(User.isSpecifiedBy(24L), 25L).size must equalTo(50)
      there was one(mockedTwitter4j).getFriendsList(24L, 25L)
    }

    "throw exception when user specific information is null" in {
      twitter.friendsFollowers.getFriendsList(null, 26L) must
      throwA[IllegalArgumentException]
    }
  }
  
  "getFollowersList" should {
    "call twitter4j getFollowersList method by screen name" in {
      twitter.friendsFollowers.getFollowersList(User.isSpecifiedBy(FakeValuesUsedByMock.userName), 27L).size must equalTo(50)
      there was one(mockedTwitter4j).getFollowersList(FakeValuesUsedByMock.userName, 27L)
    }

    "call twitter4j getFollowersList method by user id" in {
      twitter.friendsFollowers.getFollowersList(User.isSpecifiedBy(28L), 29L).size must equalTo(50)
      there was one(mockedTwitter4j).getFollowersList(28L, 29L)
    }

    "throw exception when user specific information is null" in {
      twitter.friendsFollowers.getFollowersList(null, 30L) must
      throwA[IllegalArgumentException]
    }
  }
}