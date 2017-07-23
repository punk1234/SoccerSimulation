package com.company.simulation.model;

/**
 * Created by AKEJU  FATAI on 2017-06-24.
 */
public class Player {

    private int id;
    private double speed;
    private double passAccuracy;
    private double shotAccuracy;
    private double dribbleAccuracy;

    public Player(){

    }

    public Player(int id, double speed, double passAccuracy, double shotAccuracy, double dribbleAccuracy){

        this.id = id;
        this.speed = speed;
        this.passAccuracy = passAccuracy;
        this.shotAccuracy = shotAccuracy;
        this.dribbleAccuracy = dribbleAccuracy;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPassAccuracy() {
        return passAccuracy;
    }

    public void setPassAccuracy(double passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    public double getShotAccuracy() {
        return shotAccuracy;
    }

    public void setShotAccuracy(double shotAccuracy) {
        this.shotAccuracy = shotAccuracy;
    }

    public double getDribbleAccuracy() {
        return dribbleAccuracy;
    }

    public void setDribbleAccuracy(double dribbleAccuracy) {
        this.dribbleAccuracy = dribbleAccuracy;
    }
}
