package cl.inacap.apps.sumativa4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    private lateinit var redAboveStatusRadio: RadioButton
    private lateinit var yellowAboveStatusRadio: RadioButton
    private lateinit var greenStatusRadio: RadioButton
    private lateinit var yellowBelowStatusRadio: RadioButton
    private lateinit var redBelowStatusRadio: RadioButton
    private lateinit var deviceStatusTextView: TextView
    private lateinit var humidifyButton: Button
    private lateinit var dehumidifyButton: Button
    private lateinit var offButton: Button

    private lateinit var mqttClient: MqttClientHelper
    private lateinit var statusRadioList: ArrayList<RadioButton>
    private var humidityValue: Int = 50
    private var deviceStatus: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statusRadioList = ArrayList<RadioButton>()
        redAboveStatusRadio = findViewById(R.id.redAboveStatusRadio)
        statusRadioList.add(redAboveStatusRadio)
        yellowAboveStatusRadio = findViewById(R.id.yellowAboveStatusRadio)
        statusRadioList.add(yellowAboveStatusRadio)
        greenStatusRadio = findViewById(R.id.greenStatusRadio)
        statusRadioList.add(greenStatusRadio)
        yellowBelowStatusRadio = findViewById(R.id.yellowBelowStatusRadio)
        statusRadioList.add(yellowBelowStatusRadio)
        redBelowStatusRadio = findViewById(R.id.redBelowStatusRadio)
        statusRadioList.add(redBelowStatusRadio)

        mqttClient = MqttClientHelper()
        mqttClient.subscribeToTopic(MqttClientHelper.SENSOR_TOPIC, statusRadioList)

        deviceStatusTextView = findViewById(R.id.deviceStatusTextView)
        humidifyButton = findViewById(R.id.humidifyButton)
        dehumidifyButton = findViewById(R.id.dehumidifyButton)
        offButton = findViewById(R.id.offButton)

        humidifyButton.setOnClickListener {
            mqttClient.publishMessage(MqttClientHelper.DEVICE_TOPIC, "HUMIDIFY")
            deviceStatus = 1
            deviceStatusTextView.text = resources.getText(R.string.humidificateStatusText)
        }

        dehumidifyButton.setOnClickListener {
            mqttClient.publishMessage(MqttClientHelper.DEVICE_TOPIC, "DEHUMIDIFY")
            deviceStatus = -1
            deviceStatusTextView.text = resources.getText(R.string.dehumidificateStatusText)
        }

        offButton.setOnClickListener {
            mqttClient.publishMessage(MqttClientHelper.DEVICE_TOPIC, "OFF")
            deviceStatus = 0
            deviceStatusTextView.text = resources.getText(R.string.offStatusText)
        }

        GlobalScope.launch(context = Dispatchers.Main) {
            deviceOperation(1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mqttClient.disconnect()
    }

    private suspend fun deviceOperation(sleepTime: Long) {
        while(true) {
            humidityValue += 5 * deviceStatus
            if (humidityValue > 100) humidityValue = 100
            else if (humidityValue < 0) humidityValue = 0

            val humidityStatus: String = if (humidityValue < 15) "RED-"
            else if (humidityValue < 30) "YELLOW-"
            else if (humidityValue < 65) "GREEN"
            else if (humidityValue < 75) "YELLOW+"
            else "RED+"

            mqttClient.publishMessage(MqttClientHelper.SENSOR_TOPIC, humidityStatus)
            delay(sleepTime)
        }
    }
}