#include <iostream>
#include <string>
#include <cmath>
#include <vector>
using std::string;


class abstractShape {
public:
    virtual string name() const {
        return "";
    }
    virtual double area() const = 0;
    string toString() {
        return name() + " with area " + std::to_string(area());
    }
};

class Triangle: public abstractShape {
private:
    double _height;
    double _base;

public:
    Triangle(double height, double base) {
        _height = height; _base = base;
    }
    string name() const override {
        string returnString;
        returnString = "(" + std::to_string(_height) + " X " +  std::to_string(_base) + ") / 2";
        return returnString + " Triangle";
    }
    double area() const override {
        double Area = (_height * _base) / 2;
        return Area;
    }
};

class Circle: public abstractShape {
private:
    double _radius;

public:
    Circle(double radius) {
        _radius = radius;
    }
    string name() const override {
        return "Circle of radius " + std::to_string(_radius);
    }
    double area() const override {
        return M_PI * pow(_radius, 2);
    }
};

int main() {
    std::vector<abstractShape*> S;

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