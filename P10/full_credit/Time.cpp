//
// Created by Rodney Nguyen on 11/7/23.
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

Time Time::operator+(const Time& time) const {
    int totalSecond = _second + time._second;
    int carryMinute = totalSecond / 60;
    totalSecond %= 60;

    int totalMinute = _minute + time._minute + carryMinute;
    int carryHour = totalMinute / 60;
    totalMinute %= 60;

    int totalHour = _hour + time._hour + carryHour;

    return Time(totalHour, totalMinute, totalSecond);
}

Time& Time::operator++() {
    _second++;
    if (_second >= 60) {
        _second = 0;
        _minute++;
        if (_minute >= 60) {
            _minute = 0;
            _hour++;
            if (_hour >= 24) {
                _hour = 0;
            }
        }
    }
    return *this;
}

Time Time::operator++(int) {
    Time originalTime(_hour, _minute, _second);
    _second++;
    if (_second >= 60) {
        _second = 0;
        _minute++;
        if (_minute >= 60) {
            _minute = 0;
            _hour++;
            if (_hour >= 24) {
                _hour = 0;
            }
        }
    }
    return originalTime;
}

bool Time::operator==(const Time& time) const{
    if ((_hour == time._hour) && (_minute == time._minute) && (_second == time._second))
        return true;
    return false;
}

bool Time::operator!=(const Time& time) const{
    return !(*this == time);

}

bool Time::operator<(const Time& time) const{
    if (_hour < time._hour)
        return true;
    else if (_hour == time._hour && _minute < time._minute)
        return true;
    else if (_hour == time._hour && _minute == time._minute && _second < time._second)
        return true;
    return false;
}

bool Time::operator>(const Time& time) const{
    if (_hour > time._hour)
        return true;
    else if (_hour == time._hour && _minute > time._minute)
        return true;
    else if (_hour == time._hour && _minute == time._minute && _second > time._second)
        return true;
    return false;
}

bool Time::operator<=(const Time& time) const {
    return (*this < time) || (*this == time);
}

bool Time::operator>=(const Time& time) const {
return (*this > time) || (*this == time);
}