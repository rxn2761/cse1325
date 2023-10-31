#include <iostream>
#include <vector>
#include <string>

int main(int argc, char* argv[]) {
    std::vector<std::string> caps;     
    std::vector<std::string>* no_caps = new std::vector<std::string>; 

    for (int i = 1; i < argc; ++i) {
        std::string arg = argv[i];
        if (!arg.empty()) {
            if (std::isupper(arg[0])) {
                caps.push_back(arg);
            }
            else {
                no_caps->push_back(arg);
            }
        }
    }

    std::cout << "Capitalized:" << std::endl;

    for (int i = 0; i < caps.size(); i++) {
        std::cout << caps[i] << std::endl;
    }

    std::cout << "Lower Case:" << std::endl;

    for (int i = 0; i < no_caps->size(); i++) {
        std::cout << (*no_caps)[i] << std::endl;
    }

    //free the memory allocated on the heap
    delete no_caps;

    return 0;
}
