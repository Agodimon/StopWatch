package com.bignerdranch.android.stopwatch.model

import java.lang.System.currentTimeMillis

class TimestampProviderImpl : TimestampProvider {
    override fun getMilliseconds(): Long {
        return currentTimeMillis()
    }
}
