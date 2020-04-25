package bobby.irawan.githubsearcher.repository

import bobby.irawan.githubsearcher.api.service.GithubSearchService
import bobby.irawan.githubsearcher.ui.modelview.GithubSearchItemModelView
import io.reactivex.Single

/**
 * Created by bobbyirawan09 on 22/04/20.
 */

class GithubSearchRepository constructor(private val api: GithubSearchService) :
    GithubSearchRepositoryContract {

    override fun callApi(query: String, currentPage: Int): Single<MutableList<GithubSearchItemModelView>> {
        return api.callApi(query, currentPage).map {
            val newList = mutableListOf<GithubSearchItemModelView>()
            it.items?.forEach { item ->
                newList.add(GithubSearchItemModelView.convertToModelView(response = item))
            }
            newList
        }
    }

}