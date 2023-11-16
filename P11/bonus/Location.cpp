//
// Created by Rodney Nguyen on 11/15/23.
//

#include "Location.h"


std::string Location::last_filename = "";

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

void Location::nextWord() {
    Location::last_filename = "";
}

std::ostream& operator<<(std::ostream& ost, const Location& location) {
    if (location._filename == Location::last_filename) {
        ost << ", " << location._line;
    } else {
        ost << "Filename: " << location._filename << ", Line Number(s): " << location._line;
        Location::last_filename = location._filename;
    }
    return ost;
}