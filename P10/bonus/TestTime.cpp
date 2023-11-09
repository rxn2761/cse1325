#include <iostream>
#include <ostream>
#include "Time.h"
#include <iomanip>


std::ostream& operator<<(std::ostream& ost, const Time& time) {
    ost << std::setfill('0') << std::setw(2) << time._hour << ":"
        << std::setfill('0') << std::setw(2) << time._minute << ":"
        << std::setfill('0') << std::setw(2) << time._second;
    return ost;
}

Time operator+(int seconds, const Time& time){
    return time + seconds;
}


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