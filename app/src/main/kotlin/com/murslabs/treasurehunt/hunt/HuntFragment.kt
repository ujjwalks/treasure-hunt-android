package com.murslabs.treasurehunt.hunt

import android.app.ActivityManager
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment
import com.murslabs.treasurehunt.common.helpers.SnackbarHelper
import timber.log.Timber
import java.io.IOException

/**
 * Extend the ArFragment to customize the ARCore session configuration to include Augmented Images.
 */
class HuntFragment : ArFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        // Check for Sceneform being supported on this device.  This check will be integrated into
        // Sceneform eventually.

        val openGlVersionString = (context!!.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager)
                .deviceConfigurationInfo
                .glEsVersion
        if (java.lang.Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Timber.e("Sceneform requires OpenGL ES 3.0 or later")
            SnackbarHelper.instance.showError(activity, "Sceneform requires OpenGL ES 3.0 or later")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        // Turn off the plane discovery since we're only looking for images
        planeDiscoveryController.hide()
        planeDiscoveryController.setInstructionView(null)
        arSceneView.planeRenderer.isEnabled = false
        return view
    }

    override fun getSessionConfiguration(session: Session): Config {
        val config = super.getSessionConfiguration(session)
        if (!setupAugmentedImageDatabase(config, session)) {
            SnackbarHelper.instance.showError(activity, "Could not setup augmented image database")
        }
        return config
    }

    private fun setupAugmentedImageDatabase(config: Config, session: Session): Boolean {
        var augmentedImageDatabase: AugmentedImageDatabase? = null

        val assetManager = if (context != null) context!!.assets else null
        if (assetManager == null) {
            Timber.e("Context is null, cannot intitialize image database.")
            return false
        }

        // There are two ways to configure an AugmentedImageDatabase:
        // 1. Add Bitmap to DB directly
        // 2. Load a pre-built AugmentedImageDatabase
        // Option 2) has
        // * shorter setup time
        // * doesn't require images to be packaged in apk.
        if (USE_SINGLE_IMAGE) {
            val augmentedImageBitmap = loadAugmentedImageBitmap(assetManager) ?: return false

            augmentedImageDatabase = AugmentedImageDatabase(session)
            augmentedImageDatabase.addImage(DEFAULT_IMAGE_NAME, augmentedImageBitmap)
            // If the physical size of the image is known, you can instead use:
            //     augmentedImageDatabase.addImage("image_name", augmentedImageBitmap, widthInMeters);
            // This will improve the initial detection speed. ARCore will still actively estimate the
            // physical size of the image as it is viewed from multiple viewpoints.
        } else {
            // This is an alternative way to initialize an AugmentedImageDatabase instance,
            // load a pre-existing augmented image database.
            try {
                context!!.assets.open(SAMPLE_IMAGE_DATABASE).use { `is` -> augmentedImageDatabase = AugmentedImageDatabase.deserialize(session, `is`) }
            } catch (e: IOException) {
                Timber.e(e, "IO exception loading augmented image database.")
                return false
            }

        }

        config.augmentedImageDatabase = augmentedImageDatabase
        return true
    }

    private fun loadAugmentedImageBitmap(assetManager: AssetManager): Bitmap? {
        try {
            assetManager.open(DEFAULT_IMAGE_NAME).use { `is` -> return BitmapFactory.decodeStream(`is`) }
        } catch (e: IOException) {
            Timber.e(e, "IO exception loading augmented image bitmap.")
        }

        return null
    }

    companion object {
        private val TAG = "AugmentedImageFragment"

        // This is the name of the image in the sample database.  A copy of the image is in the assets
        // directory.  Opening this image on your computer is a good quick way to test the augmented image
        // matching.
        private val DEFAULT_IMAGE_NAME = "default.jpg"

        // This is a pre-created database containing the sample image.
        private val SAMPLE_IMAGE_DATABASE = "sample_database.imgdb"

        // Augmented image configuration and rendering.
        // Load a single image (true) or a pre-generated image database (false).
        private val USE_SINGLE_IMAGE = false

        // Do a runtime check for the OpenGL level available at runtime to avoid Sceneform crashing the
        // application.
        private val MIN_OPENGL_VERSION = 3.0
    }
}