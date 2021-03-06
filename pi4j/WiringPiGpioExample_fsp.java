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

public class WiringPiGpioExample_fsp {

    private static final int SERVOUP =      11;
    private static final int SERVODOWN=     5;
    private static final int LEFT_STEPPER01=11;
    private static final int LEFT_STEPPER02=10;
    private static final int LEFT_STEPPER03= 6;
    private static final int LEFT_STEPPER04=14;
    private static final int RIGHT_STEPPER01= 2;
    private static final int RIGHT_STEPPER02= 3;
    private static final int RIGHT_STEPPER03=12;
    private static final int RIGHT_STEPPER04=13;
    private static int StepX = 0;
    private static final int STEP_PAUSE = 1;

    static void makeStepRight(int direction) throws InterruptedException {
      StepX += direction;

      if(StepX > 3){
        StepX = 0;
      }
      if(StepX < 0){
        StepX = 3;
      }

      if(StepX == 0){
        //System.out.println("step 0");
        Gpio.digitalWrite(RIGHT_STEPPER01, 1);
        Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(RIGHT_STEPPER02, 0);
        Gpio.digitalWrite(RIGHT_STEPPER03, 0);
        Gpio.digitalWrite(RIGHT_STEPPER04, 0);
      }
      if(StepX == 1){
        //System.out.println("step 1");
        Gpio.digitalWrite(RIGHT_STEPPER03, 1);
        Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(RIGHT_STEPPER01, 0);
        Gpio.digitalWrite(RIGHT_STEPPER02, 0);
        Gpio.digitalWrite(RIGHT_STEPPER04, 0);
      }
      if(StepX == 2){
        //System.out.println("step 2");
        Gpio.digitalWrite(RIGHT_STEPPER02, 1);
        Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(RIGHT_STEPPER01, 0);
        Gpio.digitalWrite(RIGHT_STEPPER03, 0);
        Gpio.digitalWrite(RIGHT_STEPPER04, 0);
      }
      if(StepX == 3){
        //System.out.println("step 3");
        Gpio.digitalWrite(RIGHT_STEPPER04, 1);
        Thread.sleep(STEP_PAUSE);
        Gpio.digitalWrite(RIGHT_STEPPER01, 0);
        Gpio.digitalWrite(RIGHT_STEPPER02, 0);
        Gpio.digitalWrite(RIGHT_STEPPER03, 0);
      }

      Thread.sleep(STEP_PAUSE);
    }

    static void motorsOff(){
      Gpio.digitalWrite(RIGHT_STEPPER01, 0);
      Gpio.digitalWrite(RIGHT_STEPPER02, 0);
      Gpio.digitalWrite(RIGHT_STEPPER03, 0);
      Gpio.digitalWrite(RIGHT_STEPPER04, 0);

      Gpio.digitalWrite(LEFT_STEPPER03, 0);
      Gpio.digitalWrite(LEFT_STEPPER01, 0);
      Gpio.digitalWrite(LEFT_STEPPER02, 0);
      Gpio.digitalWrite(LEFT_STEPPER04, 0);
    }

    public static void main(String args[]) throws InterruptedException {
        int width = 1000;

        System.out.println("<--Pi4J--> GPIO test program");

        // setup wiringPi
        if (Gpio.wiringPiSetup() == -1) {
            System.out.println(" ==>> GPIO SETUP FAILED");
            return;
        }

        // set relevant GPIO as outputs
        GpioUtil.export(RIGHT_STEPPER01, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(RIGHT_STEPPER01, Gpio.OUTPUT);
        GpioUtil.export(RIGHT_STEPPER02, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(RIGHT_STEPPER02, Gpio.OUTPUT);
        GpioUtil.export(RIGHT_STEPPER03, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(RIGHT_STEPPER03, Gpio.OUTPUT);
        GpioUtil.export(RIGHT_STEPPER04, GpioUtil.DIRECTION_OUT);
        Gpio.pinMode(RIGHT_STEPPER04, Gpio.OUTPUT);


        for(int i=0; i < width; i++){
          makeStepRight(-1);
        }
        for(int i=0; i < width; i++){
          makeStepRight(1);
        }

        motorsOff();

     }
}
