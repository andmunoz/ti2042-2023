package cl.inacap.apps.mqttexample

import android.widget.TextView
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence

class MqttClientHelper {
    // Aquí se configuran la información del servidor y cliente MQTT
    private val SERVER_URI = "tcp://mqtt.eclipseprojects.io:1883"
    private val CLIENT_ID = "andmunoz_mqtt"

    // Aquí se definen los tópico para el intercambio de mensajes
    companion object {
        const val SENSOR_TOPIC = "SensorTopic"  // Tópico para el sensor
        const val DEVICE_TOPIC = "DeviceTopic"  // Tópico para el dispositivo
    }

    // Declaramos e inicializamos la instancia con el servidor MQTT
    private lateinit var mqttClient: MqttClient
    init { connectToMqttBroker() }
    private fun connectToMqttBroker() {
        try {
            print("Connecting to $SERVER_URI... ")
            val persistence = MemoryPersistence()
            mqttClient = MqttClient(SERVER_URI, CLIENT_ID, persistence)
            val options = MqttConnectOptions()
            options.isCleanSession = true
            mqttClient.connect(options)
            println("OK!")
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Acción de suscribirse a un tópico
    fun subscribeToTopic(topic: String, messageView: TextView) {
        try {
            print("[$topic] Subscribing to topic... ")
            mqttClient.subscribe(topic) { _, message ->
                val payload = String(message.payload)
                messageView.append("[$topic] $payload\n")
                println("[$topic] Message received: $payload")
            }
            println("OK!")
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Acción que envía un mensaje a un tópico
    fun publishMessage(topic: String, message: String) {
        try {
            print("[$topic] Sending Message: $message... ")
            val mqttMessage = MqttMessage(message.toByteArray())
            mqttClient.publish(topic, mqttMessage)
            println("OK!")
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }

    // Cerramos la conexión con el servicio MQTT
    fun disconnect() {
        print("Disconnecting from $SERVER_URI... ")
        mqttClient.disconnect()
        println("OK!")
    }
}