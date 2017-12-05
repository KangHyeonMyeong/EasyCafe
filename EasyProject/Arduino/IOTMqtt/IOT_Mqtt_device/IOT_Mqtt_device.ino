#include "DHT.h"
#include <SPI.h>
#include <WizFi250.h>
#include <PubSubClient.h>

// Update these with values suitable for your network.
//통신 (Mqtt protocol)을 위한 변수
const char* ssid = "class2";
const char* password = "12345678";
const char* mqtt_server = "192.168.0.156";

//각 센서별 사용핀
#define Trash_trig A0
#define Trash_echo A1
#define Durumari A2
#define TissuePin A3
#define SeatPin A4

#define DHTTYPE DHT22
#define TemperPin A6
DHT dht(TemperPin , DHTTYPE);

//Mqtt 통신 사용토픽
const char* publish_status_topic = "/cafe/sensor";
const char* publish_seat_topic = "/cafe/seat";
const char* publish_in_topic = "/cafe/delay";

//와이파이
WiFiClient WizFi250Client;
PubSubClient client(WizFi250Client);

//메세지 버퍼와 딜레이 사용할 변수
long send_period = 5000;
long lastMsg = 0;
char msg_status[150];
char msg_seat[150];

void setup_wifi();
void callback(char* topic, byte* payload, unsigned int length);
void reconnect();

//초기화 셋팅
void setup() {
  Serial.begin(115200);
  //좌석버튼 센싱핀
  for( int i = 22 ; i <= 39 ; i++){
    pinMode( i , INPUT );
  }
  pinMode( TemperPin , INPUT);
  //그외 센서핀
  pinMode(  Trash_trig , OUTPUT );
  pinMode( Trash_echo , INPUT );
  pinMode( TissuePin , INPUT );
  setup_wifi();
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
}

void setup_wifi() {

  delay(10);
  // We start by connecting to a WiFi network
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.init();
  WiFi.begin((char*)ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void callback(char* topic, byte* payload, unsigned int length) {
  Serial.print("Message arrived [");
  Serial.print(topic);
  Serial.print("] ");
  for (int i = 0; i < length; i++) {
    Serial.print((char)payload[i]);
  }
  Serial.println();

  // Switch on the LED if an 1 was received as first character
  if ((char)payload[0] == '1') {
    digitalWrite(10, HIGH);   // Turn the LED on (Note that LOW is the voltage level
    // but actually the LED is on; this is because
    // it is acive low on the ESP-01)
  } else {
    digitalWrite(10, LOW);  // Turn the LED off by making the voltage HIGH
  }
}

void reconnect() {
  // Loop until we're reconnected
  while (!client.connected()) {
    Serial.print("Attempting MQTT connection...");
    // Attempt to connect
    if (client.connect("led-on")) {
      Serial.println("connected");
      client.subscribe(publish_in_topic);
    } else {
      Serial.print("failed, rc=");
      Serial.print(client.state());
      Serial.println(" try again in 5 seconds");
      // Wait 5 seconds before retrying
    }
  }
}

//좌석데이터 버퍼에 넣기
void readSeat(){
  int cursorS = sprintf( msg_seat , "s1:%d" , digitalRead(22));
  for(int i = 2 ; i  <= 20 ; i ++)
  {
    cursorS += sprintf( msg_seat+cursorS , ",s%d:%d" , i , digitalRead( 21+i ) );
  }
}

//센싱하여 버퍼에 넣기
void readStatus(){
  int t = dht.readTemperature();
  int h = dht.readHumidity();
  int tra = trash_level();
  int cursorB = sprintf( msg_status , "temp:%d," , t );
  cursorB += sprintf( msg_status + cursorB , "humi:%d," , h );
  cursorB += sprintf( msg_status + cursorB , "trash:%d," , tra );
  cursorB += sprintf( msg_status + cursorB , "tissue:%d," , tissue_read() );
  cursorB += sprintf( msg_status + cursorB , "toilet:%d," , digitalRead(Durumari) );
  int curS = 0;
  for( int i = 22 ; i < 40 ; i++){
    if(digitalRead(i)) curS++;
  }
  cursorB += sprintf( msg_status + cursorB , "curSeat:%d" , curS );
}

int trash_level(){
  digitalWrite(Trash_trig, LOW);
  digitalWrite(Trash_echo, LOW);
  delayMicroseconds(2);
  digitalWrite(Trash_trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(Trash_trig, LOW);
  unsigned long duration = pulseIn(Trash_echo, HIGH);
  int distance = duration/29.0/2.0;
  return distance;
}

int tissue_read(){
  if(analogRead(TissuePin) > 700 ){
    return 1;
  }else{
    return 0;
  }
}
void loop() {

  if (!client.connected()) {
    reconnect();
  }
  client.loop();

  long now = millis();
  if (now - lastMsg > send_period) {
    lastMsg = now;
    readSeat();
    Serial.println(publish_seat_topic);
    Serial.print("Publish message: ");
    Serial.println(msg_seat);
    client.publish(publish_seat_topic , msg_seat);

    readStatus();
    Serial.println(publish_status_topic);
    Serial.print("Publish message: ");
    Serial.println(msg_status);
    client.publish(publish_status_topic , msg_status);
  }
}


