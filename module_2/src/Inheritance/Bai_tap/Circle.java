package Inheritance.Bai_tap;

public class Circle {
    private double radius;
    private String color;
    private boolean filled;
    public Circle() {
        this.radius=1.0;
        this.color ="red";
    }
    public Circle(double radius){
        this.radius=radius;
    }
    public Circle(double rad, String color){
        this.radius=rad;
        this.color=color;
    }

    public Circle(double radius, String color, boolean filled) {
        this.radius=radius;
        this.color=color;
        this.filled=filled;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
    public String toString(){
        return "Circle: radius: "+this.radius+" color: "+this.color+" filled: "+this.filled;
    }
    public double getArea(){
        return this.radius*this.radius*3.14;
    }
//    public String toString
}
