#include <iostream>
#include "Shape.h"
#include "Triangle.h"
#include "Circle.h"
#include <vector>

int main() {
    std::vector<Shape*> S;

    Triangle triangle(4.0, 5.0);
    Circle circle(6.0);
    Triangle triangle2(2.56, 4.59);
    Circle circle2(3.9);

    S.push_back(&triangle);
    S.push_back(&circle);
    S.push_back(&triangle2);
    S.push_back(&circle2);

    for (int i = 0; i < S.size(); i++) {
        std::cout << S[i]->toString() << std::endl;
    }
    return 0;
}