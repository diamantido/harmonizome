#!/bin/bash

gradle --stop
gradle build || exit 1
gradle build -t &
BUILD_PID=$!
gradle tomcatRun --stacktrace
