package com.zero.zerobase.utils.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import android.widget.ImageView.ScaleType.*
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

/**
 * Method to load image to ImageView, please read carefully what param you can use
 *
 * There's 3 valid object you can load to ImageView here, you <b>must</b> use one of these three
 * @param imageDrawable drawable of the image to load
 * @param imageUrl url of the image to load
 * @param imageRes resource id of the image to load
 *
 * You can add placeHolder when loading Image, choose <i>one of these two</i>
 * @param placeHolderDrawable drawable of the placeHolder
 * @param placeHolderResourceId resource id of the placeholder
 *
 * You can add progressBar when loading the image, for that pass value for this parameter
 * @param progressBar progressbar to display when image is loading
 *
 * You can add error image when the process failed, choose <i> one of these two</i>
 * @param errorDrawable drawable of the error image
 * @param errorResourceId resource id of error image
 *
 * @param onImageLoaded action when the image is loaded, null by default
 * @param onImageNotLoaded action when the image  fail to load, null by default
 *
 * You can set the ScaleType of the image, if not defined the default value is CENTER_CROP
 * @param scaleType scaleType of image that will be loaded
 *
 */
@SuppressLint("CheckResult")
fun ImageView.loadImage(
    imageDrawable: Drawable? = null,
    imageUrl: String? = null,
    imageRes: Int? = null,
    placeHolderDrawable: Drawable? = null,
    placeHolderResourceId: Int? = null,
    progressBar: ProgressBar? = null,
    errorResourceId: Int? = null,
    errorDrawable: Drawable? = null,
    onImageLoaded: (() -> Unit)? = null,
    onImageNotLoaded: (() -> Unit)? = null,
    scaleType: ScaleType = CENTER_CROP
) {
    if (!isValidContext(this.context)) return

    val options = RequestOptions()

    when (scaleType) {
        CENTER_INSIDE -> options.centerInside()
        CENTER_CROP -> options.centerCrop()
        FIT_CENTER -> options.fitCenter()
        else -> options.centerCrop()
    }

    placeHolderDrawable?.let { drawable -> options.placeholder(drawable) }
    placeHolderResourceId?.let { resourceId -> options.placeholder(resourceId) }

    errorDrawable?.let { drawable -> options.error(drawable) }
    errorResourceId?.let { resourceId -> options.error(resourceId) }

    progressBar?.visible()

    Glide.with(this.context).load(
        when {
            imageDrawable.isNotNull() -> imageDrawable
            imageRes.isNotNull() -> imageRes
            imageUrl.isNotNull() -> imageUrl
            else -> return
        }
    ).apply(options)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?, model: Any, target: Target<Drawable>, isFirstResource: Boolean
            ): Boolean {
                progressBar?.gone()
                onImageNotLoaded?.invoke()
                return false
            }

            override fun onResourceReady(
                resource: Drawable,
                model: Any,
                target: Target<Drawable>,
                dataSource: DataSource,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.gone()
                onImageLoaded?.invoke()
                return false
            }
        })
        .into(this)
}

/**
 * Method to check if context is valid or not
 * @param context Context that will be used to display image
 * @return is Context valid?
 */
fun isValidContext(context: Context): Boolean {
    val activity = context as? Activity
    return if (activity != null) {
        !(activity.isDestroyed || activity.isFinishing)
    } else {
        true
    }
}

@SuppressLint("CheckResult")
class ImageViewLoadConfiguration(
    var source: Any? = null,
    var scaleType: ScaleType = CENTER_CROP,
    var progressBar: ProgressBar? = null,
    var onImageLoaded: (() -> Unit)? = null,
    var onImageNotLoaded: (() -> Unit)? = null,
) {
    val options = RequestOptions()
    val loadListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            progressBar?.gone()
            onImageNotLoaded?.invoke()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            progressBar?.gone()
            onImageLoaded?.invoke()
            return false
        }

    }

    fun withImageSource(source: Any?) {
        when (source) {
            is Drawable -> this.source = source
            is Int -> this.source = source
            is String -> this.source = source
            else -> throw Throwable("data type not supported for image source")
        }
    }

    fun withPlaceHolder(placeHolder: Any?) {
        when (placeHolder) {
            is Drawable -> options.placeholder(placeHolder)
            is Int -> options.placeholder(placeHolder)
            else -> throw Throwable("only Drawable and resource Id is supported for place holder")
        }
    }

    fun withErrorImage(errorImage: Any?) {
        when (errorImage) {
            is Drawable -> options.error(errorImage)
            is Int -> options.error(errorImage)
            else -> throw Throwable("only Drawable and resource Id is supported for error image")
        }
    }

    fun withScaleType(scaleType: ScaleType) {
        when (scaleType) {
            CENTER_INSIDE -> options.centerInside()
            CENTER_CROP -> options.centerCrop()
            FIT_CENTER -> options.fitCenter()
            else -> options.centerCrop()
        }
    }

    fun withProgressBar(progressBar: ProgressBar) {
        this.progressBar = progressBar
    }

    fun withImageLoaded(action: () -> Unit) {
        onImageLoaded = action
    }

    fun withImageNotLoaded(action: () -> Unit) {
        onImageNotLoaded = action
    }
}

typealias loadConfiguration = ImageViewLoadConfiguration.() -> Unit

fun ImageView.loadImage(configuration: loadConfiguration) {
    val config = ImageViewLoadConfiguration().apply(configuration)
    Glide.with(context)
        .load(config.source)
        .apply(config.options)
        .listener(config.loadListener)
        .into(this)
}