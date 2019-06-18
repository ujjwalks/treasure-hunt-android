package com.mindtickle.assignment.assignmentapp.utils.ui;

import android.databinding.DataBindingComponent;
import com.mindtickle.assignment.assignmentapp.utils.ViewBindingUtils;


public class AppDataBindingComponent implements DataBindingComponent   {

    @Override
    public ViewBindingUtils.Companion getCompanion() {
        return ViewBindingUtils.Companion;
    }
}