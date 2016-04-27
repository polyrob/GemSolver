package com.robbomb.gemsolver;

import java.awt.*;

/**
 * Created by NewRob on 4/26/2016.
 */
public class Move {

    Point from;
    Point to;
    int runs;

    public Move(Point from, Point to, int value) {
        this.from = from;
        this.to = to;
        this.runs = value;
    }

    public Point getFrom() {
        return from;
    }

    public Point getTo() {
        return to;
    }

    public int getRuns() {
        return runs;
    }

    public void setFrom(Point from) {
        this.from = from;
    }

    public void setTo(Point to) {
        this.to = to;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
