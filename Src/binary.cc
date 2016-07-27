#include <iostream>
using namespace std;

int binaSearch(int *data, int num, int goal, int low, int high) {
    int mid = (low + high) / 2; 
    while (low <= high) {
        while (low <= high && data[mid] > goal) {
            high = mid - 1;
            mid = (low + high) / 2;
        }
        while (low <= high && data[mid] < goal) {
            low = mid + 1;
            mid = (low + high) / 2;
        }
        if (data[mid] == goal)
            return mid;
    }
    return -1;
}

int main()
{
    int data[10] = {1,3,5,7,8,23,456,678,3459,13241}, num = 10, i = 0, goal;
    cout << "Please input the data:" << endl;
    cin >> goal;
    cout << "the location is:" << endl;
    int low = 0, high = num - 1;
    cout << binaSearch(data, num, goal, low, high) << endl;
    return 0;
}
