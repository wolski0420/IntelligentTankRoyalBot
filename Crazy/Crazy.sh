#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" Crazy.java
