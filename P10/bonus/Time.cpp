//
// Created by Rodney Nguyen on 11/8/23.
//

#include "Time.h"
#include <iomanip>


Time::Time(int hour, int minute, int second) : _hour(hour), _minute(minute), _second(second) {
    rationalize();
}

void Time::rationalize() {
    int totalSecond = (_hour * 3600) + (_minute * 60) + _second;
    int newHour = totalSecond / 3600;
    int newMinute = (totalSecond % 3600) / 60;
    int newSecond = (totalSecond % 60);
    if (newHour > 23)
        throw std::out_of_range("Hours shouldn't exceed 23 hours");
    _second = newSecond;
    _minute = newMinute;
    _hour = newHour;
}

Time Time::operator+(int seconds) const {
    int totalSecond = seconds + (_hour * 3600) + (_minute * 60) + _second;
    int totalHour = totalSecond / 3600;
    int totalMinute = (totalSecond % 3600) / 60;
    totalSecond = (totalMinute % 60);
    return Time(totalHour, totalMinute, totalSecond);
}

std::ostream& operator<<(std::ostream& ost, const Time& time) {
    ost << std::setfill('0') << std::setw(2) << time._hour << ":"
        << std::setfill('0') << std::setw(2) << time._minute << ":"
        << std::setfill('0') << std::setw(2) << time._second;
    return ost;
}

Time operator+(int seconds, const Time& time){
    return time + seconds;
}
