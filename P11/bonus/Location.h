//
// Created by Rodney Nguyen on 11/15/23.
//

#ifndef _LOCATION_H
#define _LOCATION_H

#include <iostream>
using namespace std;


class Location {
private:
    string _filename;
    int _line;
    static string last_filename;

public:
    Location(const string& filename, int line);

    bool operator==(const Location& location) const;
    bool operator!=(const Location& location) const;
    bool operator<(const Location& location) const;
    bool operator>(const Location& location) const;
    bool operator<=(const Location& location) const;
    bool operator>=(const Location& location) const;

    static void nextWord();

    friend std::ostream& operator<<(std::ostream& ost, const Location& location);
};


#endif //_LOCATION_H
