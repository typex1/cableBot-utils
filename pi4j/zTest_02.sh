#!/bin/bash
#
# date: 20180128
#
# 4, -800, 1 means: z-stepper with half steps, 2 full cycles clockwise, 1 ms wait period
#
set -x

./run.sh MoveRLZStepper << EOF
4
-800
2
EOF
