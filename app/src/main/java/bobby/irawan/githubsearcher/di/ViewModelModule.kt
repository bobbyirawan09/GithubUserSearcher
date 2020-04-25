package bobby.irawan.githubsearcher.di

import bobby.irawan.githubsearcher.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by bobbyirawan09 on 22/04/20.
 */

val viewModelModule = module {

    viewModel {
        MainActivityViewModel(get())
    }
}