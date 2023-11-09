#include <iostream>
#include <ostream>
#include "Time.h"


int main() {
    try {
        Time time(10, 30, 21);
        int hour = time[0];
        int minute = time[1];
        int second = time[2];
        std::cout << "Hour: " << hour << ", Minute: " << minute << ", Second: " << second << std::endl;
    }
    catch(std::out_of_range& e)
    {
        std::cout << "Exception: " << e.what() << std::endl;
    }

    return 0;
}