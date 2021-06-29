#!/bin/bash
## usage : ./perform.sh (caseNo) (producer) (comsumer) [same|each]

if [ $4 == same ]; then

for i in `seq $3`
do
echo ./comsumer.sh $1 $i 1
 ./comsumer.sh $1 $i 1
done

for i in `seq $2`
do
echo ./producer.sh $1 $i 1
 ./producer.sh $1 $i 1
done


else

for i in `seq $3`
do
echo ./comsumer.sh $1 $i $i
 ./comsumer.sh $1 $i $((($i-1) % $2 + 1))
done

for i in `seq $2`
do
echo ./producer.sh $1 $i $i
 ./producer.sh $1 $i $i
done


fi