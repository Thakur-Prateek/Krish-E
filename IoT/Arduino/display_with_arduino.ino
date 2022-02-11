#include <Adafruit_GFX.h>
#include <Adafruit_SH1106.h>
#include <Adafruit_Sensor.h>
#include "DHT.h"
 
 
#define SCREEN_WIDTH 128 // OLED display width, in pixels
#define SCREEN_HEIGHT 64 // OLED display height, in pixels
 
#define OLED_RESET -1
Adafruit_SH1106 display(OLED_RESET);
 
#define DHTPIN 2     // Digital pin connected to the DHT sensor
 
// Uncomment the type of sensor in use:
#define DHTTYPE    DHT11     // DHT 11
//#define DHTTYPE    DHT22     // DHT 22 (AM2302)
//#define DHTTYPE    DHT21     // DHT 21 (AM2301)
 
DHT dht(DHTPIN, DHTTYPE);
 
void setup() {
  Serial.begin(9600);
  dht.begin();
  display.begin(SH1106_SWITCHCAPVCC, 0x3C);
  delay(2000);
  display.clearDisplay();
  display.setTextColor(WHITE);
}
 
void loop() {



 


 
 
  //read temperature and humidity
  float t = dht.readTemperature();
  float h = dht.readHumidity();
  if (isnan(h) || isnan(t)) {
    Serial.println("Failed to read from DHT sensor!");
  }
 
  //clear display
  display.clearDisplay();
 
  // display temperature
  display.setTextSize(2);
  display.setCursor(30,0);
  display.print("KRISH-E");
  
  
  
  display.setTextSize(1);
  display.setCursor(0,25);
  display.print("Temp.: ");
  display.setTextSize(2);
  display.setCursor(40,25);
  display.print(t);
  display.print(" ");
  display.setTextSize(1);
  display.cp437(true);
  display.write(167);
  display.setTextSize(1);
  display.print("C");
  
  // display humidity
  display.setTextSize(1);
  display.setCursor(0, 50);
  display.print("Hum.: ");
  display.setTextSize(2);
  display.setCursor(40, 50);
  display.print(h);
  display.print(" %"); 
  
  display.display(); 
}
