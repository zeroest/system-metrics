#!/bin/zsh

java -Dserver.port=8880 \
-Dcom.sun.management.jmxremote \
-Dcom.sun.management.jmxremote.port=8888 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false \
-Djava.rmi.server.hostname=localhost \
-jar build/libs/system-metrics-0.0.1.jar

