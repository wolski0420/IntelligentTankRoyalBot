#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" Fire.java
