package com.example.indoorlocalization;

import android.graphics.PointF;

import java.util.Random;

public class Particle {

    public PointF[] landmarks;
    public int worldWidth;
    public float orientation;
    public int worldHeight;
    public float x;
    public float y;
    public Random random;
    public double probability = 0;
    public float forwardNoise, turnNoise, senseNoise;

    //generates initial particles
    public Particle(PointF[] landmarks, int width, int height) {
        this.landmarks = landmarks;
        this.worldWidth = width;
        this.worldHeight = height;
        random = new Random();
        x = random.nextFloat() * width;
        y = random.nextFloat() * (height )  ;
        y = y + 5.98f ;
        orientation = random.nextFloat() * 2f * ((float)Math.PI);

    }

    //move method
    public void move(float turn, float forward)throws Exception {
        if(forward < 0) {
            throw new Exception("cannot move backwards");
        }
        orientation = orientation + turn + (float)random.nextGaussian();
        orientation = circle(orientation, 2f * (float)Math.PI);

        double dist = forward * 0.6 ;  // one step length is assumed as 60 cm

        x += Math.cos(orientation) * dist;
        y += Math.sin(orientation) * dist;
        x = circle(x, worldWidth);
        y = circle(y, worldHeight);
    }

    private float circle(float num, float length) {
        while(num > length - 1) num -= length;
        while(num < 0) num += length;
        return num;
    }

    public void set(float x, float y, float orientation, double probability) throws Exception {
        if(x < 0 || x >= worldWidth) {
            throw new Exception("X coordinate out of bounds");
        }
        if(y < 0 || y >= worldHeight) {
            throw new Exception("Y coordinate out of bounds");
        }
        if(orientation < 0 || orientation >= 2 * Math.PI) {
            throw new Exception("X coordinate out of bounds");
        }
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.probability = probability;
    }

}
