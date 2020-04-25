package bobby.irawan.githubsearcher.repository

import bobby.irawan.githubsearcher.ui.modelview.GithubSearchItemModelView
import io.reactivex.Single

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
interface GithubSearchRepositoryContract {

    fun callApi(query: String, currentPage: Int): Single<MutableList<GithubSearchItemModelView>>
}