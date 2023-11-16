//
// Created by Rodney Nguyen on 11/14/23.
//

#include "Location.h"


Location::Location(const string& filename, int line) : _filename(filename), _line(line) {}

bool Location::operator==(const Location& location) const {
    return _filename == location._filename && _line == location._line;
}

bool Location::operator!=(const Location& location) const {
    return !(*this == location);
}

bool Location::operator<(const Location& location) const {
    return _filename < location._filename || (_filename == location._filename && _line < location._line);
}

bool Location::operator>(const Location& location) const {
    return _filename > location._filename || (_filename == location._filename && _line > location._line);
}

bool Location::operator<=(const Location& location) const {
    return *this < location || *this == location;
}

bool Location::operator>=(const Location& location) const {
    return *this > location || *this == location;
}

std::ostream& operator<<(std::ostream& ost, const Location& location) {
    ost << "Filename: " << location._filename << " Line Number: " << location._line << ";";
    return ost;
}