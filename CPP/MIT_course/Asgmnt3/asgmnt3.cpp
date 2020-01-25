/*
 * asgmnt3.cpp
 *
 *  Created on: Jun 19, 2019
 *      Author: Matt
 */

#include <iostream>
#include "geometry.h"
#include <string>

using namespace std;


void printAttributes(Polygon *p);
string pigLatinify(const string word);

// main
int main() {
	// polygons

	int xRight, xLeft, yUpper, yLower;

	cout << "Rectangle\n" << "enter upper right X coordinate: ";
	cin >> xRight;
	cout << "enter upper right Y coordinate: ";
	cin >> yUpper;
	cout << "enter lower left X coordinate: ";
	cin >> xLeft;
	cout << "enter lower left Y coordinate: ";
	cin >> yLower;

	Rectangle rect = Rectangle(xLeft, yLower, xRight, yUpper);  // 4 ints, not 2 pts
	printAttributes(&rect); // pass by reference

	cout << endl;
	int aX, aY, bX, bY, cX, cY;

	cout << "Triangle\n" << "enter first coordinate pair (separated by spaces): ";
	cin >> aX >> aY;
	cout << "enter first coordinate pair (separated by spaces): ";
	cin >> bX >> bY;
	cout << "enter first coordinate pair (separated by spaces): ";
	cin >> cX >> cY;

	Triangle tri = Triangle(Point(aX, aY), Point(bX, bY), Point(cX, cY));
	printAttributes(&tri);

	// strings
	cout << pigLatinify("beast") << endl;
	cout << pigLatinify("dough") << endl;
	cout << pigLatinify("happy") << endl;
	cout << pigLatinify("question") << endl;
	cout << pigLatinify("") << endl;

	cout << pigLatinify("contains") << endl;
	cout << pigLatinify("an") << endl;
	cout << pigLatinify("error") << endl;
	cout << pigLatinify("because") << endl;
	cout << pigLatinify("the") << endl;
	cout << pigLatinify("function") << endl;
	cout << pigLatinify("is") << endl;
	cout << pigLatinify("declared") << endl;
	cout << pigLatinify("constant") << endl;

	cout << pigLatinify("qis") << endl;

	cout << "\nProgram complete";
	return 0;
}

void printAttributes(Polygon *p) {
	cout << "polygon area: " << p->area() << endl;

	cout << "polygon points: ";
	const PointArray *pp = p->getPoints(); // still learning . vs ->
	for(int i = 0; i < pp->getSize(); i++) {
		cout << "(" << pp->get(i)->getX() << ", " << pp->get(i)->getY() << ")";
		if(i < pp->getSize() - 1)
			cout << "; ";
		else
			cout << std::endl;
	}
}

string pigLatinify(const string word) {
	string newWord;

	int length = word.size();

	if (length == 0)
		return word;

	// this is not as elegant as the solution in the text
	// where: 	const string vowels = "aeiou";
	// and		if(vowels.find(word[0]) != string::npos) // starts with a vowel

	switch (word[0]) {
		case 'q':
			if(word[1] == 'u')
				newWord = word.substr(2,length-2) + "quay";
			else
				newWord = word.substr(1,length-1) + "qay";
			break;
		case 'a':
		case 'e':
		case 'i':
		case 'o':
		case 'u':
			newWord = word + "-way";
			break;
		default:
			newWord = word.substr(1,length-1) + word[0] + "ay";
	}


	return newWord;
}
