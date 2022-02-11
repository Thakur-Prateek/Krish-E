


#include <splash.h>
#include <DallasTemperature.h>
#include <OneWire.h>

#define ONE_WIRE_BUS 4       
#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include <dht.h>
dht DHT;

 
#define DHT11_PIN D4
 
// Set these to run example.
#define FIREBASE_HOST "krish-e-fbb5e-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "U3XKyHkcE3pjJw7Oo2gZNXXKFOLG2xwx5pZ61mSS"
#define WIFI_SSID "POCOX3"
#define WIFI_PASSWORD "11111111"
 
   int sensor_pin = A0;

  int output_value ;

OneWire oneWire(ONE_WIRE_BUS);
 
DallasTemperature sensors(&oneWire); 

void setup() {

sensors.begin();

  

   Serial.begin(9600);

   Serial.println("Reading From the Sensor ...");

   delay(2000);

  Serial.begin(9600);
 
  // connect to wifi.
  Serial.begin(9600);

  // connect to wifi.
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("connecting");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("connected: ");
  Serial.println(WiFi.localIP());
  
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
}
    
 
 
 
void loop() {

  sensors.requestTemperatures();                // Send the command to get temperatures  
  Serial.println("Temperature is: ");
  Serial.println(sensors.getTempCByIndex(0));   // Why "byIndex"? You can have more than one IC on the same bus. 0 refers to the first IC on the wire
  
 
  delay(1000);


   output_value= analogRead(A0);

   output_value = map(output_value,550,0,0,100);

   Serial.print("Mositure : ");

   Serial.print(output_value);

   Serial.println("%");

   delay(1000);
   
  float chk = DHT.read11(DHT11_PIN);
  Serial.print("Temperature = ");
  Serial.println(DHT.temperature);
  Serial.print("Humidity = ");
  Serial.println(DHT.humidity);


String temp = String(DHT.temperature);
String hum = String(DHT.humidity);
String moist = String(output_value);
String soilTemp = String(sensors.getTempCByIndex(0));

 
    
  Firebase.setString("temperature", temp);  
  Firebase.setString("humidity", hum); 
  Firebase.setString("Moisture", moist);  
  Firebase.setString("Soil Temperature",soilTemp);      
  
 
  delay(1000);
}
