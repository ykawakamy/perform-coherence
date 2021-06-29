#!/bin/sh
classpath=".\\target\\classes\\;C:\\Users\\y.kawakami\\.m2\\repository\\com\\oracle\\coherence\\ce\\coherence\\20.12\\coherence-20.12.jar;C:\\Users\\y.kawakami\\.m2\\repository\\org\\slf4j\\slf4j-api\\1.7.31\\slf4j-api-1.7.31.jar;C:\\Users\\y.kawakami\\.m2\\repository\\ch\\qos\\logback\\logback-classic\\1.2.3\\logback-classic-1.2.3.jar;C:\\Users\\y.kawakami\\.m2\\repository\\ch\\qos\\logback\\logback-core\\1.2.3\\logback-core-1.2.3.jar"
#classpath='./target/classes/:/c/Users/y.kawakami/.m2/repository/com/oracle/coherence/ce/coherence/20.12/coherence-20.12.jar:/c/Users/y.kawakami/.m2/repository/org/slf4j/slf4j-api/1.7.31/slf4j-api-1.7.31.jar:/c/Users/y.kawakami/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/c/Users/y.kawakami/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar'

java -cp $classpath -Dccs.perform.key=key$3 -Dccs.perform.iterate=-1 ccs.coherence.keyperform.SpecKeyListenConsumerMain > log/No$1-con$2.log &
