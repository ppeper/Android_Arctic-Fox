package com.kyonggi.workmanagerdemo

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DownloadingWorker(context: Context, params: WorkerParameters) : Worker(context, params) {

    override fun doWork(): Result {
        try {
            for (i in 0..3000) {
                Log.i("TAG", "Downloading $i")
            }
            val time = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = time.format(Date())
            Log.i("TAG", "Complete $currentDate")
            return Result.success()
        } catch (e: Exception) {
            return Result.success()
        }
    }
}