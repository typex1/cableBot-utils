class ShowProperties {
    public static void main(String[] args) {
        //System.getProperties().list(System.out);
		if (System.getProperty("os.arch").toString() == "arm")
			System.out.println("Raspi");
		else
			System.out.println("not Raspi, maybe mac");

			
        System.out.println("out: "+System.getProperty("os.arch").toString());
    }
}
