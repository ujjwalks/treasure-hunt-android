package com.mindtickle.assignment.assignmentapp.dependency.module

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.mindtickle.assignment.assignmentapp.dependency.qualifiers.ChildFragmentManager
import com.mindtickle.assignment.assignmentapp.dependency.scope.PerFragment
import dagger.Module
import dagger.Provides

/**
 * Created by Ujjwal on 21/01/18.
 */
@Module
class FragmentModule(private val mFragment: Fragment) {

    @Provides
    @PerFragment
    @ChildFragmentManager
    internal fun provideChildFragmentManager(): FragmentManager {
        return mFragment.childFragmentManager
    }
}