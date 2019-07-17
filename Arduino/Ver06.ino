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

#define Button1 D4
#define Button2 D5
// Used to count state of button
int buttonPushCounter1 = 0;
int buttonPushCounter2 = 0;
int State_Button1 = 0;
int State_Button2 = 0;
int State_Button1_last = 0;
int State_Button2_last = 0;

int z1, z2;
int State1, State2, State3, State4, State5;
void setup() 
{
  Serial.begin(115200);                                                   // Select the same baud rate if you want to see the datas on Serial Monitor
  pinMode(Relay1, OUTPUT);
  pinMode(Relay2, OUTPUT);
  pinMode(Relay3, OUTPUT);

  pinMode(Button1, INPUT);
  pinMode(Button2, INPUT);
  // define value of relay
  digitalWrite(Relay1, State1);
  digitalWrite(Relay2, State2);
  digitalWrite(Relay3, State3);
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
  // read signal digital of button1;
  State_Button1 = digitalRead(Button1);
  if( State_Button1 != State_Button1_last)
  {
    if( State_Button1 == HIGH)
    {
      buttonPushCounter1++;
      Serial.print("So lan nhan nut nhan so 1 la: ");
      Serial.println(buttonPushCounter1);
    }
    else{
      Serial.print("Button1 off");
    }
  }
  // save last button
  State_Button1_last = State_Button1;
  if(buttonPushCounter1 % 4 == 0)
  {
    int z = 1;
  }
  else {
    int z1 = 0;
  }

  State_Button2 = digitalRead(Button2);
  if(State_Button2 != State_Button2_last)
  {
    if(State_Button2 == HIGH)
    {
      buttonPushCounter2++;
      Serial.print("So lan nhan nut2 la: ");
      Serial.println(buttonPushCounter2);
    }
    else
    {
      Serial.print("Button2 is off");
    }
  }
  State_Button2_last = State_Button2; 
  if(buttonPushCounter2 % 4 == 0)
  {
    int z2 = 1;
  }
  else
  {
    int z2 = 0;
  }
   
  if((State1 == "on") || ( z1 == 1))
  {
    digitalWrite(Relay1, HIGH);
  }
  else if( (State1 == "off") || ( z1 == 0))
  {
    digitalWrite(Relay1, LOW);
  }
  Serial.print("Fan 1 is: ");
  Serial.println(State1);
  if((State2 == "on")||( z2 == 1))
  {
    digitalWrite(Relay2, HIGH);
  }
  else if((State2 == "off") || (z2 == 0))
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
