
//pins used for detecting light level
const int lightSensorPin = A0;
//pins used for detecting the distance from the top of the water reservoir to the current water level
const int triggerPin = 13;
const int echoPin = 12;
//pins used for reading humidity in the soil
const int humiditySensorPin = A1;
//motor pin
const int motorPin = 6;

int tresholdHumidity=1024;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600);
  //Serial.print("start");
  //setting up sound sensor
  pinMode(triggerPin, OUTPUT);
  pinMode(echoPin, INPUT);
  //setting up motor
  pinMode(motorPin,OUTPUT);
  //digitalWrite(motorPin, LOW);
  //tresholdHumidity=1024;
}
//activates the motor for the specified time in miliseconds
void activateMotorForTime(int miliseconds){
  digitalWrite(motorPin, HIGH);
  delay(miliseconds);
  digitalWrite(motorPin, LOW);
}
//calculates distance from the top of the water reservoir to the current water level
long getDistance(){
  // The sensor is triggered by a HIGH pulse of 10 or more microseconds.
  // Give a short LOW pulse beforehand to ensure a clean HIGH pulse:
  digitalWrite(triggerPin, LOW);
  delayMicroseconds(5);
  digitalWrite(triggerPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(triggerPin, LOW);
 
  // Read the signal from the sensor: a HIGH pulse whose
  // duration is the time (in microseconds) from the sending
  // of the ping to the reception of its echo off of an object.
  pinMode(echoPin, INPUT);
  long duration = pulseIn(echoPin, HIGH);
 
  // Convert the time into a distance
  return (duration/2) / 29.1;     // Divide by 29.1 or multiply by 0.0343
}
int getHumidity(){
  return analogRead(humiditySensorPin);
}
int getLight(){
  return analogRead(lightSensorPin);
}
//the ordered triple of light, distance and humidity send over serial com
char buff[50];

// the loop routine runs over and over again forever:
void loop() {
  //digitalWrite(motorPin, HIGH);
  if( Serial.available() > 0 ) {
    tresholdHumidity = Serial.parseInt();  
  }
  
 
  //Serial.println(tresholdHumidity);
  int light = getLight();
  int distance = getDistance();
  int humidity = getHumidity();
  //motor activation
  if(humidity>tresholdHumidity){
      digitalWrite(motorPin, HIGH);
  } else {
      digitalWrite(motorPin, LOW);
  }
  //print out the value you read:
  int charCount = sprintf(buff,"%d %d %d %d \n",light,distance,humidity,tresholdHumidity);

  Serial.print(buff);
  delay(1000);        // delay in between serial reads for stability
}
