#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" IntelligentBot.java
