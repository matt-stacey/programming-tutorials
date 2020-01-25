#include <iostream>
#include <string>
using namespace std;

int main()
{
	const int len1 = 4;
	int nums1[len1] = {0, 1, 2, 3};
	
	cout << "array numbers backwards: ";
	for (int i = len1 - 1; i >= 0; i--)
	{
		cout << nums1[i] << ", ";
	}
	cout << "\b\b" << endl;
	
	const int len2 = 3;
	int nums2[len2] = {4, 5, 6};
	
	for (int i = len1 - 1; i >=0; i--)
	{
		for (int j = len2 - 1; j >= 0; j--)
		{
			cout << "nums1[" << i << "] + nums2[" << j << "] = " << nums1[i] + nums2[j] << endl;
		}
	}
	
	int a = 0, b = 1;
	int fib = 0;
	
	cout << "how many fibonacci numbers (>=2): ";
	while (fib < 2)
	{
		cin >> fib;
	}
	
	cout << a << endl;
	cout << b << endl;
	for (int i = 2; i < fib; i++)
	{
		int c = a + b;
		cout << c << endl;
		a = b; b = c;
	}
	
	enum color
	{
	    red = 0,
	    orange,
	    yellow,
	    green,
	    blue,
	    indigo,
	    violet,
	    mauve
	};
	
	const int  colors = 8;
	int pick = 0;
	string color_list[colors] = {"red", "orange", "yellow", "green", "blue", "indigo", "violet", "mauve"};
	
	cout << "available colors: " << endl;
	for (int c = 0; c < colors; c++)
	{
		cout << color_list[c] << ": " << c << endl;
	}
	cout << "enter color: ";
	cin >> pick;
	
	switch (pick)
	{
		case red:
		case orange:
		case yellow:
		case green:
		case blue:
		case indigo:
		case violet:
		    cout << "in rainbow";
		    break;
		default:
		    cout << "not in rainbow";
	}
	
	return 0;
}