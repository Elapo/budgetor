#!/usr/bin/env bash
java -Dspring.profiles.active=remote -Xms32M -Xmx64M -XX:MaxMetaspaceSize=64M -server -jar budgetor-1.0-SNAPSHOT.jar