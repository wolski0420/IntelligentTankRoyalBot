#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" Target.java
