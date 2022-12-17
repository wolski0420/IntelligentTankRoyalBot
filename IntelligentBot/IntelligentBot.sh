#!/bin/sh

trap "rm *.class" EXIT

javac -cp "$(printf %s: ../lib/*.jar)" ByAngleShootingRule.java
java -cp "$(printf %s: ../lib/*.jar)":ByAngleShootingRule.class IntelligentBot.java
