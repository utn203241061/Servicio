package io.github.utn203241061

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL


class MainActivity :
AppCompatActivity() {
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mensaje =
            findViewById<TextView>(R.id.mensaje)
        Thread {
            val connection =
                URL("http://holaphp.tichomon-pbpb.repl.co/")
                    .openConnection()
                        as HttpURLConnection
            connection.connect()
            val code =
                connection.responseCode;
            if (200 <= code && code < 300) {
                val text =
                    connection.inputStream.use {
                        it.reader()
                            .use { reader -> reader.readText() }
                    }
                mensaje.post {
                    mensaje.text = text
                }
            } else {
                mensaje.post {
                    mensaje.text =
                        connection.responseMessage
                }
            }
            connection.disconnect()
        }.start()
    }
}