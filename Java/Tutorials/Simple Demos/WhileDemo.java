class WhileDemo {
    public static void main(String[] args){
        int count = 1;
        while (count < 11) {
            System.out.println("Count is: "
                               + count);
            count++;
        }
		
		count = 0;
        while (count < 11) {
			count++;
            System.out.println("Count is: "
                               + count);
        }
		
		count = 1;
        while (count < 11) {
            System.out.println("Count is: "
                               + count);
            ++count;
        }
		
		count = 0;
        while (count < 11) {
			++count;
            System.out.println("Count is: "
                               + count);
        }
    }
}