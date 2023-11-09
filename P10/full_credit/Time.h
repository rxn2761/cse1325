//
// Created by Rodney Nguyen on 11/7/23.
//

#ifndef _TIME_H
#define _TIME_H
#include <iostream>


class Time {
public:
    Time(int hour, int minute, int second);

    Time operator+(const Time& time) const;
    Time& operator++();
    Time operator++(int);

    bool operator==(const Time& time) const;
    bool operator!=(const Time& time) const;
    bool operator<(const Time& time) const;
    bool operator>(const Time& time) const;
    bool operator<=(const Time& time) const;
    bool operator>=(const Time& time) const;

private:
    int _hour;
    int _minute;
    int _second;
    void rationalize();
    friend std::ostream& operator<<(std::ostream& ost, Time& time);
    friend std::istream& operator>>(std::istream& ist, Time& time);
};


#endif //_TIME_H
