package com.bignerdranch.android.stopwatch.viewmodel

import com.bignerdranch.android.stopwatch.model.TimestampProvider
import com.bignerdranch.android.stopwatch.model.StopwatchState

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}