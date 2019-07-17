// coppy ver 01
#include <ESP8266WiFi.h>
#include<FirebaseArduino.h>
// Host address
#define FIREBASE_HOST "version02-06-06-2019.firebaseio.com"                     //Your Firebase Project URL goes here without "http:" , "\" and "/"
#define FIREBASE_AUTH "4NngSjZZZxeTAwKVYgRmYJc6U9yJi79A0LSMm24k"       //Your Firebase Database Secret goes here
#define WIFI_SSID "Cherry F2"             //your WiFi SSID for which yout NodeMCU connects
#define WIFI_PASSWORD "yeuanhthivao"        //Password of your wifi network 
 
#define Relay1 D0
#define Relay2 D1
#define Relay3 D3
#define Relay4 D4
#define Relay5 D5

int val1, val2, val3, val4;
int State1, State2, State3, State4, State5;
void setup() 
{
  Serial.begin(115200);                                                   // Select the same baud rate if you want to see the datas on Serial Monitor
  pinMode(Relay1, OUTPUT);
  pinMode(Relay2, OUTPUT);
  pinMode(Relay3, OUTPUT);
  pinMode(Relay4, OUTPUT);
  // define value of relay
  digitalWrite(Relay1, State1);
  digitalWrite(Relay2, State2);
  digitalWrite(Relay3, State3);
  digitalWrite(Relay4, State4);
  WiFi.begin(WIFI_SSID,WIFI_PASSWORD);
  Serial.print("Dang ket noi...");
  while (WiFi.status()!=WL_CONNECTED){
    Serial.print(".");
    delay(500);
  }
  Serial.println();
  Serial.print("Da ket noi: ");
  Serial.println(WiFi.localIP());
  // Firebase setup
  Firebase.begin(FIREBASE_HOST,FIREBASE_AUTH);
  
}
void firebasereconnect()
{
    Serial.println("Dang co gang ket noi lai");
    Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
 }

void loop() 
{
   if (Firebase.failed())
      {
      Serial.print("setting number failed:");
      Serial.println(Firebase.error());
      firebasereconnect();
      return;
      }
  // Setup Firebase web, have child
  String State1 = Firebase.getString("HOME/fan/value");
  String State2 = Firebase.getString("HOME/light/value");
  String allDevice = Firebase.getString("HOME/all devices/value");
    
  if(State1 == "on")
  {
    digitalWrite(Relay1, HIGH);
  }
  else if( State1 == "off")
  {
    digitalWrite(Relay1, LOW);
  }
  Serial.print("Fan 1 is: ");
  Serial.println(State1);
  if(State2 == "on")
  {
    digitalWrite(Relay2, HIGH);
  }
  else if(State2 == "off")
  {
    digitalWrite(Relay2, LOW);
  }
  Serial.print("Light is: ");
  Serial.println(State2);
  if(allDevice == "on")
  {
    Firebase.setString("HOME/fan/value", "on");
    Firebase.setString("HOME/light/value", "on");
    Serial.print("allDevice is on");
    Firebase.setString("HOME/all devices/value", "NULL");
  }
  else if(allDevice == "off")
  {
    Firebase.setString("HOME/fan/value", "off");
    Firebase.setString("HOME/light/value", "off");
    Serial.print("allDevice is off");
    Firebase.setString("HOME/all devices/value", "NULL");
  }
  Serial.print("\n");
}
