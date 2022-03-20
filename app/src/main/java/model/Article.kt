package model

import java.io.Serializable

data class Article(
    var Title: String?,
    var ImageUrl: String?,
    var Author: String?,
    var Body: String?
) : Serializable