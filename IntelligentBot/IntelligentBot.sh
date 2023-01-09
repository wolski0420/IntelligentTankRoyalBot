#!/bin/sh

trap "rm *.class" EXIT

javac -cp "$(printf %s: ../lib/*.jar)" ByAngleShootingRule.java
javac -cp "$(printf %s: ../lib/*.jar)" RetreatRule.java
java -cp "$(printf %s: ../lib/*.jar)":ByAngleShootingRule.class:RetreatRule.class IntelligentBot.java
