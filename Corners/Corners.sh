#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" Corners.java
