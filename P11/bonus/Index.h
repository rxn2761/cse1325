//
// Created by Rodney Nguyen on 11/15/23.
//

#ifndef _INDEX_H
#define _INDEX_H
#include <map>
#include <set>
using namespace std;
typedef string Word;

class Location;
typedef std::set<Location> Locations;


class Location;

class Index {
private:
    using IndexMap = map<Word, Locations>;
    IndexMap index;

public:
    void add_word(const Word& word, const string& filename, int line);

    friend std::ostream& operator<<(std::ostream& ost, const Index& index);
};


#endif //_INDEX_H
