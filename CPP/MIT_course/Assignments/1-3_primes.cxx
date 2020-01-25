#include <iostream>
using namespace std;

bool isPrime(int);

int main()
{
	int N = 0;
	
	do
	{
		cout << "Enter number of primes to find: ";
		cin >> N;
	}while(N<1);
	
	int f = 0;
	for(int k = 2; f<N; k++)
	{
		if (isPrime(k))
		{
			cout << k << endl;
			f++;
		}
	}
	
	return 0;
}

bool isPrime(int P)
{
	if (P == 2)
	{
		return true;
	}
	
	double max = P/2.0;
	
	for(int i=2; i<=max; i++)
	{
		if (P % i == 0)
		{
			return false;
		}
	}
	return true;
}