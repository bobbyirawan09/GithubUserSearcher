package bobby.irawan.githubsearcher.api.service

import bobby.irawan.githubsearcher.api.response.GithubSearchResponse
import io.reactivex.Single

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
interface GithubSearchService {

    fun callApi(query: String, currentPage: Int): Single<GithubSearchResponse>

}