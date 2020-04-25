package bobby.irawan.githubsearcher.api.response

import java.io.Serializable

/**
 * Created by bobbyirawan09 on 22/04/20.
 */

data class GithubSearchResponse(
    val incomplete_results: Boolean? = false,
    val items: List<GithubSearchItemResponse>? = listOf(),
    val total_count: Int? = 0
) : Serializable