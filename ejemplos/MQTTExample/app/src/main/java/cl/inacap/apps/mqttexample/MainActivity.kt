package cl.inacap.apps.mqttexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Declarmos los objetos de la pantalla
    private lateinit var messageView: TextView
    private lateinit var messageText: EditText
    private lateinit var sendButton: Button

    // Declaramos un objeto que representa la conexión MQTT
    private lateinit var mqttClient: MqttClientHelper
    private val sensorTopic = MqttClientHelper.SENSOR_TOPIC
    private val deviceTopic = MqttClientHelper.DEVICE_TOPIC

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hacemos referencia a los elementos del screen
        messageView = findViewById(R.id.messageView)
        messageText = findViewById(R.id.messageText)
        sendButton = findViewById(R.id.sendButton)

        // Creamos la referencia al cliente MQTT
        mqttClient = MqttClientHelper()
        messageView.append(" OK!\n")

        // Suscribimos al tópico y enviamos el primer mensaje
        mqttClient.publishMessage(sensorTopic, "GREEN+")
        mqttClient.subscribeToTopic(deviceTopic, messageView)

        // Construimos la lógica cuando se presiona "Enviar"
        sendButton.setOnClickListener {
            messageView.append("Sending Message to $sensorTopic... ")
            mqttClient.publishMessage(sensorTopic, messageText.text.toString())
            messageView.append("OK!\n")
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // Cerramos la conexión con el servicio MQTT
        mqttClient.disconnect()
    }
}