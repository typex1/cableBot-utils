public class OSArchValidator {

	private static String OS = System.getProperty("os.arch").toLowerCase();

	public static void main(String[] args) {

		System.out.println(OS);

		if (isArm()) {
			System.out.println("This is arm");
		} else {
			System.out.println("Your OS is not arm!!");
		}
	}

	public static boolean isArm() {

		return (OS.indexOf("arm") >= 0);

	}

}
