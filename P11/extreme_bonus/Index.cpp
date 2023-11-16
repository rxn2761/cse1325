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
    auto range = index.equal_range(lowercaseWord);
    for (auto it = range.first; it != range.second; ++it) {
        if (it->second == Location(filename, line)) {
            return;
        }
    }

    index.emplace(lowercaseWord, Location(filename, line));
}

std::ostream& operator<<(std::ostream& ost, const Index& index) {
    string lastWord = "";
    for (const auto& entry : index.index) {
        Location::nextWord();
        if (lastWord == entry.first)
            continue;
        auto range = index.index.equal_range(entry.first);
        int count = 0;
        for (auto it = range.first; it != range.second; ++it)
        {
            if (count == 0)
                cout << "[" << it->first << "]" << it->second;
            else
                cout << it->second ;
            count++;
            lastWord = it->first;
        }
        cout << endl;
    }
    return ost;
}