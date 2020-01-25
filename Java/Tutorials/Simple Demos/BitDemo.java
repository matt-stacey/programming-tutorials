class BitDemo {
    public static void main(String[] args) {
        int bitmask = 0x000F; // 15 or 0b0000000000001111
        int val = 0x2222; // 8738   or 0b0010001000100010
        // & == AND                 or 0b0000000000000010
		System.out.println(val);
		System.out.println(bitmask);
        System.out.println(val & bitmask);
    }
}