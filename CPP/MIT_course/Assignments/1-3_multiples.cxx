#include <iostream>
using namespace std;

int main()
{
	while(1)
	{
		int N;
		cout << "Input integer: ";
		cin >> N;
		
		if (N<0)
			break;
		
		cout << "Ternary: " << (N % 5 == 0 ? N/5 : -1) << endl;
		
		cout << "Continue: ";
		if (N % 5 == 0)
			cout << N/5 << endl;
		else {
			cout << endl;
			continue;
		}
		
	}
	
	cout << "Goodbye!";
	return 0;
}