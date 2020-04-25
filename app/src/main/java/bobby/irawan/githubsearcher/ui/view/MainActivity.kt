package bobby.irawan.githubsearcher.ui.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import bobby.irawan.githubsearcher.R
import bobby.irawan.githubsearcher.databinding.ActivityMainBinding
import bobby.irawan.githubsearcher.ui.adapter.GithubSearchAdapter
import bobby.irawan.githubsearcher.ui.modelview.GithubSearchItemModelView
import bobby.irawan.githubsearcher.ui.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.toolbar_github_user_search.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    private val adapter =
        GithubSearchAdapter()
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        hideActionBar()
        observeViewModelChanges()
        setInputListener()
        binding.textViewEmpty.visibility = View.VISIBLE
        setUpRecyclerView()
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }

    private fun observeViewModelChanges() {
        viewModel.loadingListLiveData.observe(
            this,
            Observer { visibility -> binding.progressBarLoadingItem.visibility = visibility }
        )

        viewModel.emptyViewLiveData.observe(
            this,
            Observer { visibility -> binding.textViewEmpty.visibility = visibility }
        )

        viewModel.githubUsersLiveData.observe(
            this,
            Observer { result -> setDataToAdapter(result) }
        )

        viewModel.errorMessageLiveData.observe(
            this,
            Observer { message -> Toast.makeText(this, message, Toast.LENGTH_LONG).show() }
        )

        viewModel.loadingAdapterItemLiveData.observe(
            this,
            Observer { visibility -> binding.progressBarLoadingNewItem.visibility = visibility }
        )
    }

    private fun setInputListener() {
        binding.toolbarSearch.edit_text_search_merchant.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = v.text.toString()
                hideKeyboard(v)
                viewModel.setLoadingListVisibility(View.GONE)
                viewModel.callApi(query, false)
                true
            } else {
                false
            }
        }
    }

    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        adapter.setHasStableIds(true)
        binding.recyclerViewGithubRepoResult.layoutManager = layoutManager
        binding.recyclerViewGithubRepoResult.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewGithubRepoResult.getContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerViewGithubRepoResult.adapter = adapter
        binding.nestedScrollViewGithubUser.setOnScrollChangeListener { v: NestedScrollView?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v?.getChildAt(v.getChildCount() - 1) != null) {
                if ((scrollY >= (v.getChildAt(v.getChildCount() - 1)
                        .getMeasuredHeight() - v.getMeasuredHeight())) &&
                    scrollY > oldScrollY
                ) {
                    viewModel.showLoadingAdapterItem()
                    viewModel.callApi(fetchNextResult = true)
                }
            }
        }
    }

    private fun setDataToAdapter(result: MutableList<GithubSearchItemModelView>) {
        val isFirstData = viewModel.isFirstDataLiveData.value ?: false
        if (isFirstData) {
            binding.recyclerViewGithubRepoResult.smoothScrollToPosition(0)
            adapter.setGithubSearchResult(result)
        } else {
            adapter.addGithubSearchResult(result)
        }
        viewModel.isDataEmpty(result, isFirstData)
    }

    private fun hideKeyboard(v: TextView?) {
        val imm: InputMethodManager =
            applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v?.getWindowToken(), 0)
    }
}
