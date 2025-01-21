package org.firstinspires.ftc.teamcode.HelperClasses;

import androidx.annotation.NonNull;

public class Position {
    public double x,y,t;
    /**
     * Creates a position object
     * @param x x position
     * @param y y position
     * @param t rotational position
     */
    public Position(double x, double y, double t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
    /**
     * Creates a generic position object with 0 coordinates and rotation
     */
    public Position() {
        x = 0;
        y = 0;
        t = 0;
    }

    /**
     * Adds two positions' values together
     * @param toAdd position to be added to this position
     */
    public void add(@NonNull Position toAdd) {
        x += toAdd.x;
        y += toAdd.y;
        t += toAdd.t;
        while (t < 0) //Normalize theta between [0,2pi)
            t+=2*Math.PI;
        t%=2*Math.PI;
    }

    @NonNull
    public double[] toArray() {
        return new double[]{x,y,t};
    }

    @NonNull
    @Override
    public String toString() {
        return
                "X: " + x +
                "\nY: " + y +
                "\nT: " + t;
    }
}