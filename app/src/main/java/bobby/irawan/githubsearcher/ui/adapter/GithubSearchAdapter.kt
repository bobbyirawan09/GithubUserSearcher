package bobby.irawan.githubsearcher.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bobby.irawan.githubsearcher.R
import bobby.irawan.githubsearcher.databinding.RowGithubRepoBinding
import bobby.irawan.githubsearcher.ui.modelview.GithubSearchItemModelView

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
class GithubSearchAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var githubUsers = mutableListOf<GithubSearchItemModelView>()

    fun setGithubSearchResult(newGithubUsers: MutableList<GithubSearchItemModelView>) {
        githubUsers.clear()
        githubUsers = newGithubUsers
        notifyDataSetChanged()
    }

    fun addGithubSearchResult(newGithubUsers: MutableList<GithubSearchItemModelView>) {
        githubUsers.addAll(newGithubUsers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingAdapter = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_github_repo,
            parent,
            false
        ) as RowGithubRepoBinding
        return ListViewHolder(bindingAdapter)
    }

    override fun getItemCount(): Int {
        return githubUsers.size
    }

    override fun getItemId(position: Int): Long {
        return (githubUsers.get(position).id ?: position).toLong()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listHolder = holder as ListViewHolder
        listHolder.bind(githubUsers.get(position))
    }

    inner class ListViewHolder(val binding: RowGithubRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchResult: GithubSearchItemModelView) {
            binding.githubUsers = searchResult
            binding.executePendingBindings()
        }
    }
}