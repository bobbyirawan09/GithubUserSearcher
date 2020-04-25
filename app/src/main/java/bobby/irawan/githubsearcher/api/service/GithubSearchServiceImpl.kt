package bobby.irawan.githubsearcher.api.service

import bobby.irawan.githubsearcher.api.response.GithubSearchResponse
import bobby.irawan.githubsearcher.utils.ApiUtils
import io.reactivex.Single

/**
 * Created by bobbyirawan09 on 22/04/20.
 */

class GithubSearchServiceImpl : GithubSearchService {
    override fun callApi(query: String, currentPage: Int): Single<GithubSearchResponse> {
        val api = ApiUtils.getRetrofitInstance().create(GithubSearchAPI::class.java)
        return api.getGithubUsers(query, currentPage).map {
            when (it.code()) {
                200 -> it.body()
                else -> null
            }
        }
    }
}