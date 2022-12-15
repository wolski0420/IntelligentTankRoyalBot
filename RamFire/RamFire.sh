#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" RamFire.java
