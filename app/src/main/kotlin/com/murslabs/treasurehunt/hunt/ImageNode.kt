package com.murslabs.treasurehunt.hunt

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.ar.core.AugmentedImage
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import java.util.concurrent.CompletableFuture

/**
 * Node for rendering an augmented image. The image is framed by placing the virtual picture frame
 * at the corners of the augmented image trackable.
 */
class ImageNode(context: Context) : AnchorNode() {

    // The augmented image represented by this node.
    /**
     * Called when the AugmentedImage is detected and should be rendered. A Sceneform node tree is
     * created based on an Anchor created from the image. The corners are then positioned based on the
     * extents of the image. There is no need to worry about world coordinates since everything is
     * relative to the center of the image, which is the parent node of the corners.
     */
    // If any of the models are not loaded, then recurse when all are loaded.
    // Set the anchor based on the center of the image.
    // Make the 4 corner nodes.
    // Upper left corner.
    // Upper right corner.
    // Lower right corner.
    // Lower left corner.
    private var arImage: AugmentedImage? = null

    fun setArImage(image: AugmentedImage) {
        arImage = image
        if (!ulCorner!!.isDone || !urCorner!!.isDone || !llCorner!!.isDone || !lrCorner!!.isDone) {
            CompletableFuture.allOf(ulCorner, urCorner, llCorner, lrCorner)
                    .thenAccept { aVoid: Void -> setArImage(image) }
                    .exceptionally { throwable ->
                        Log.e(TAG, "Exception loading", throwable)
                        null
                    }
        }
        anchor = image.createAnchor(image.centerPose)
        val localPosition = Vector3()
        var cornerNode: Node = Node()
        localPosition.set(-0.5f * image.extentX, 0.0f, -0.5f * image.extentZ)
        cornerNode.setParent(this)
        cornerNode.localPosition = localPosition
        cornerNode.renderable = ulCorner!!.getNow(null)
        localPosition.set(0.5f * image.extentX, 0.0f, -0.5f * image.extentZ)
        cornerNode = Node()
        cornerNode.setParent(this)
        cornerNode.localPosition = localPosition
        cornerNode.renderable = urCorner!!.getNow(null)
        localPosition.set(0.5f * image.extentX, 0.0f, 0.5f * image.extentZ)
        cornerNode = Node()
        cornerNode.setParent(this)
        cornerNode.localPosition = localPosition
        cornerNode.renderable = lrCorner!!.getNow(null)
        localPosition.set(-0.5f * image.extentX, 0.0f, 0.5f * image.extentZ)
        cornerNode = Node()
        cornerNode.setParent(this)
        cornerNode.localPosition = localPosition
        cornerNode.renderable = llCorner!!.getNow(null)
    }

    init {
        // Upon construction, start loading the models for the corners of the frame.
        if (ulCorner == null) {
            ulCorner = ModelRenderable.builder()
                    .setSource(context, Uri.parse("models/frame_upper_left.obj"))
                    .build()
            urCorner = ModelRenderable.builder()
                    .setSource(context, Uri.parse("models/frame_upper_right.obj"))
                    .build()
            llCorner = ModelRenderable.builder()
                    .setSource(context, Uri.parse("models/frame_lower_left.obj"))
                    .build()
            lrCorner = ModelRenderable.builder()
                    .setSource(context, Uri.parse("models/frame_lower_right.obj"))
                    .build()
        }
    }

    companion object {

        private val TAG = "ImageNode"

        // Models of the 4 corners.  We use completable futures here to simplify
        // the error handling and asynchronous loading.  The loading is started with the
        // first construction of an instance, and then used when the image is set.
        private var ulCorner: CompletableFuture<ModelRenderable>? = null
        private var urCorner: CompletableFuture<ModelRenderable>? = null
        private var lrCorner: CompletableFuture<ModelRenderable>? = null
        private var llCorner: CompletableFuture<ModelRenderable>? = null
    }
}