package com.company.simulation.model;

import com.company.simulation.Location;

/**
 * Created by AKEJU  FATAI on 2017-06-24.
 */
public class Stadium {

    private double width;
    private double height;
    private Ball ball;

    public Stadium(){

        width = 1000;
        height = 500;
        ball = new Ball(1,new Location(0,0,0));

    }

    public Stadium(double width, double height, Ball ball){

        this.width = width;
        this.height = height;
        this.ball = ball;

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }
}
