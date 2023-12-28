import paho.mqtt.client as mqtt
from config import SERVER, PORT, CLIENT_ID, SENSOR

SERVER = 'test.mosquito.org'
PORT = 1883
CLIENT_ID = 'andmunoz_mqtt'
SENSOR = 'SensorTopic'
DEVICE = 'DeviceTopic'

# The callback for when the client receives a CONNACK response from the server.
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected to ", SERVER)
    else:
        print("Connection Error %d", rc)

# The callback for when a PUBLISH message is received from the server.
def on_message(client, userdata, msg):
    print("[", msg.topic, "]: ", msg.payload)
    
# The callback for whan PUBLISH a message to the server
def on_publish(client, userdata, result):
    print("Data published!\n")

client = mqtt.Client(CLIENT_ID)
client.connect(SERVER, PORT, 60)

client.on_connect = on_connect
client.on_message = on_message
client.on_publish = on_publish

client.subscribe(SENSOR)
client.subscribe(DEVICE)

result = client.publish(SENSOR, "50")
if result[0] == 0:
    print("Message sent to topic", SENSOR)
else:
    print("Publishing Error %d", result[0])

# Blocking call that processes network traffic, dispatches callbacks and
# handles reconnecting.
# Other loop*() functions are available that give a threaded interface and a
# manual interface.
client.loop_forever()