package bobby.irawan.githubsearcher.di

import bobby.irawan.githubsearcher.api.service.GithubSearchService
import bobby.irawan.githubsearcher.api.service.GithubSearchServiceImpl
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 22/04/20.
 */

val serviceModule = module {

    single<GithubSearchService> {
        GithubSearchServiceImpl()
    }
}