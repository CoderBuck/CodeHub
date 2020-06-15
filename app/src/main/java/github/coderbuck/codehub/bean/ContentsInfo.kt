package github.coderbuck.codehub.bean

class Contents : ArrayList<Content>()

data class Content(
    val _links: Links,
    val download_url: String,
    val git_url: String,
    val html_url: String,
    val name: String,
    val path: String,
    val sha: String,
    val size: Int,
    val type: String,
    val url: String
)

data class Links(
    val git: String,
    val html: String,
    val self: String
)