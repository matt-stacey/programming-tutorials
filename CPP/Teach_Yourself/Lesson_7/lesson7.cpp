#include <iostream>

using namespace std;

const double Pi = 3.14159265;

double Volume(double radius);
double Volume(double radius, double height);
void AreaCirc(double radius, double& area, double& circumference);
void Array(int array[], const int length);

int main()
{
    // exercise 1
    double radius = 0, height = 0;

    cout << "Enter radius: ";
    cin >> radius;
    cout << "Enter height: ";
    cin >> height;

    cout << "Sphere volume: " << Volume(radius) << endl;
    cout << "Cylinder volume: " << Volume(radius, height) << endl;

    // exercise 5
    double area = 0, circumference = 0;
    AreaCirc(radius, area, circumference);
    cout << "Circle area: " << area << endl;
    cout << "       circumference: " << circumference << endl;

    // exercise 2
    const int length = 4;
    int array[length] = {0, 1, 2, 3};
    Array(array, length);

    return 0;
}

double Volume(double radius)
{
    // volume of a sphere
    return 4 * Pi * radius * radius * radius / 3;
}

double Volume(double radius, double height)
{
    // volume of a cylinder
    return Pi * radius * radius * height;
}

void AreaCirc(double radius, double& area, double& circumference)
{

    area = Pi * radius * radius;
    circumference = 2 * Pi * radius;

    return;
}

void Array(int array[], const int length)
{
    cout << "Array received: ";

    for (int idx = 0; idx < length; idx++)
    {
        cout << array[idx] << " ";
    }

    cout << endl;
    return;
}
