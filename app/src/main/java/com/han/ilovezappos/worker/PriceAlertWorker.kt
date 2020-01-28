package com.han.ilovezappos.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.han.ilovezappos.R
import com.han.ilovezappos.api.ApiClient
import com.han.ilovezappos.api.TickerHourApi
import com.han.ilovezappos.api.TickerHourResponse
import com.han.ilovezappos.db.ILoveZapposDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriceAlertWorker(val context: Context, workerParam: WorkerParameters) :
    Worker(context, workerParam) {

    val CHANNEL_ID = "alert"
    val NOTIFICATION_ID = 9527

    override fun doWork(): Result {
        createNotificationChannel()

        val priceAlertDao = ILoveZapposDatabase.getDB(context).priceAlertDao()
        val allPriceAlert = priceAlertDao.getAllPriceAlertSync()


        val api = ApiClient().getApi(TickerHourApi::class.java)
        api.getTicker().enqueue(object : Callback<TickerHourResponse> {
            override fun onFailure(
                call: Call<TickerHourResponse>,
                t: Throwable
            ) {
                // TODO: Handle api exception
            }

            override fun onResponse(
                call: Call<TickerHourResponse>,
                response: Response<TickerHourResponse>
            ) {
                response.body()?.let {
                    allPriceAlert.forEach { priceAlert ->
                        if (priceAlert.price >= it.lastPrice.toDouble()) {
                            sendNotification()
                        }
                    }
                }
            }
        })

        return Result.success()
    }

    private fun sendNotification() {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_lightbulb_outline_black_24dp)
            .setContentTitle("BTC Price Alert")
            .setContentText("BTC price reach the price you set")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.channel_name)
            val descriptionText = context.getString(R.string.channel_desc)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}