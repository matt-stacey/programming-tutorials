#include <iostream>
using namespace std;

int main()
{
	/* problem 3.5.1
	guess: multiplies bob and dole
	does: got it right
	*/
	for(int b = 1; b<10; b++)
	{
		for(int d = 1; d<10; d++)
		{
			int bob = b;
			int dole = d;
			
			// bob and dole are integers
			int accumulator = 0;

			while ( true )
			{
				if (dole == 0)
					break;
				accumulator += ((dole % 2 == 1) ? bob : 0);

				dole /= 2;

				bob *= 2;
			}


			cout << b << " * " << d << " =" << accumulator << "\n" ;
		}
	}
	
	cout << endl;
	
	/* problem 3.5.3
	guess: Taylor series
	does: not a Taylor series
	*/
	for(int N=1; N<=50; N++)
	{
	
		// N is a nonnegative integer
		double acc = 0;

		for ( int i = 1; i <= N ; ++i )
		{
			double term = (1.0/ i );

			acc += term * term ;

			for ( int j = 1; j < i ; ++j )
			{
				acc *= -1;
			}
		}
		cout << N << "  : " << acc << "\n" ;
	}
}