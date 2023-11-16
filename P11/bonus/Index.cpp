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
    for (const auto& wordEntry : index.index) {
        Location::nextWord();
        ost << "[" << wordEntry.first << "]";

        for (const auto& location : wordEntry.second) {
            ost << " " << location;
        }

        ost << endl;

    }
    return ost;
}