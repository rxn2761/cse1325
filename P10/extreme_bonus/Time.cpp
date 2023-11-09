//
// Created by Rodney Nguyen on 11/8/23.
//

#include "Time.h"


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

int Time::operator[](int index) const {
    if (index == 0) {
        return _hour;
    }
    else if (index == 1) {
        return _minute;
    }
    else if (index == 2) {
        return _second;
    }
    else {
        // Handle out-of-range index here
        throw std::out_of_range("Index is out of range. Index should be 0, 1 or 2");
    }
}