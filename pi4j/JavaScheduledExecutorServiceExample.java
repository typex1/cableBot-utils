import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JavaScheduledExecutorServiceExample {

	static void printDate(){
		//System.out.println("hi there at: "+ new java.util.Date()+" "+Integer.toString(i));
		System.out.println("hi there at: "+ new java.util.Date());
	}

	public static void main(String[] args) {
		ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
		int i=0;

		execService.scheduleAtFixedRate(()->{
			//The repetitive task, say to update Database
			//System.out.println("hi there at: "+ new java.util.Date()+" "+Integer.toString(i));
			this.printDate();
			//i=i+1;
		}, 0, 1000L, TimeUnit.MILLISECONDS);
	}
}
