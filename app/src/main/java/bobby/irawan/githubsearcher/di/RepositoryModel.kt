package bobby.irawan.githubsearcher.di

import bobby.irawan.githubsearcher.repository.GithubSearchRepository
import bobby.irawan.githubsearcher.repository.GithubSearchRepositoryContract
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 22/04/20.
 */

val repositoryModule = module {

    single<GithubSearchRepositoryContract> {
        GithubSearchRepository(get())
    }
}