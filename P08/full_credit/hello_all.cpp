#include <iostream>

int main()
{
    std::string input;
    std::cout << "What is your name? ";
    std::getline(std::cin, input);
    std::cout << "Hello, " << input << "!" << std::endl;
    return 0;
}