#include <iostream>
using namespace std;

int main()
{
	int number = 0;
	
	cout << "enter a number: ";
	cin >> number;
	
	int result = ((number << 1) + 5) << 1;
	cout << "((number << 1) + 5) << 1 = " << result << endl;
	
	bool a =0, b = 0;
	cout << "enter bool a: ";
	cin >> a;
	cout << "enter bool b: ";
	cin >> b;
	
	cout << "bitwise operators:" << endl;
	cout << "a and b: " << (a&b) << endl;
	cout << "a or b: " << (a|b) << endl;
	cout << "a xor b: " << (a^b) << endl;
	
	return 0;
}