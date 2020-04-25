package bobby.irawan.githubsearcher.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.api.load

/**
 * Created by bobbyirawan09 on 22/04/20.
 */
object DataBindingUtils {
    @BindingAdapter("bind:setImageCoil")
    @JvmStatic
    fun setImageCoil(imageView: ImageView, urlImage: String?) {
        imageView.load(urlImage.orEmpty()) {
            crossfade(true)
        }
    }

    @BindingAdapter("bind:setVisibility")
    @JvmStatic
    fun setProgressBarVisibility(progressBar: ProgressBar, isShow: Boolean) {
        if (isShow) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }
}