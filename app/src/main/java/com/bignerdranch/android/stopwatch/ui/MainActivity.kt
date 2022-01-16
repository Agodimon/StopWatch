package com.bignerdranch.android.stopwatch.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.stopwatch.databinding.ActivityMainBinding
import com.bignerdranch.android.stopwatch.model.TimestampProviderImpl
import com.bignerdranch.android.stopwatch.viewmodel.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var job: Job? = null

    private val mainViewModelFirstStopWatcher by lazy {
        StopwatchListOrchestratorMainViewModel(
            StopwatchStateHolder(
                StopwatchStateCalculator(TimestampProviderImpl(),
                ElapsedTimeCalculator(TimestampProviderImpl())),
                ElapsedTimeCalculator(TimestampProviderImpl()),
                TimestampMillisecondsFormatter()))
    }

    private val mainViewModelSecondStopWatcher by lazy {
        StopwatchListOrchestratorMainViewModel(
            StopwatchStateHolder(
                StopwatchStateCalculator(TimestampProviderImpl(),
                    ElapsedTimeCalculator(TimestampProviderImpl())),
                ElapsedTimeCalculator(TimestampProviderImpl()),
                TimestampMillisecondsFormatter()))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            job = scope.launch {
                mainViewModelFirstStopWatcher
                    .ticker
                    .collect { textTime.text=it }
            }
            job = scope.launch {
                mainViewModelSecondStopWatcher
                    .ticker
                    .collect {
                        textTime2.text = it
                    }
            }

            with(binding){
                buttonStart.setOnClickListener {mainViewModelFirstStopWatcher.start()}
                buttonPause.setOnClickListener {mainViewModelFirstStopWatcher.pause()}
                buttonStop.setOnClickListener {mainViewModelFirstStopWatcher.stop()}

                buttonStart2.setOnClickListener {mainViewModelSecondStopWatcher.start()}
                buttonPause2.setOnClickListener {mainViewModelSecondStopWatcher.pause()}
                buttonStop2.setOnClickListener {mainViewModelSecondStopWatcher.stop()}
            }
        }
    }
    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}












