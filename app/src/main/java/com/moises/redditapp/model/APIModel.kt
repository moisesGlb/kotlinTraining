package com.moises.redditapp.model


 class RedditPostData(
      val children: List<RedditPostData>,
      val data: RedditPostData,
      val title: String,
      val thumbnail: String,
      val author: String,
      val num_comments: Int,
      val url: String,
      val created: Long)


class RedditDataResponse(
     val redditPostData: RedditPostData,
     val after: String?,
     val before: String?
)