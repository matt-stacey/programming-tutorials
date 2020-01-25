#include <iostream>

using namespace std;

int main()
{
    // exercise 1
    int number = 3;
    int* pNum1 = &number;  // const int* would make line 11 invalid without a const
    *pNum1 = 20;
    int* pNum2 = pNum1;
    number *= 2;
    cout << *pNum2 << endl;  // should display 40

    // exercise 4
    int* pointToAnInt = new int;
    *pointToAnInt = 9;  // needed the deref operator
    cout << "val @ pointToAnInt: " << *pointToAnInt << endl;
    delete pointToAnInt;

    // exercise 5/6
    int* pointToAnInt2 = new int;  // needed *
    int* pNumberCopy = pointToAnInt2;
    *pNumberCopy = 30;
    cout << *pointToAnInt2 << endl;
    delete pNumberCopy;
    // delete pointToAnInt;  did not need 2nd delete

    return 0;
}
