#include <iostream>
using namespace std;

double mean(int[], int);
int max(int[], int);
int min(int[], int);
int range(int[], int);

int main()
{
	cout << "Enter 'N' for number of ints (1-10):" << endl;
	int N = 0;
	while (N<1 || N>10)
	{
		cin >> N;
	}
	
	int nums[N];
	
	for(int i=0; i<N; i++)
	{
		cout << "nums[" << i << "]= ";
		cin >> nums[i];
	}
	cout << endl;
	
	cout << "Mean: " << mean(nums, N) << endl;
	cout << "Max: " << max(nums, N) << endl;
	cout << "Min: " << min(nums, N) << endl;
	cout << "Range: " << range(nums, N) << endl;
	
	return 0;
}

double mean(int nums[], int N)
{
	int sum = 0;
	for(int i=0; i<N; i++)
	{
		sum += nums[i];
	}
	
	return sum/(double)N;
}

int max(int nums[], int N)
{
	int max = nums[0];
	for(int i=1; i<N; i++)
	{
		max = nums[i] > max ? nums[i] : max;
	}
	
	return max;
}

int min(int nums[], int N)
{
	int min = nums[0];
	for(int i=1; i<N; i++)
	{
		min = nums[i] < min ? nums[i] : min;
	}
	
	return min;
}

int range(int nums[], int N)
{
	int maxR = max(nums, N);
	int minR = min(nums, N);
	
	return maxR - minR;
}