import serial
import requests

with serial.Serial('/dev/ttyS3',9600,timeout=1) as ser:
    #flushing the buffer
    ser.readline()
    while (True) :
        line = ser.readline().split()
        if (len(line)<3):
            continue
        light = line[0]
        leftWaterInCm = line[1]
        humidity = line[2]
        print('light '+light+' leftWaterInCm '+leftWaterInCm+' humidity '+humidity)
        url = 'https://mysterious-brushlands-97617.herokuapp.com/data/add'
        objectToSend = {'light':(light), 'leftWaterInCm':(leftWaterInCm), 'humidity':(humidity)}
        headers = {'Content-type': 'application/json'}
        x = requests.post(url, json = objectToSend, headers=headers)
        print(x)
        url = 'https://mysterious-brushlands-97617.herokuapp.com/data/currentThreshold'
        x = str(requests.get(url).content)
        print(x)
        ser.write(x)
