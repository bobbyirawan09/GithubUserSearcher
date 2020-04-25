package bobby.irawan.githubsearcher.ui.modelview

import bobby.irawan.githubsearcher.api.response.GithubSearchItemResponse
import java.io.Serializable

data class GithubSearchItemModelView(
    var avatar_url: String? = "",
    var events_url: String? = "",
    var followers_url: String? = "",
    var following_url: String? = "",
    var gists_url: String? = "",
    var gravatar_id: String? = "",
    var html_url: String? = "",
    var id: Int? = 0,
    var login: String? = "",
    var node_id: String? = "",
    var organizations_url: String? = "",
    var received_events_url: String? = "",
    var repos_url: String? = "",
    var score: Double? = 0.0,
    var site_admin: Boolean? = false,
    var starred_url: String? = "",
    var subscriptions_url: String? = "",
    var type: String? = "",
    var url: String? = ""
) : Serializable {

    companion object {

        fun convertToModelView(response: GithubSearchItemResponse): GithubSearchItemModelView {
            return GithubSearchItemModelView()
                .apply {
                    avatar_url = response.avatar_url
                    events_url = response.events_url
                    followers_url = response.followers_url
                    following_url = response.following_url
                    gists_url = response.gists_url
                    gravatar_id = response.gravatar_id
                    html_url = response.html_url
                    id = response.id
                    login = response.login
                    node_id = response.node_id
                    organizations_url = response.organizations_url
                    received_events_url = response.received_events_url
                    repos_url = response.repos_url
                    score = response.score
                    site_admin = response.site_admin
                    starred_url = response.starred_url
                    subscriptions_url = response.subscriptions_url
                    type = response.type
                    url = response.url
                }
        }
    }
}
