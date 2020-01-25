#include <iostream>
using namespace std;

int main()
{
	const char* HW = "Hello, world! c*";
	
	cout << "Hello, world!" << endl;
	cout << HW << endl << endl;
	
	int n;
	do
	{
		cout << "Enter a number between 1 and 10" << endl;
		cin >> n;
		cout << endl;
	} while(n<1 || n>10);
	
	for(int i=0; i<n; i++)
	{
		cout << HW << i+1 << "f" << endl;
	}
	
	cout << endl;
	int i = 0;
	while(i<n)
	{
		cout << HW << i+1 << "w" << endl;
		i++;
	}
	
	cout << endl;
	i = 0;
	do
	{
		cout << HW << i+1 << "d" << endl;
		i++;
	} while(i<n);
	
	return 0;
}