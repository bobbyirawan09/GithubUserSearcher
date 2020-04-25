package bobby.irawan.githubsearcher.api.response

import java.io.Serializable

data class GithubSearchItemResponse(
    val avatar_url: String? = "",
    val events_url: String? = "",
    val followers_url: String? = "",
    val following_url: String? = "",
    val gists_url: String? = "",
    val gravatar_id: String? = "",
    val html_url: String? = "",
    val id: Int? = 0,
    val login: String? = "",
    val node_id: String? = "",
    val organizations_url: String? = "",
    val received_events_url: String? = "",
    val repos_url: String? = "",
    val score: Double? = 0.0,
    val site_admin: Boolean? = false,
    val starred_url: String? = "",
    val subscriptions_url: String? = "",
    val type: String? = "",
    val url: String? = ""
) : Serializable
