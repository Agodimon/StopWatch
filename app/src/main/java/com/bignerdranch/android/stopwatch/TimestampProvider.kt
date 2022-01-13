package com.bignerdranch.android.stopwatch

interface TimestampProvider {
    fun getMilliseconds(): Long
}