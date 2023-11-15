//
// Created by Rodney Nguyen on 11/14/23.
//

#ifndef _LOCATION_H
#define _LOCATION_H

#include <iostream>
using namespace std;


class Location {
private:
    string _filename;
    int _line;

public:
    Location(const string& filename, int line);

    bool operator==(const Location& location) const;
    bool operator!=(const Location& location) const;
    bool operator<(const Location& location) const;
    bool operator>(const Location& location) const;
    bool operator<=(const Location& location) const;
    bool operator>=(const Location& location) const;

    friend std::ostream& operator<<(std::ostream& ost, const Location& location);
};


#endif //_LOCATION_H
