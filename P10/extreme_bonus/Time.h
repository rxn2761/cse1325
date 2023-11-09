//
// Created by Rodney Nguyen on 11/8/23.
//

#ifndef _TIME_H
#define _TIME_H
#include <ostream>


class Time {
public:
    Time(int hour, int minute, int second);

    int operator[](int index) const;

private:
    int _hour;
    int _minute;
    int _second;
    void rationalize();
};


#endif //_TIME_H
