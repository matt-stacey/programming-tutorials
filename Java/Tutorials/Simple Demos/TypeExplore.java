class TypeExplore {
	public static void main(String[] args) {
	
		byte byteLimit;
		short shortLimit;
		int intLimit;
		long longLimit;
		float dollars;
		double cents;
		boolean isFalse;
		char spotMark;
	
		/*
		System.out.println(byteLimit);
		System.out.println(shortLimit);
		System.out.println(intLimit);
		System.out.println(longLimit);
		System.out.println(dollars);
		System.out.println(cents);
		System.out.println(isFalse);
		System.out.println(spotMark);
		// won't let this happen if they haven't been initialized
		*/
		
		byteLimit = 127;
		shortLimit = 32767;
		intLimit = 2147483647;
		longLimit = 2147483647;
		dollars = 54;
		cents = .2605;
		isFalse = true;
		spotMark = 'X';
	
		System.out.println(byteLimit);
		System.out.println(shortLimit);
		System.out.println(intLimit);
		System.out.println(longLimit);
		System.out.println(dollars);
		System.out.println(cents);
		System.out.println(isFalse);
		System.out.println(spotMark);
		
		int twentySix = 26;
		int twentySixHex = 0x1a;
		int twentySixBin = 0b11010;
		
		System.out.println(twentySix);
		System.out.println(twentySixHex);
		System.out.println(twentySixBin);
		
	}
}