import java.util.concurrent.locks.LockSupport;
import java.util.Scanner;

public class TimeCheckThreadSleep {

    private static int STEP_PAUSE = 4;//original value from C program: 1.5
    private static int StepX = 0;

    //from https://www.programcreek.com/java-api-examples/index.php?class=java.util.concurrent.locks.LockSupport&method=parkNanos
    private static void sleepMillis(long delayMillis) {
        if (delayMillis <= 0) {
            return;
        }

        long nanos = delayMillis * 1000000;
        LockSupport.parkNanos(nanos);
    }

    static void makeStep(int direction) throws InterruptedException {
      StepX += direction;

      if(StepX > 3){
        StepX = 0;
      }
      if(StepX < 0){
        StepX = 3;
      }

      if(StepX == 0){
        //System.out.println("step 0");
        Thread.sleep(STEP_PAUSE);
        //sleepMillis(STEP_PAUSE);
      }
      if(StepX == 1){
        //System.out.println("step 1");
        Thread.sleep(STEP_PAUSE);
        //sleepMillis(STEP_PAUSE);
      }
      if(StepX == 2){
        //System.out.println("step 2");
        //Thread.sleep(STEP_PAUSE);
        sleepMillis(STEP_PAUSE);
      }
      if(StepX == 3){
        //System.out.println("step 3");
        Thread.sleep(STEP_PAUSE);
        //sleepMillis(STEP_PAUSE);
      }

      //Thread.sleep(STEP_PAUSE);
      //sleepMillis(STEP_PAUSE);
    }

    public static void main(String args[]) throws InterruptedException {
        //int width = 1000;
	Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\\n");
	System.out.print("Enter number of steps (width): ");
	int width = scanner.nextInt();
	System.out.print("Enter value for STEP_PAUSE: ");
	STEP_PAUSE = scanner.nextInt();

        System.out.println("time test program");

	// sleep 1 second after wiringPi init:
	//Thread.sleep(1000);

	// sleep 1 second after pinMode init:
	//Thread.sleep(1000);


        if (width > 0){
		for(int i=0; i < width; i++){
          		makeStep(-1);
		}
        }else{
        	for(int i=width; i < 0; i++){
          		makeStep(1);
        	}
	}

     }
}
