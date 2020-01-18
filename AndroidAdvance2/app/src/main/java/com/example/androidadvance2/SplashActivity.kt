package com.example.androidadvance2

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)

        BackGroundSplashTask(this).execute()

    }

    private class BackGroundSplashTask(var activity: AppCompatActivity): AsyncTask<Void, Void, Void>() {

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun doInBackground(vararg params: Void?): Void? {
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }


        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            val i = Intent(activity, MainActivity::class.java)
            activity.startActivity(i)
            activity.finish()
        }
    }

}
