package com.mati.HealthyEating

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class HiltTestRunner: AndroidJUnitRunner() {

    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestRunner::class.java.name, context)
    }

}