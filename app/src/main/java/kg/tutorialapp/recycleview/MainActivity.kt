package kg.tutorialapp.recycleview

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificatoinId = 101
    val WHERE_MY_CAT_ACTION = "kg.tutorialapp..action.CAT"
    val ALARM_MESSAGE = "Срочно пришлите кота!"




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomRecyclerAdapter(fillList())
        recyclerView.adapter = CustomRecyclerAdapter(getCatList())

        createNotificationChannel()
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener{
            sendNotification()
        }
        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener{
            sendMessage()

        }
    }

    private fun fillList(): List<String> {
        val data = mutableListOf<String>()
        (0..30).forEach { i -> data.add("$i element") }
        return data
    }
    private fun getCatList(): List<String> {
        return this.resources.getStringArray(R.array.cat_names).toList()
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.images)
            .setContentTitle("Example Title")
            .setContentText("Example Description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificatoinId, builder.build())
        }
    }
    fun sendMessage() {
        val intent = Intent()
        intent.action = WHERE_MY_CAT_ACTION
        intent.putExtra("kg.tutorialapp.broadcast.Message", ALARM_MESSAGE)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }
}