package dev.theimpulson.workmanagerexample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.theimpulson.workmanagerexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val constraints = Constraints.Builder()
                .setRequiresCharging(true)
                .build()
            val workRequest = OneTimeWorkRequestBuilder<MainActivityWorkManager>()
                .setConstraints(constraints)
                .build()

            WorkManager
                .getInstance(it.context)
                .enqueue(workRequest)

            Toast.makeText(it.context, "WorkManager Request Made", Toast.LENGTH_SHORT).show()
        }
    }
}