class ArithmeticDemo {

     public static void main (String[] args){
          
          int result = 1 + 2; // result is now 3
		  int newbie = 3;
          System.out.println(result);
		  System.out.println(newbie);

          result = result - 1; // result is now 2
		  newbie -= 1;
          System.out.println(result);
		  System.out.println(newbie);

          result = result * 2; // result is now 4
		  newbie *= 2;
          System.out.println(result);
		  System.out.println(newbie);

          result = result / 2; // result is now 2
		  newbie /= 2;
          System.out.println(result);
		  System.out.println(newbie);

          result = result + 8; // result is now 10
		  newbie += 8;
          result = result % 7; // result is now 3
		  newbie %= 7;
          System.out.println(result);
		  System.out.println(newbie);
     }
}