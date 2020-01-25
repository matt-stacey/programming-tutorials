/*
 * geometry.h
 *
 *  Created on: Jun 20, 2019
 *      Author: matth
 */

#ifndef NULL
#define NULL   ((Point *) 0)
#endif

#ifndef GEOMETRY_H_
#define GEOMETRY_H_

class Point {
private:
	int x, y;

public:
	Point (int xx = 0, int yy = 0) {
		x = xx;
		y = yy;
	}

	int getX() const { return x; } // can be called on const Point
	int getY() const { return y; }

	void setX(const int new_x) { x = new_x; } // brackets to implement in header
	void setY(const int new_y) { y = new_y; }
};

class PointArray {
private:
	Point *points; // pointer to start of array (why there is no & in the get accessor), data created/stored dynamically
	int size;

	void resize(int n); // semi-colon to declare/implement external to header

public:
	PointArray();
	PointArray(const Point initPoints[], const int initSize);
	PointArray(const PointArray& pv);
	~PointArray() { delete[] points; }

	void push_back(const Point &p);
	void insert(const int position, const Point &p);
	void remove(const int pos);
	const int getSize() const { return size; }
	void clear() { resize(0); }
	Point *get(const int position) { return ((position >=0 && position < size) ? points + position : NULL); } // why is the * here? returning a pointer
	const Point *get(const int position) const { return ((position >=0 && position < size) ? points + position : NULL); } // so we just add position to points (which is a pointer to array start)
};

class Polygon {
protected:
	PointArray points; // not a pointer: this is the actual data
	static int polygons;

public:
	Polygon(const Point pointList[], const int pointNum);
	Polygon(const PointArray &pa);
	~Polygon() { polygons--; }
	virtual double area() const = 0; // class is now abstract: must be instantiated to call this function
	static int getNumPolygons() { return polygons; }
	int getNumSides() const { return points.getSize(); }
	const PointArray *getPoints() const { return &points; } // const: unmod; pointer: *getPoints, &points (address of...); PointArray datatype!
};

class Rectangle : public Polygon {
public:
	Rectangle(const Point &lowerLeft, const Point &upperRight);
	Rectangle(const int xLeft, const int yLower, const int xRight, const int yUpper);
	virtual double area() const;
	~Rectangle() {} // default destructor: polygon handles count already
};

class Triangle : public Polygon {
public:
	Triangle(const Point &a, const Point &b, const Point &c);
	virtual double area() const;
	~Triangle() {} // default destructor: polygon handles count already
};

#endif /* GEOMETRY_H_ */

