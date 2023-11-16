#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include "Location.h"
#include "Index.h"


int main() {

    vector<string> filenames = { "america.txt", "captain.txt", "glimpse.txt", "spider.txt" , /* add more filenames */ };

    Index index;

    for (const auto& filename : filenames) {
        ifstream file(filename);
        if (!file.is_open()) {
            cerr << "Error opening file: " << filename << endl;
            return 1;
        }

        string line;
        int lineNumber = 1;

        while (getline(file, line)) {
            istringstream iss(line);
            string word;

            while (iss >> word) {
                index.add_word(word, filename, lineNumber);
            }

            lineNumber++;
        }

        file.close();
    }

    cout << index;

    return 0;
}
