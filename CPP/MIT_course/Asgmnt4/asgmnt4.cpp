/*
 * asgmnt4.cpp
 *
 *  Created on: Jun 19, 2019
 *      Author: Matt
 */

#include <iostream>
#include <vector>

#include "stack.h"

#define minPP(X, Y) (X < Y ? X : Y)

using namespace std;

template <typename T>
T minF (const T a, const T b) {
	if (a < b)
		return a;
	else
		return b;
	// could be cleaner with ternary
}

// main
int main() {

	cout << minF(1, 0) << endl; // 0
	cout << minF(2.5, 2.45) << endl; // 2.45

	cout << minPP(20, 100) << endl; // 20
	cout << minPP(3.6, 3.141) << endl; // 3.141

	// 3.1 assumes assignment 3 is correct
	// reinterpret_cast<Triangle *>(rect);

	// 3.2 assumes the same
	// dynamic_cast<Triangle *>(rect);

	// stack: LIFO, push/pop
	Stack<int> a, b;
	a.push(1);
	a.push(2);
	b.push(3);
	b.push(4);

	Stack<int> c = a + b;
	int items = c.size();

	for(int i = 0; i < items; i++) {
		cout << c.pop();
		if(i < items -1)
			cout << ", ";
		else
			cout << endl;
	}

	cout << "Program complete";
	return 0;
}
