package com.example.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.button_job
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

class MainActivity : AppCompatActivity() {

    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0
    private val JOB_TIME = 6000
    private lateinit var job: CompletableJob


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_job.setOnClickListener {
            changeButtonText()
            if (!::job.isInitialized) {
                initJob()
            }
            progressBar.startJob1OrCancel(job)
        }
    }

    private fun ProgressBar.startJob1OrCancel(job: Job) {
        if (this.progress > 0) {
            resetJob()
        } else {
           // this@MainActivity.button_job.text = "Cancel Job #1"
            CoroutineScope(IO + job).launch {

                for (i in PROGRESS_START..PROGRESS_MAX) {
                    delay((JOB_TIME / PROGRESS_MAX).toLong())
                    this@startJob1OrCancel.progress = i
                }
                updateTextView("Job is complete!")
            }
        }
    }

    private fun changeButtonText() {
        GlobalScope.launch(Main) {
            withContext(Main) {
                button_job.text = "Clicked"
                delay(2000)
                button_job.text = "Cancel Job #1"
            }
        }
    }

    private fun updateTextView(txt: String) {
        GlobalScope.launch(Main) {
            text.text = txt
        }
    }

    private fun resetJob() {
        if (job.isActive || job.isCompleted) {
            job.cancel(CancellationException("Resetting job"))
        }
        initJob()
    }

    private fun initJob() {
        button_job.text = "Start job"
        updateTextView("")
        job = Job()
        job.invokeOnCompletion {
            it?.message.let {
                var message = it
                if (message.isNullOrBlank()) {
                    message = "Some error occured"
                }
                showToast(message)
            }
        }
        progressBar.max = PROGRESS_MAX
        progressBar.progress = PROGRESS_START
    }

    private fun showToast(message: String) {
        CoroutineScope(Main).launch {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}