#include <iostream>
#include <vector>
#include <string>
using namespace std;
constexpr int Squared(int number) { return number * number; }

int main()
{
    // basic index/array stuff
    const int ARR_LEN = 5;

    int myNums[ARR_LEN] = {5, 10, 0, -101, 20};

    int moreNums[Squared(ARR_LEN)];

    int idx = 0;
    cout << "element to change : ";
    cin >> idx;

    int newVal = 0;
    cout << "new value: ";
    cin >> newVal;

    if ((idx < ARR_LEN) && (idx >= 0))
    {
        myNums[idx] = newVal;
        moreNums[idx] = newVal;
    }

    cout << "myNums[" << idx << "]: " << myNums[idx] << endl;
    cout << "moreNums[" << idx << "]: " << moreNums[idx] << endl;

    int threeByThree[3][3] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};

    for (int r = 0; r < 3; r++)
    {
        cout << "row " << r << ": {";
        for (int c = 0; c < 3; c++)
        {
            cout << threeByThree[r][c] << ", ";
        }
        cout << "\b\b}" << endl;
    }

    // introducing vectors
    vector<int> dynArr (3);

    dynArr[0] =365;
    dynArr[1] = -421;
    dynArr[2] = 789;

    cout << "num ints: " << dynArr.size() << endl;
    cout << "new value: ";
    cin >> newVal;

    dynArr.push_back(newVal);
    cout << "num ints: " << dynArr.size() << endl;
    cout << "last element: dynArr[" << dynArr.size() - 1 << "]: ";
    cout << dynArr[dynArr.size() - 1] << endl;

    // strings
    cin.ignore();  // flush the last \n from the buffer
    cout << "enter 1st line:" << endl;
    string firstLine;
    getline(cin, firstLine);

    cout << "enter 2nd line:" << endl;
    string secondLine;
    getline(cin, secondLine);

    string catString = firstLine + " " + secondLine;
    cout << "concatenated: " << catString << endl;
    cout << "length: " << catString.length() << endl;

    // multi dimensional arrays
    enum Square
    {
        Empty = 0,
        Pawn,
        Rook,
        Knight,
        Bishop,
        King,
        Queen
    };

    Square chessBoard[8][8];

    chessBoard[0][0] = chessBoard[0][7] = Rook;
    chessBoard[7][0] = chessBoard[7][7] = Rook;

    return 0;
}
