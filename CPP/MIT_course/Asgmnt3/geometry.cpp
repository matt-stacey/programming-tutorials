/*
 * geometry.cpp
 *
 *  Created on: Jun 20, 2019
 *      Author: matth
 */


#include "geometry.h"
#include <cmath>

#include <iostream>

PointArray::PointArray() {
	size = 0;
	points = new Point[0]; // to delete later
}

PointArray::PointArray(const Point initPoints[], const int initSize) {
	size = initSize;
	points = new Point[initSize]; // not size, as it may change??

	for(int i = 0; i < initSize; i++) {
		points[i] = initPoints[i];
	}
}

PointArray::PointArray(const PointArray& pv) {
	size = pv.size;
	points = new Point[pv.size];
	for(int i = 0; i < pv.size; i++) {
		points[i] = pv.points[i];
	}
}

void PointArray::resize(const int n) {
	Point* newPoints = new Point[n];
	int copyVals = (size <= n ? size : n);

	for(int i = 0; i < copyVals; i++) {
		newPoints[i] = points[i];
	}

	delete[] points;

	size = n;
	points = newPoints;
}

void PointArray::push_back(const Point &p) {
	resize(size + 1);
	points[size - 1] = p; // not "size" because size is increased during resize
	// insert(size, p) works also
}

void PointArray::insert(const int position, const Point &p) {
	resize(size + 1);
	for(int i = size - 1; i > position; i--) {
		points[i] = points[i-1];
	}
	points[position] = p;
}

void PointArray::remove(const int pos) {
	// did not check to ensure pos is valid
	if(pos<0 || pos >= size) { return; } // now it does

	for(int i = pos; i < size - 2; i++) { // see below re: why "size - 2"
		points[i] = points[i+1];
	}

	// size = 7, pos = 2
	// 0 1 2 3 4 5 6
	// becomes
	// 0 1 3 4 5 6
	// points[2] = points[3]
	// points[3] = points[4]
	// points[4] = points[5]
	// points[5] = points[6]
	// points[6] = points[7] <-- index error

	resize(size - 1);
}

int Polygon::polygons = 0; // initialize number of polygons at 0

Polygon::Polygon(const Point pointList[], const int pointNum) : points(pointList, pointNum) { // more elegant to use points(pointList, pointNum)
	// cannot use points = PointArray(pointList, pointNum);
	// why not?

	polygons++;
}

Polygon::Polygon(const PointArray &pa) { // more elegant to use : points(pa)
	points = PointArray(pa);
	polygons++;
}

Point constructorPoints[4];

Point *updateConstructorPoints(const Point &p1, const Point &p2, const Point &p3, const Point &p4 = Point(0,0)) {
	constructorPoints[0] = p1;
	constructorPoints[1] = p2;
	constructorPoints[2] = p3;
	constructorPoints[3] = p4;

	return constructorPoints;
}

// this is how to do a constructor for an inherited class (member initializer syntax)
// be sure they reflect properly in geometry.h, without the :Polygon portion
// polygon define clockwise from the upper right
Rectangle::Rectangle(const Point &lowerLeft, const Point &upperRight) : Polygon(updateConstructorPoints(upperRight, Point(upperRight.getX(), lowerLeft.getY()), lowerLeft, Point(lowerLeft.getX(), upperRight.getY())), 4) {}
Rectangle::Rectangle(const int xLeft, const int yLower, const int xRight, const int yUpper) : Polygon(updateConstructorPoints(Point(xRight, yUpper), Point(xRight, yLower), Point(xLeft, yLower), Point(xLeft, yUpper)), 4) {}

double Rectangle::area() const {
	int width = points.get(0)->getX() - points.get(3)->getX(); // upper corners
	int height = points.get(0)->getY() - points.get(1)->getY(); // right corners

	return std::abs((double)width * height);
}

Triangle::Triangle(const Point &a, const Point &b, const Point &c) : Polygon(updateConstructorPoints(a, b, c), 3) {}

double Triangle::area() const {
	double A = sqrt(
			pow(points.get(2)->getX() - points.get(1)->getX(), 2) +
			pow(points.get(2)->getY() - points.get(1)->getY(), 2));
	double B = sqrt(
			pow(points.get(0)->getX() - points.get(2)->getX(), 2) +
			pow(points.get(0)->getY() - points.get(2)->getY(), 2));
	double C = sqrt(
			pow(points.get(0)->getX() - points.get(1)->getX(), 2) +
			pow(points.get(0)->getY() - points.get(1)->getY(), 2));

	double s = (A + B + C)/2.0;

	return std::sqrt(s * (s-A) * (s-B) * (s-C));
}
