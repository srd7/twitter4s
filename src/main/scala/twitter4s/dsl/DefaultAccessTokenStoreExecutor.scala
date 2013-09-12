package twitter4s.dsl

/*
 * Copyright (C) 2013 Shinsuke Abe
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
import twitter4j.auth.AccessToken
import java.io.PrintWriter

/**
 * @author mao.instantlife at gmail.com
 */
object DefaultAccessTokenStoreExecutor extends AccessTokenStoreExecutor {
  def store(args: Pair[Long, AccessToken]) = {
    val writer = new PrintWriter(args._1.toString + ".txt")

    writer.println("accessToken=" + args._2.getToken)
    writer.println("tokenSecret=" + args._2.getTokenSecret)
    writer.flush()

    writer.close()

    args
  }
}
