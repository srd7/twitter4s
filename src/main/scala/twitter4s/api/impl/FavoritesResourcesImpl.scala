package twitter4s.api.impl

import twitter4s.ResponseList
import twitter4s.api.FavoritesResources
import scala.util.Either
import twitter4s.Twitter
import twitter4s.Page
import twitter4s.Status

trait FavoritesResourcesImpl extends FavoritesResources {
  self: Twitter =>
    
  /**
   * {@inhritDoc}
   */
  def getFavorites(
      id: String = null,
      page: Page.PageSpecific = null): ResponseList[twitter4j.Status] = {
    (Option(id), Option(page)) match {
      case (None, None) => twitter4jObj.getFavorites()
      case (Some(id), None) => twitter4jObj.getFavorites(id)
      case (Some(id), Some(page)) => page match {
        // TODO getFavoritesからpageNumberのパターンがなくなった
        case Left(pageNumber) => null//twitter4jObj.getFavorites(id, pageNumber)
        case Right(paging) => twitter4jObj.getFavorites(id, paging)
      }
      case (None, Some(page)) => page match {
        // TODO getFavoritesからpageNumberのパターンがなくなった
        case Left(pageNumber) => null//twitter4jObj.getFavorites(pageNumber)
        case Right(paging) => twitter4jObj.getFavorites(paging)
      }
    }
  }
  
  /**
   * {@inheritDoc}
   */
  def createFavorite(id: Long): Status = {
    twitter4jObj.createFavorite(id)
  }
  
  /**
   * {@inhritDoc}
   */
  def destroyFavorite(id: Long): Status = {
    twitter4jObj.destroyFavorite(id)
  }
}