#!/bin/bash
#
# time loop: different wait time between two steps
# i loop: number of repetitions per time value

for time in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
do
for i in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
do
echo check no. ${i}: ${time} milliseconds Thread.sleep
time java TimeCheckThreadSleep << EOF
2000
${time}
EOF
done
done

for time in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
do
for i in 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
do
echo check no. ${i}: ${time} milliseconds ParkNano
time java TimeCheckNano << EOF
2000
${time}
EOF
done
done
