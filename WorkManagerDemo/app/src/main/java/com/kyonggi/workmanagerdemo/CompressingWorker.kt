package com.kyonggi.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception

class CompressingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0..300) {
                Log.i("TAG", "Compressing $i")
            }
            return Result.success()
        } catch (e: Exception) {
            return Result.success()
        }
    }
}