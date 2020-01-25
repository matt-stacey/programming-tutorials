# include <iostream>
using namespace std;

int main ()
{
	int arg1;
	arg1 = -1;
	int x , y , z;
	char myDouble = '5';
	//char arg1 = 'A'; // can't re-type in scope
	
	cout << arg1 << "\n"; // -1
	
	{
		char arg1 = 'A'; // new scope
		cout << arg1 << "\n"; // A
	}
	
	cout << arg1 << "\n"; // -1, original scope
	return 0;
}