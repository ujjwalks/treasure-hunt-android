package com.mindtickle.assignment.assignmentapp.utils.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerActivity
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Ujjwal on 06/02/18.
 */
@Suppress("UNCHECKED_CAST")
@PerActivity
class ViewModelFactory
@Inject constructor(
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator = creators[modelClass]
        ?: creators.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        ?: throw IllegalArgumentException("unknown model class " + modelClass)

        return try {
            creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}