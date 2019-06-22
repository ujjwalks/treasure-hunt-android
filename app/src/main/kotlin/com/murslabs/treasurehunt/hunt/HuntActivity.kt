package com.murslabs.treasurehunt.hunt

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.google.ar.core.AugmentedImage
import com.google.ar.core.TrackingState
import com.google.ar.sceneform.FrameTime
import com.google.ar.sceneform.Scene
import com.google.ar.sceneform.ux.ArFragment
import com.murslabs.treasurehunt.R
import com.murslabs.treasurehunt.common.helpers.SnackbarHelper
import java.util.HashMap

/**
 * This application demonstrates using augmented images to place anchor nodes. app to include image
 * tracking functionality.
 */
class HuntActivity : AppCompatActivity() {

    private var arFragment: ArFragment? = null
    private var fitToScanView: ImageView? = null

    // Augmented image and its associated center pose anchor, keyed by the augmented image in
    // the database.
    private val augmentedImageMap = HashMap<AugmentedImage, ImageNode>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arFragment = supportFragmentManager.findFragmentById(R.id.ux_fragment) as ArFragment?
        fitToScanView = findViewById(R.id.image_view_fit_to_scan)

        arFragment!!.arSceneView.scene.addOnUpdateListener(Scene.OnUpdateListener { this.onUpdateFrame(it) })
    }

    override fun onResume() {
        super.onResume()
        if (augmentedImageMap.isEmpty()) {
            fitToScanView!!.visibility = View.VISIBLE
        }
    }

    /**
     * Registered with the Sceneform Scene object, this method is called at the start of each frame.
     *
     * @param frameTime - time since last frame.
     */
    private fun onUpdateFrame(frameTime: FrameTime) {
        val frame = arFragment!!.arSceneView.arFrame

        // If there is no frame or ARCore is not tracking yet, just return.
        if (frame == null || frame.camera.trackingState != TrackingState.TRACKING) {
            return
        }

        val updatedAugmentedImages = frame.getUpdatedTrackables(AugmentedImage::class.java)
        for (augmentedImage in updatedAugmentedImages) {
            when (augmentedImage.trackingState) {
                TrackingState.PAUSED -> {
                    // When an image is in PAUSED state, but the camera is not PAUSED, it has been detected,
                    // but not yet tracked.
                    val text = "Detected Image " + augmentedImage.index
                    SnackbarHelper.instance.showMessage(this, text)
                }

                TrackingState.TRACKING -> {
                    // Have to switch to UI Thread to update View.
                    fitToScanView!!.visibility = View.GONE

                    // Create a new anchor for newly found images.
                    if (!augmentedImageMap.containsKey(augmentedImage)) {
                        val node = ImageNode(this)
                        node.setArImage(augmentedImage)
                        augmentedImageMap[augmentedImage] = node
                        arFragment!!.arSceneView.scene.addChild(node)
                    }
                }

                TrackingState.STOPPED -> augmentedImageMap.remove(augmentedImage)
            }
        }
    }
}