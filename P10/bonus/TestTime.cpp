#include <iostream>
#include <ostream>
#include "Time.h"


int main() {
    try {
        int seconds = 900;
        Time time1{9, 30, 0};
        std::cout << time1 + seconds << " is also " << seconds + time1 << std::endl;
    }
    catch(std::out_of_range& e)
    {
        std::cout << "Exception: " << e.what() << std::endl;
    }

    return 0;
}