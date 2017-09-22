#!/bin/bash

# CHECK FOR MISSING CLI ARGUMENTS
if [ $# -eq 0 ]; then
    echo
    echo "No program name argument supplied!"
    echo "Use 'run <file.java>'"
    echo
    exit 1;
fi

javac -classpath '.:classes:/opt/pi4j/lib/*' -d . $1
