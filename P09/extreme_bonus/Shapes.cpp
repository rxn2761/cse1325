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
    virtual void draw() const = 0;
};

class Rectangle: public abstractShape {
private:
    double _height;
    double _width;

public:
    Rectangle(double height, double width) {
        _height = height; _width = width;
    }
    string name() const override {
        string returnString;
        returnString = "(" + std::to_string(_height) + " X " +  std::to_string(_width) + ") / 2";
        return returnString + " Rectangle";
    }
    double area() const override {
        double Area = (_height * _width) / 2;
        return Area;
    }
    void draw() const override {
        for (int i = 0; i < _height; ++i) {
            for (int j = 0; j < _width; ++j) {
                std::cout << "* ";
            }
            std::cout << std::endl;
        }
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
    void draw() const override {
        int diameter = static_cast<int>(2 * _radius);
        int centerX = diameter / 2;
        int centerY = diameter / 2;

        for (int y = 0; y <= diameter; ++y) {
            for (int x = 0; x <= diameter; ++x) {
                int dx = x - centerX;
                int dy = y - centerY;
                if (dx * dx + dy * dy <= _radius * _radius) {
                    std::cout << "* ";
                }
                else {
                    std::cout << "  ";
                }
            }
            std::cout << std::endl;
        }
    }
};

int main() {
    std::vector<abstractShape*> S;

    Rectangle rectangle(4.0, 6.0);
    Circle circle(6.0);
    Rectangle rectangle2(2.0, 9.0);
    Circle circle2(3.0);

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