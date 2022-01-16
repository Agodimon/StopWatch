package com.bignerdranch.android.stopwatch.model

interface TimestampProvider {
    fun getMilliseconds(): Long
}