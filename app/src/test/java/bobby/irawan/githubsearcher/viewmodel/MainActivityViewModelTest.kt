package bobby.irawan.githubsearcher.viewmodel

import bobby.irawan.githubsearcher.ui.modelview.GithubSearchItemModelView
import bobby.irawan.githubsearcher.repository.GithubSearchRepositoryContract
import bobby.irawan.githubsearcher.ui.viewmodel.MainActivityViewModel
import bobby.irawan.githubsearcher.utils.Constant.EMPTY
import io.reactivex.Single
import junit.framework.Assert.assertEquals
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

/**
 * Created by bobbyirawan09 on 25/04/20.
 */

class MainActivityViewModelTest : Spek({

    val repository = mock(GithubSearchRepositoryContract::class.java)
    val viewModel =
        MainActivityViewModel(repository)

    Feature("Call API") {

        Scenario("queries aren't same as before and results are empty") {
            var query = EMPTY
            var isFetchNextResult = false
            var currentPage = 0

            Given("a query and isFetchNextResult status") {
                query = "Pikachu"
                isFetchNextResult = false
                currentPage = 1
                doReturn(Single.just(mutableListOf<GithubSearchItemModelView>())).`when`(repository)
                    .callApi(query, currentPage)
            }

            When("request to API") {
                viewModel.callApi(query, isFetchNextResult)
            }

            Then("it should return empty value") {
                assertEquals(true, viewModel.githubUsersLiveData.value?.isEmpty())
            }
        }
    }
})