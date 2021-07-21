package com.example.parkingsystemkotlin.mvp.view.base

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {

    private val activityRef: WeakReference<Activity> = WeakReference(activity)

    val activity: Activity?
        get() = activityRef.get()

    fun getContext(): Context? = activity

    fun getFragmentManager(): FragmentManager? = (activity as? AppCompatActivity)?.supportFragmentManager
}
