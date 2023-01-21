#!/bin/sh

javac -cp "$(printf %s: ../lib/*.jar)" ByAngleShootingRule.java
javac -cp "$(printf %s: ../lib/*.jar)" RetreatRule.java
javac -cp "$(printf %s: ../lib/*.jar)" MoveTowardsEnemyRule.java
javac -cp "$(printf %s: ../lib/*.jar)" ExtraPhysicsValues.java
javac -cp "$(printf %s: ../lib/*.jar)" ArenaBoundsValidator.java
java -cp "$(printf %s: ../lib/*.jar)":ByAngleShootingRule.class:RetreatRule.class:MoveTowardsEnemyRule.class:ExtraPhysicsValues.class:ArenaBoundsValidator.class IntelligentBot.java

trap "rm *.class" EXIT
