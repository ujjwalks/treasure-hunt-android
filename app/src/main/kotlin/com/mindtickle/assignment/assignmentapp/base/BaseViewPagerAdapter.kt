package com.mindtickle.assignment.assignmentapp.base

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.mindtickle.assignment.assignmentapp.dependency.qualifiers.ActivityFragmentManager

/**
 * Created by neeraj on 29/01/18.
 */

abstract class BaseViewPagerAdapter constructor(@ActivityFragmentManager fm: FragmentManager) : FragmentPagerAdapter(fm)