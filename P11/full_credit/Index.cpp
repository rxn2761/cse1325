//
// Created by Rodney Nguyen on 11/15/23.
//

#include "Index.h"
#include "Location.h"


void Index::add_word(const Word &word, const std::string &filename, int line) {
    Word lowercaseWord = word;
    transform(lowercaseWord.begin(), lowercaseWord.end(), lowercaseWord.begin(), ::tolower);

    while (!lowercaseWord.empty() && !isalnum(lowercaseWord.front())) {
        lowercaseWord.erase(lowercaseWord.begin());
    }

    while (!lowercaseWord.empty() && !isalnum(lowercaseWord.back())) {
        lowercaseWord.pop_back();
    }

    if (lowercaseWord.empty()) {
        cerr << "Invalid word: " << word << endl;
        return;
    }
    index[lowercaseWord].insert(Location(filename, line));
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
    for (auto it = index.index.begin(); it != index.index.end(); ++it) {

        //below codes should take care of the issue with displaying character ' correctly in Windows 10. Example of a word with that issue: [you've]
        /*string word = it->first;
        size_t position = word.find("’");
        if (position != string::npos)
            word = word.replace(position, 3, "'");*/

        ost << "[" << it->first << "]";

        for (auto locIt = it->second.begin(); locIt != it->second.end(); ++locIt) {
            ost << " " << *locIt;
        }
        ost << endl;
    }
    return ost;
}