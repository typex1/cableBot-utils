/*
 * #%L
 * **********************************************************************
 * ORGANIZATION  :  Pi4J
 * PROJECT       :  Pi4J :: Java Examples
 * FILENAME      :  WiringPiGpioExample.java
 *
 * This file is part of the Pi4J project. More information about
 * this project can be found here:  http://www.pi4j.com/
 * **********************************************************************
 * %%
 * Copyright (C) 2012 - 2016 Pi4J
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 *
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.GpioUtil;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class MoveRLZStepper {

    private static final int SERVOUP =      11;
    private static final int SERVODOWN=     5;
    private static final int L_STEPPER01=11;
    private static final int L_STEPPER02=10;
    private static final int L_STEPPER03= 6;
    private static final int L_STEPPER04=14;
    private static final int R_STEPPER01= 2;
    private static final int R_STEPPER02= 3;
    private static final int R_STEPPER03=12;
    private static final int R_STEPPER04=13;
    private static final int Z_STEPPER01= 26;
    private static final int Z_STEPPER02= 27;
    private static final int Z_STEPPER03= 28;
    private static final int Z_STEPPER04= 29;
    private static int StepL = 0;
    private static int StepR = 0;
    private static int StepZ = 0;
    //private static final int STEP_PAUSE = 4;//original value from C program: 1.5 milliseconds
    private static int STEP_PAUSE = 4;//original value from C program: 1.5

    //from https://www.programcreek.com/java-api-examples/index.php?class=java.util.concurrent.locks.LockSupport&method=parkNanos
    private static void sleepMillis(long delayMillis) {
        if (delayMillis <= 0) {
            return;
        }

        long nanos = delayMillis * 1000000;
        LockSupport.parkNanos(nanos);
    }

    static void makeStepL(int direction) throws InterruptedException {
      StepL += direction;

      if(StepL > 3){
        StepL = 0;
      }
      if(StepL < 0){
        StepL = 3;
      }

      if(StepL == 0){
        //System.out.println("step 0");
        Gpio.digitalWrite(L_STEPPER01, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(L_STEPPER02, 0);
        Gpio.digitalWrite(L_STEPPER03, 0);
        Gpio.digitalWrite(L_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepL == 1){
        //System.out.println("step 1");
        Gpio.digitalWrite(L_STEPPER03, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(L_STEPPER01, 0);
        Gpio.digitalWrite(L_STEPPER02, 0);
        Gpio.digitalWrite(L_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepL == 2){
        //System.out.println("step 2");
        Gpio.digitalWrite(L_STEPPER02, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(L_STEPPER01, 0);
        Gpio.digitalWrite(L_STEPPER03, 0);
        Gpio.digitalWrite(L_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepL == 3){
        //System.out.println("step 3");
        Gpio.digitalWrite(L_STEPPER04, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(L_STEPPER01, 0);
        Gpio.digitalWrite(L_STEPPER02, 0);
        Gpio.digitalWrite(L_STEPPER03, 0);
        sleepMillis(STEP_PAUSE);
      }
    }

    static void makeStepR(int direction) throws InterruptedException {
      StepR += direction;

      if(StepR > 3){
        StepR = 0;
      }
      if(StepR < 0){
        StepR = 3;
      }

      if(StepR == 0){
        //System.out.println("step 0");
        Gpio.digitalWrite(R_STEPPER01, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(R_STEPPER02, 0);
        Gpio.digitalWrite(R_STEPPER03, 0);
        Gpio.digitalWrite(R_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepR == 1){
        //System.out.println("step 1");
        Gpio.digitalWrite(R_STEPPER03, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(R_STEPPER01, 0);
        Gpio.digitalWrite(R_STEPPER02, 0);
        Gpio.digitalWrite(R_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepR == 2){
        //System.out.println("step 2");
        Gpio.digitalWrite(R_STEPPER02, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(R_STEPPER01, 0);
        Gpio.digitalWrite(R_STEPPER03, 0);
        Gpio.digitalWrite(R_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepR == 3){
        //System.out.println("step 3");
        Gpio.digitalWrite(R_STEPPER04, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(R_STEPPER01, 0);
        Gpio.digitalWrite(R_STEPPER02, 0);
        Gpio.digitalWrite(R_STEPPER03, 0);
        sleepMillis(STEP_PAUSE);
      }
    }

    static void makeStepZ(int direction) throws InterruptedException {
      StepZ += direction;

      if(StepZ > 3){
        StepZ = 0;
      }
      if(StepZ < 0){
        StepZ = 3;
      }

      if(StepZ == 0){
        //System.out.println("step 0");
        Gpio.digitalWrite(Z_STEPPER01, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(Z_STEPPER02, 0);
        Gpio.digitalWrite(Z_STEPPER03, 0);
        Gpio.digitalWrite(Z_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepZ == 1){
        //System.out.println("step 1");
        Gpio.digitalWrite(Z_STEPPER03, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(Z_STEPPER01, 0);
        Gpio.digitalWrite(Z_STEPPER02, 0);
        Gpio.digitalWrite(Z_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepZ == 2){
        //System.out.println("step 2");
        Gpio.digitalWrite(Z_STEPPER02, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(Z_STEPPER01, 0);
        Gpio.digitalWrite(Z_STEPPER03, 0);
        Gpio.digitalWrite(Z_STEPPER04, 0);
        sleepMillis(STEP_PAUSE);
      }
      if(StepZ == 3){
        //System.out.println("step 3");
        Gpio.digitalWrite(Z_STEPPER04, 1);
        //Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(Z_STEPPER01, 0);
        Gpio.digitalWrite(Z_STEPPER02, 0);
        Gpio.digitalWrite(Z_STEPPER03, 0);
        sleepMillis(STEP_PAUSE);
      }
    }

    static void motorsOff(){
      Gpio.digitalWrite(Z_STEPPER01, 0);
      Gpio.digitalWrite(Z_STEPPER02, 0);
      Gpio.digitalWrite(Z_STEPPER03, 0);
      Gpio.digitalWrite(Z_STEPPER04, 0);

      Gpio.digitalWrite(L_STEPPER03, 0);
      Gpio.digitalWrite(L_STEPPER01, 0);
      Gpio.digitalWrite(L_STEPPER02, 0);
      Gpio.digitalWrite(L_STEPPER04, 0);

      Gpio.digitalWrite(R_STEPPER03, 0);
      Gpio.digitalWrite(R_STEPPER01, 0);
      Gpio.digitalWrite(R_STEPPER02, 0);
      Gpio.digitalWrite(R_STEPPER04, 0);
    }

    public static void main(String args[]) throws InterruptedException {
        //int width = 1000;
	Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
	System.out.print("Enter 1,2,3 for stepper R,L,Z (stepper): ");
	int stepper = scanner.nextInt();
	System.out.print("Enter number of steps (width): ");
	int width = scanner.nextInt();
	System.out.print("Enter value for STEP_PAUSE: ");
	STEP_PAUSE = scanner.nextInt();

        System.out.println("<--Pi4J--> GPIO test program");

        // setup wiringPi
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }
	// sleep some time after wiringPi init:
	//Thread.sleep(1000);
	sleepMillis(200);

        // set relevant GPIO as outputs
        GpioUtil.export(L_STEPPER01, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(L_STEPPER01, Gpio.OUTPUT);
        GpioUtil.export(L_STEPPER02, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(L_STEPPER02, Gpio.OUTPUT);
        GpioUtil.export(L_STEPPER03, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(L_STEPPER03, Gpio.OUTPUT);
        GpioUtil.export(L_STEPPER04, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(L_STEPPER04, Gpio.OUTPUT);
        GpioUtil.export(R_STEPPER01, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(R_STEPPER01, Gpio.OUTPUT);
        GpioUtil.export(R_STEPPER02, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(R_STEPPER02, Gpio.OUTPUT);
        GpioUtil.export(R_STEPPER03, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(R_STEPPER03, Gpio.OUTPUT);
        GpioUtil.export(R_STEPPER04, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(Z_STEPPER04, Gpio.OUTPUT);
        GpioUtil.export(Z_STEPPER01, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(Z_STEPPER01, Gpio.OUTPUT);
        GpioUtil.export(Z_STEPPER02, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(Z_STEPPER02, Gpio.OUTPUT);
        GpioUtil.export(Z_STEPPER03, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(Z_STEPPER03, Gpio.OUTPUT);
        GpioUtil.export(Z_STEPPER04, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(Z_STEPPER04, Gpio.OUTPUT);

	// sleep some time after pinMode init:
	//Thread.sleep(1000);
	sleepMillis(200);


        if (width > 0){
		for(int i=0; i < width; i++){
			if(stepper == 1){
          			makeStepR(-1);
			}
			if(stepper == 2){
          			makeStepL(-1);
			}
			if(stepper == 3){
          			makeStepZ(-1);
			}
		}
        }else{
        	for(int i=width; i < 0; i++){
			if(stepper == 1){
          			makeStepR(1);
			}
			if(stepper == 2){
          			makeStepL(1);
			}
			if(stepper == 3){
          			makeStepZ(1);
			}
        	}
	}

        motorsOff();
     }
}
