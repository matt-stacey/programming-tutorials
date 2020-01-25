/*
 * asgmt2.cpp
 *
 *  Created on: Jun 13, 2019
 *      Author: matth
 */

#include <iostream>
#include <cstdlib> // standard library including rand(), srand(), RAND_MAX
#include <ctime> // time functions, including time()
#include <cmath> // sqrt function

using namespace std;

const int WIDTH = 4; // rows
const int LENGTH = 3; // cols

// problem 1 functions
void printNTimes(const char*, int);
void print1(const long&);
void setString(char**);
//problem 2 functions
void f(const int);
//problem 3 functions
//problem 4 functions
int sum(const int, const int);
double sum(const double, const double);
int sum(const int, const int, const int);
int sum(const int, const int, const int, const int);
int sum(const int[], const int);
//problem 5 functions
double piCalc();
//problem 6 functions
void printArray(const int[], const int);
void reverseArray(const int[], const int);
void transpose(const int input[][LENGTH], int output[][WIDTH]);
void reverseArray2(const int[], const int);
//problem 7 functions
int stringLength(const char*);
void swapInts(int&, int&);
void swapInts2(int*, int*);
void swapPtr(int**, int**);
// problem overfunctions
int prob1(const char*, int);
int prob2();
int prob3();
int prob4();
int prob5();
int prob6();
int prob7();

// main
int main() {
	const char* param = "\n";
	int p1 = prob1(param, 1);
	cout << endl;

	int p2 = prob2();
	cout << endl;

	int p3 = prob3();
	cout << endl;

	int p4 = prob4();
	cout << endl;

	int p5 = prob5();
	cout << endl;

	int p6 = prob6();
	cout << endl;

	int p7 = prob7();
	cout << endl;

	cout << "Program complete";
	return p1 + p2 + p3 + p4 + p5 + p6 + p7;
}

// problem 1 functions
void printNTimes(const char* msg = "\n", int n = 1)
{
	for(int i = 0; i < n; i++)
	{
		cout << msg;
	}
}
void print1(const long &x)
{
	cout << x;
}
void setString(char **strPtr)
{
	int x;
	cout << "Enter number: ";
	cin >> x;
	if(x < 0)
		*strPtr = "Negative!"; // dereference: set value at location to which 'strPtr' points, aka str
	else
		*strPtr = "Nonnegative!";
}

//problem 2 functions
void f(const int a = 5)
{
	cout << a*2 << endl;
}

//problem 3 functions

//problem 4 functions
int sum(const int a, const int b) // use const!
{
	return a + b;
}
double sum(const double a, const double b) // use const!
{
	return a + b;
}
int sum(const int a, const int b, const int c) // use const!
{
	return a + b + c;
}
int sum(const int a, const int b, const int c, const int d) // can set defaults to 0, but then cannot use with 3-part (ambiguity)
{
	return a + b + c + d;
}
int sum(const int array[], const int length)
{
	int acc = 0;
	for(int i = 0; i < length; i++)
	{
		acc += array[i];
	}
	return acc;
}
int sum(const int array[], const int length, char ver)
{
	if (length == 1) // base case
		return array[0];
	return array[0] + sum(array + 1, length - 1);
}

//problem 5 functions
double piCalc(int n)
{
	srand(time(0));

	int hits = 0;

	for(int i = 0; i <= n; i++)
	{
		double x = rand()/(double)RAND_MAX, y = rand()/(double)RAND_MAX;
		if (sqrt(x*x + y*y) <= 1)
			hits++;
	}

	return 4.0*hits/(double)n;
}

//problem 6 functions
void printArray(const int array[], const int length)
{
	for(int i = 0; i < length; i++)
	{
		cout << array[i];
		if (i < length - 1)
			cout << ", ";
	}
	cout << endl;

	return;
}
void reverseArray(int array[], const int length)
{
	int array2[length];
	for(int i = 0; i < length; i++)
	{
		array2[i] = array[length-(i+1)];
	}
	for(int i = 0; i < length; i++)
	{
		array[i] = array2[i];
	}

	return;
}
void transpose(const int input[][LENGTH], int output[][WIDTH])
{
	// int WIDTH = 4; // rows
	// int LENGTH = 3; // cols

	for(int r = 0; r < WIDTH; r++)
	{
		for(int c = 0; c < LENGTH; c++)
		{
			output[c][r] = input[r][c];
		}
	}
}
void reverseArray2(int array[], const int length)
{
	int array2[length];
	for(int i = 0; i < length; i++)
	{
		*(array2 + i) = *(array + (length-(i+1)));
	}
	for(int i = 0; i < length; i++)
	{
		*(array + i) = *(array2 + i);
	}

	return;
}

//problem 7 functions
int stringLength(const char *string)
{
	int length = 0;

	while (*(string + length) != '\0')
	{
		length++;
	}

	return length;
}
void swapInts(int &a, int &b)
{
	int temp = a;
	a = b;
	b = temp;

	return;
}
void swapInts2(int *a, int *b)
{
	int temp = *a;
	*a = *b;
	*b = temp;

	return;
}
void swapPtr(int **a, int **b)
{
	int *temp = *a;

	*a = *b;
	*b = temp;

	return;
}

// problem overfunctions
int prob1(const char* msg = "\n", int n = 1)
{
	// showcases:
	// default and constant arguments
	// random numbers
	// pointers/arrays

	long x = 234923592;
	print1(x); // x will not be changed when passed by reference to the function
	printNTimes(); // uses default values of 1 new line

	srand(time(0)); //seeds rand with time(0) == current time as #

	int randNum = rand();
	cout << "A random number: " << randNum << endl;

	char *str; // pointer to char (address of the string); named 'str'
	setString(&str); //pass by reference the address of a pointer, to the string
	cout << str << endl;

	return 0;
}
int prob2()
{
	int a = 123;
	f(1);
	f(a);
	int b = 3;
	f(b);
	a = 4; // originally changed scope
	f(a);
	f();

	return 0;
}
int prob3(){ return 0; }
int prob4()
{
	int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int length = 10;

	cout << sum(array, length) << endl; // 55
	cout << sum(array, length, '2') << endl; // 55

	return 0;
}
int prob5()
{
	int n = 0;
	do
	{
		cout << "Darts (>0): ";
		cin >> n;
	} while (n<=0);

	double pie = piCalc(n);

	cout << "Pi is approx " << pie << endl;

	return 0;
}
int prob6()
{
	int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
	int length = sizeof(array)/sizeof(*array);

	printArray(array, length); // 1 to 10

	reverseArray(array, length);
	printArray(array, length); // 10 to 1

	reverseArray2(array, length);
	printArray(array, length); // 1 to 10

	return 0;
}
int prob7()
{
	char *string = "1234567890";
	int length = stringLength(string);
	cout << length << endl; // 10

	int a = 1, b = 2;
	cout << a << ", " << b << endl; // 1, 2
	swapInts(a, b);
	cout << a << ", " << b << endl; // 2, 1
	swapInts2(&a, &b);
	cout << a << ", " << b << endl; // 1, 2

	int x = 5, y = 6;
	int *ptr1 = &x, *ptr2 = &y;
	swapPtr(&ptr1, &ptr2);
	cout << *ptr1 << ", " << *ptr2 << endl; // 6, 5

	char *oddOrEven = "Never odd or even";

	char *nthCharPtr = &oddOrEven[5]; // point to address of 6th char
	nthCharPtr -= 2; // update with pointer arithmetic to 4th char
	cout << *nthCharPtr << endl; // output value at pointer address: 'e'

	char **pointerPtr = &nthCharPtr; // pointer to pointer
	cout << pointerPtr << endl; // output value stored
	cout << **pointerPtr << endl; // output value at address pointed to by pointer: 'e'

	nthCharPtr += 1; // update with pointer +1 position
	cout << nthCharPtr - oddOrEven << endl; // print how far pointer is from front of string

	return 0;
}
