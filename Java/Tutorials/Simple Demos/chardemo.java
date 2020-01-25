class chardemo {
	public static void main(String[] args) {
		char disp = 0;
		int counter = 0;
		while (counter < 65536) {
			System.out.print(counter + "   ");
			System.out.println(disp);
			counter++;
			disp++;
		}
	}
}