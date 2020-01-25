#include <iostream>

// #define pi 3.14159  // don't do this anymore!

// constexpr's allow the compiler to replace these calls with the value here
constexpr double GetPi() { return 22.0 / 7.0; }
constexpr double TwicePi() { return 2 * GetPi(); }

enum YourCards
{
    y_Ace,
    y_Jack,
    y_Queen,
    y_King
};

enum MyCards
{
    Ace = 43,
    Jack,
    Queen,
    King
};

enum RainbowColors
{
    red = 0,
    orange,
    yellow,
    green,
    blue,
    indigo,
    violet
};

int main()
{
    using namespace std;

    const double PI = 22.0 / 7.0;

    double pi_estimate{ PI };  // 'list initialization' to ensure data isn't lost
    pi_estimate = PI;

    cout << "const PI = " << PI << endl;
    cout << "var pi_estimate = " << pi_estimate << endl;
    cout << "GetPi() = " << GetPi() << endl;
    cout << "TwicePi() = " << TwicePi() << endl;

    RainbowColors favorite = green;
    cout << "My favorite color is green: " << favorite << endl;

    // exercise 1
    MyCards exercise_1 = Queen;
    cout << "I have a Queen: " << exercise_1 << endl;

    // exercise 2
    cout << "sizeof(unsigned int) = " << sizeof(unsigned int) << endl;
    cout << "sizeof(int) = " << sizeof(int) << endl;
    cout << "sizeof(long) = " << sizeof(long) << endl;

    // exercise 3
    float radius = 0, area = 0;
    cout << "Enter radius: ";
    cin >> radius;
    area = PI * radius*radius;
    cout << "Area of circle: " << area << endl;

    return 0;
}
