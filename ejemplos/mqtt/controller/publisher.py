import paho.mqtt.client as paho
from config import SERVER, PORT, CLIENT_ID, SENSOR, DEVICE

def on_publish(client, userdata, result):
    print("data published \n")

client1= paho.Client(CLIENT_ID)
client1.on_publish = on_publish
client1.connect(SERVER, PORT)
result = client1.publish(SENSOR, "50")