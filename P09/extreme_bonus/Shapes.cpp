#include <iostream>
#include "abstractShape.h"
#include "Rectangle.h"
#include "Circle.h"
#include <vector>

int main() {
    std::vector<abstractShape*> S;

    Rectangle rectangle(4.0, 6.0);
    Circle circle(6.0);
    Rectangle rectangle2(10.0, 19.0);
    Circle circle2(15.0);

    S.push_back(&rectangle);
    S.push_back(&circle);
    S.push_back(&rectangle2);
    S.push_back(&circle2);

    for (int i = 0; i < S.size(); i++) {
        std::cout << S[i]->toString() << std::endl;
        S[i]->draw();
    }
    return 0;
}
