//
// Created by Rodney Nguyen on 11/8/23.
//

#ifndef _TIME_H
#define _TIME_H
#include <ostream>


class Time {
public:
    Time(int hour, int minute, int second);

    Time operator+(int seconds) const;

private:
    int _hour;
    int _minute;
    int _second;
    void rationalize();
    friend std::ostream& operator<<(std::ostream& ost, const Time& time);
    friend Time operator+(int seconds, const Time& time);
};


#endif //_TIME_H
