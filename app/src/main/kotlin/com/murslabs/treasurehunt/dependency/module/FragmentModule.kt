package com.murslabs.treasurehunt.dependency.module

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.murslabs.treasurehunt.dependency.qualifiers.ChildFragmentManager
import com.murslabs.treasurehunt.dependency.scope.PerFragment
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