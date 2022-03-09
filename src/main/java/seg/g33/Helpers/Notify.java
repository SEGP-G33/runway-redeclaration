package seg.g33.Helpers;

import seg.g33.Entitites.*;


import java.util.ArrayList;

public class Notify {

    private ArrayList<Airport> addedAirport;
    private ArrayList<Runway> addedRunway;
    private ArrayList<Obstacle> addedObstacle;
    private ArrayList<Plane> addedPlane;
    private ArrayList<Calculator> addedCalculations;

    private ArrayList<Airport> removedAirport;
    private ArrayList<Runway> removedRunway;
    private ArrayList<Obstacle> removedObstacle;
    private ArrayList<Plane> removedPlane;

    public Notify(){
        this.addedAirport = new ArrayList<>();
        this.addedRunway = new ArrayList<>();
        this.addedObstacle = new ArrayList<>();
        this.addedPlane = new ArrayList<>();
        this.addedCalculations = new ArrayList<>();
        this.removedAirport = new ArrayList<>();
        this.removedObstacle = new ArrayList<>();
        this.removedPlane = new ArrayList<>();
        this.removedRunway = new ArrayList<>();
    }

    public ArrayList<Airport> getAddedAirport() {
        return addedAirport;
    }

    public void setAddedAirport(ArrayList<Airport> addedAirport) {
        this.addedAirport = addedAirport;
    }

    public ArrayList<Runway> getAddedRunway() {
        return addedRunway;
    }

    public void setAddedRunway(ArrayList<Runway> addedRunway) {
        this.addedRunway = addedRunway;
    }

    public ArrayList<Obstacle> getAddedObstacle() {
        return addedObstacle;
    }

    public void setAddedObstacle(ArrayList<Obstacle> addedObstacle) {
        this.addedObstacle = addedObstacle;
    }

    public ArrayList<Plane> getAddedPlane() {
        return addedPlane;
    }

    public void setAddedPlane(ArrayList<Plane> addedPlane) {
        this.addedPlane = addedPlane;
    }

    public ArrayList<Calculator> getAddedCalculations() {
        return addedCalculations;
    }

    public void setAddedCalculations(ArrayList<Calculator> addedCalculations) {
        this.addedCalculations = addedCalculations;
    }

    public ArrayList<Airport> getRemovedAirport() {
        return removedAirport;
    }

    public void setRemovedAirport(ArrayList<Airport> removedAirport) {
        this.removedAirport = removedAirport;
    }

    public ArrayList<Runway> getRemovedRunway() {
        return removedRunway;
    }

    public void setRemovedRunway(ArrayList<Runway> removedRunway) {
        this.removedRunway = removedRunway;
    }

    public ArrayList<Obstacle> getRemovedObstacle() {
        return removedObstacle;
    }

    public void setRemovedObstacle(ArrayList<Obstacle> removedObstacle) {
        this.removedObstacle = removedObstacle;
    }

    public ArrayList<Plane> getRemovedPlane() {
        return removedPlane;
    }

    public void setRemovedPlane(ArrayList<Plane> removedPlane) {
        this.removedPlane = removedPlane;
    }
}