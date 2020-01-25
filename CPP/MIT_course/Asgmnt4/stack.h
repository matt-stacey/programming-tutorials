/*
 * stack.h
 *
 *  Created on: Jun 23, 2019
 *      Author: matth
 */

#include <vector>

#ifndef STACK_H_
#define STACK_H_

/* templates and friends
 * 1 - templated forward declaration of class
 * 2 - templated prototype of friend function
 * 3 - templated class definition, including friend function with <>
 * 4 - operator definition
 * */

// 1
template <class T>
class Stack;

// 2
template <class T>
Stack<T> operator+(const Stack<T> &a, const Stack<T> &b);

// 3
template <class T>
class Stack {
	friend Stack<T> operator+<>(const Stack<T> &a, const Stack<T> &b);
	std::vector<T> items; // use std:: to access

public:
	bool empty() const { return items.empty(); } // vector functions
	void push(const T &item) { items.push_back(item); }
	T top() { return items.back(); } // not in solution
	T pop() {
		T last = items.back(); // save last item
		items.pop_back(); // drop it
		return last; // return it
	}
	int size() const { return items.size(); } // not reqd

};

// 4
template <class T>
Stack<T> operator+(const Stack<T> &a, const Stack<T> &b) {
	Stack<T> fullStack = a; // remember to use <T> to continue to pass type info

	for(unsigned i = 0; i < b.items.size(); i++) { // b.size is incorrect
		fullStack.items.push_back(b.items[i]); // can use .items.push_back because items is the vector inside stack a that is tracking these
	}

	return fullStack;
}

#endif /* STACK_H_ */
