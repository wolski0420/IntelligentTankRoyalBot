#!/bin/sh
java -cp "$(printf %s: ../lib/*.jar)" TrackFire.java
