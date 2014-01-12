package twitter4s
/*
 * Copyright (C) 2012 Shinsuke Abe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import api.impl._
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken
import twitter4j.RateLimitStatusListener
import twitter4j.TwitterFactory
import auth.ConsumerKey
import twitter4s.api.HelpResources
import twitter4s.api.SpamReportingResources

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
// TODO Twitter4J 3.0.5対応
case class Twitter(twitter4jObj: twitter4j.Twitter) extends TwitterBase {
  /* TwitterBase method */
  /**
   * {@inheritDoc}
   */
  def screenName: String = {
    twitter4jObj.getScreenName()
  }
  
  /**
   * {@inheritDoc}
   */
  def id: Long = {
    twitter4jObj.getId()
  }
  
  /**
   * {@inheritDoc}
   */
  def addRateLimitStatusListener(listener: RateLimitStatusListener) {
    twitter4jObj.addRateLimitStatusListener(listener)
  }
  
  /**
   * {@inheritDoc}
   */
  def authorization: twitter4j.auth.Authorization = {
    twitter4jObj.getAuthorization()
  }
  
  /**
   * {@inheritDoc}
   */
  def configuration: twitter4j.conf.Configuration = {
    twitter4jObj.getConfiguration()
  }
  
  /**
   * {@inheritDoc}
   */
  def shotdown {
    twitter4jObj.shutdown()
  }
  
  /* OAuthSupport */
  /**
   * {@inheritDoc}
   */
  def setOAuthConsumer(consumerKey: String, consumerSecret: String) {
    twitter4jObj.setOAuthConsumer(consumerKey, consumerSecret)
  }
  
  /**
   * {@ineritDoc}
   */
  def setOAuthConsumer(consumerKey: ConsumerKey) {
    twitter4jObj.setOAuthConsumer(consumerKey.consumerKey, consumerKey.consumerSecret)
  }
  
  /**
   * {@inheritDoc}
   */
  // TODO でフォルトパターンの例外スロー
  def getOAuthRequestToken(callbackURL: String = null, xAuthAccessType: String = null): RequestToken = {
    (Option(callbackURL), Option(xAuthAccessType)) match {
      case (None, None) => twitter4jObj.getOAuthRequestToken()
      case (Some(callbackURL), None) => twitter4jObj.getOAuthRequestToken(callbackURL)
      case (Some(callbackURL), Some(xAuthAccessType)) => twitter4jObj.getOAuthRequestToken(callbackURL, xAuthAccessType)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  // TODO デフォルトパターンの例外スロー
  def getOAuthAccessToken(
      oauthVerifier: String = null,
      requestToken: RequestToken = null,
      screenName: String = null,
      password: String = null): AccessToken = {
    (Option(oauthVerifier), Option(requestToken), Option(screenName), Option(password)) match {
      case (None, None, None, None) => twitter4jObj.getOAuthAccessToken()
      case (Some(oauthVerifier), None, None, None) => twitter4jObj.getOAuthAccessToken(oauthVerifier)
      case (Some(oauthVerifier), Some(requestToken), None, None) => twitter4jObj.getOAuthAccessToken(requestToken, oauthVerifier)
      case (None, Some(requestToken), None, None) => twitter4jObj.getOAuthAccessToken(requestToken)
      case (None, None, Some(screenName), Some(password)) => twitter4jObj.getOAuthAccessToken(screenName, password)
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def setOAuthAccessToken(accessToken: AccessToken) {
    twitter4jObj.setOAuthAccessToken(accessToken)
  }


  // TODO 追加 Twitter4Jではreturn thisしてる -> DSLにも有効活用可能か
  // TODO 各リソースのmixinはTwitterBaseに戻さないと行けなくなるかも、だけど。。。
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  TimelinesResources timelines();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  TweetsResources tweets();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  SearchResource search();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  DirectMessagesResources directMessages();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  FriendsFollowersResources friendsFollowers();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  UsersResources users();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  SuggestedUsersResources suggestedUsers();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  FavoritesResources favorites();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  ListsResources list();
  //
  //  /**
  //   * @since Twitter4J 3.0.4
  //   */
  //  SavedSearchesResources savedSearches();

    /**
     * @since Twitter4S 2.1.0
     */
    def placesGeo = PlacesGeoResourcesBinder(this)

    /**
     * @since Twitter4S 2.1.0
     */
    def trends = TrendsResourcesBinder(this)

    /**
     * @since Twitter4S 2.1.0
     */
    def spamReporting = SpamReportingResourcesBinder(this)

    /**
     * @since Twitter4S 2.1.0
     */
    def help = HelpResourcesBinder(this)
}

/**
 * @author Shinsuke Abe - mao.instantlife at gmail.com
 */
object Twitter {
  /**
   * Create Twitter4S object from twitter4j factory.
   * 
   * @param conf (optional) Configuration object or configuration strings for create factory.
   * @param auth (optional) AccessToken or Authorization object for create twitter4j object.
   * @return twitter4s.Twitter
   * @since Twitter4S 1.0.0
   */
  def apply(conf: Configuration.SpecificInfo = null, auth: AuthorizationInformation.SpecificType = null) = {
    val factory4j = getTwitterFactory4j(Option(conf))
    buildTwitter4sObject(getTwitter4jInstance(factory4j, Option(auth)))
  }
  
  /**
   * Create Twitter4S object from twitter4j factory.
   * 
   * @param consumerKey(required) Consumer key object for create factory.
   * @param accessToken(required) Access token object for create twitter4j object.
   * @return twitter4s.Twitter
   * @since Twitter4S 1.0.0
   */
  def apply(consumerKey: ConsumerKey, accessToken: AccessToken) = {
    buildTwitter4sObject(buildTwitter4jInstance(consumerKey, accessToken))
  }
  
  private def buildTwitter4sObject(twitter4jObj: twitter4j.Twitter) = {
    new Twitter(twitter4jObj) with HelpResourcesImpl
                              with TrendsResourcesImpl
                              with PlaceGeoResourcesImpl
                              with TimelinesResourcesImpl
                              with SearchResourcesImpl
                              with UsersResourcesImpl
                              with SuggestedUsersResourcesImpl
                              with SavedSearchesResourcesImpl
                              with DirectMessagesResourcesImpl
                              with FavoritesResourcesImpl
                              with FriendsFollowersResourcesImpl
                              with TweetsResourcesImpl
                              with ListsResourcesImpl
                              with SpamReportingResourcesImpl
  }
  
  /**
   * Create TwitterFactory object from configurations.
   * 
   * @param conf (optional) Configuration object or configuration strings.
   * @return twitter4j.TwitterFactory
   * @since Twitter4S 1.0.0
   */
  private def getTwitterFactory4j(conf: Option[Configuration.SpecificInfo]) = {
    conf match {
      case None => new TwitterFactory()
      case Some(conf) => conf match {
        case Left(conf) => new TwitterFactory(conf)
        case Right(configTreePath) => new TwitterFactory(configTreePath)
      }
    }
  }
  
  /**
   * Create Twitter4J object from factory.
   * 
   * @param factory4j (required) TwitterFactory is created by configuration.
   * @param auth (optinal) Authorization or AccessToken object.
   * @return twitter4j.Twitter
   * @since Twitter4S 1.0.0
   */
  private def getTwitter4jInstance(factory4j: TwitterFactory, auth: Option[AuthorizationInformation.SpecificType]) = {
    auth match {
      case None => factory4j.getInstance()
      case Some(auth) => auth match {
        case Right(accessToken) => factory4j.getInstance(accessToken)
        case Left(auth) => factory4j.getInstance(auth)
      }
    }
  }

  /**
   * Create Twitter4J instance
   *
   * @param consumerKey(required) Consumer key object for create factory.
   * @param accessToken(required) Access token object for create twitter4j object.
   * @return twitter4j.Twitter
   * @since Twitter4S 2.1.0
   */
  def buildTwitter4jInstance(consumerKey: ConsumerKey, accessToken: AccessToken) = {
    val twitter4jObj = getTwitter4jInstance(
      getTwitterFactory4j(None),
      None)
    twitter4jObj.setOAuthConsumer(consumerKey.consumerKey, consumerKey.consumerSecret)
    twitter4jObj.setOAuthAccessToken(accessToken)
    twitter4jObj
  }
}