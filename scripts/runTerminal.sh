#!/bin/bash
java -cp "/home/pi/Desktop/terminal/*" -Dtextillevel.server.ip=192.168.1.250:8080 -Dtextillevel.server.ip2=192.168.1.13:8080 -Dtextillevel.terminal.name=Terminal1 -jar /home/pi/Desktop/terminal/gtl-terminal.jar &
