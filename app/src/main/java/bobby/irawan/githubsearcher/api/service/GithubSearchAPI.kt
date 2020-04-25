package bobby.irawan.githubsearcher.api.service

import bobby.irawan.githubsearcher.api.response.GithubSearchResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
interface GithubSearchAPI {

    @GET("/search/users")
    fun getGithubUsers(
        @Query("q") keyword: String,
        @Query("page") currentPage: Int
    ): Single<Response<GithubSearchResponse>>

}