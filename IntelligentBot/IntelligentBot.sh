#!/bin/sh

trap "rm *.class" EXIT

javac -cp "$(printf %s: ../lib/*.jar)" ByAngleShootingRule.java
javac -cp "$(printf %s: ../lib/*.jar)" RetreatRule.java
javac -cp "$(printf %s: ../lib/*.jar)" MoveTowardsEnemyRule.java
javac -cp "$(printf %s: ../lib/*.jar)" ExtraPhysicsValues.java
java -cp "$(printf %s: ../lib/*.jar)":ByAngleShootingRule.class:RetreatRule.class:MoveTowardsEnemyRule.class:ExtraPhysicsValues.class IntelligentBot.java
