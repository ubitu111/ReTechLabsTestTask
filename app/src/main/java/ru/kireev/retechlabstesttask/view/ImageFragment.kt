package ru.kireev.retechlabstesttask.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.kireev.retechlabstesttask.databinding.FragmentImageBinding
import ru.kireev.retechlabstesttask.viewmodel.AppState
import ru.kireev.retechlabstesttask.viewmodel.ImageViewModel

class ImageFragment(
    private val viewModel: ImageViewModel
) : Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner, {
            handleState(it)
        })
    }

    private fun handleState(state: AppState) {
        when (state) {
            is AppState.Success<*> -> setImage(state.data as String)
            is AppState.Error -> showError(state.error.localizedMessage)
            is AppState.Loading -> showProgress(true)
        }
    }

    private fun setImage(url: String) {
        Glide
            .with(this)
            .load(url)
            .centerInside()
            .listener(object : RequestListener<Drawable> {
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.fragmentImageView.setImageDrawable(resource)
                    showProgress(false)
                    return true
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    showProgress(false)
                    showError(e?.localizedMessage)
                    return true
                }

            })
            .into(binding.fragmentImageView)
    }

    private fun showError(message: String?) {
        showProgress(false)
        Toast.makeText(binding.root.context, message, Toast.LENGTH_SHORT).show()
    }

    fun loadImage() {
        viewModel.loadImageUrl()
    }

    private fun showProgress(isLoading: Boolean) {
        val visibility = if (isLoading) {
            View.VISIBLE
        } else {
            View.INVISIBLE
        }
        binding.progressImage.visibility = visibility
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}