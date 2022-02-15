package kg.tutorialapp.recycleview

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class MessageReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        Toast.makeText(
            context, "Обнаружено сообщение: " +
                    intent.getStringExtra("kg.tutorialapp.broadcast.Message"),
            Toast.LENGTH_LONG
        ).show()
    }
}