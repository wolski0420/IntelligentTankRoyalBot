#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" SpinBot.java
