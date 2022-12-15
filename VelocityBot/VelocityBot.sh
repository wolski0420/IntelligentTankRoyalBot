#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" VelocityBot.java
