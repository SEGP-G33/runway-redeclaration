package seg.g33.Helpers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import seg.g33.Entitites.*;

public class Drawer {

    /**
     * Draws the runway from the top down view
     * For requirement 2, 10 and 11
     * @param canvas the canvas to draw on
     * @param angle the angle to draw the runway at
     * @param runway the runway to draw
     * @param obstacle the obstacle to draw on the runway
     * @param params1 the redeclared parameters for the lesser angle runway
     * @param params2 the redeclared parameters for the higher angle runway
     */
    public static void drawTopDown(Canvas canvas, Integer angle, Runway runway, Obstacle obstacle, RunwayParameters params1, RunwayParameters params2){
        // TODO Setup the needed parameters
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        RunwaySection section1 = runway.getRunwaySections().get(0);
        RunwaySection section2 = runway.getRunwaySections().get(1);
        Double runwayLength = Math.max(section1.getDefaultParameters().getTORA(), section2.getDefaultParameters().getTORA());

        // Runway width is an arbitrary value because it is not mentioned in the specification
        double runwayWidth = 150d;

        Double leftClearway = section1.getClearWayLength();
        Double rightClearway = section2.getClearWayLength();

        Double leftStopway = section1.getStopWayLength();
        Double rightStopway = section2.getStopWayLength();

        // Normalises scale so that runway will fit onto the canvas
        double totalLength = runwayLength + Math.max(leftClearway, leftStopway) + Math.max(rightClearway, rightStopway) + 120;
        double scale = width / totalLength;
        runwayLength *= scale;
        runwayWidth *= scale;
        leftClearway *= scale;
        rightClearway *= scale;
        leftStopway *= scale;
        rightStopway *= scale;

        Double[] obstaclePoint = {60d+rightClearway+obstacle.getLeftDistance(), height/2+obstacle.getCenterDistance()};
        // Right clearway (appears on the left of the runway)
        Double[][] rightClearwayPoints =    {
                {60d, height/2-runwayWidth/2-50},
                {60d, height/2+runwayWidth/2+50},
                {60d+rightClearway, height/2-runwayWidth/2-50},
                {60d+rightClearway, height/2+runwayWidth/2+50}};
        // Right stopway (appears on the left of the runway)
        Double[][] rightStopwayPoints =     {
                {60d+rightClearway-rightStopway, height/2-runwayWidth/2},
                {60d+rightClearway-rightStopway, height/2+runwayWidth/2},
                {60d+rightClearway, height/2-runwayWidth/2},
                {60d+rightClearway, height/2+runwayWidth/2}};
        // Runway points
        Double[][] runwayPoints =           {
                {60d+rightClearway, height/2-runwayWidth/2},
                {60d+rightClearway, height/2+runwayWidth/2},
                {60d+rightClearway+runwayLength, height/2-runwayWidth/2},
                {60d+rightClearway+runwayLength, height/2+runwayWidth/2}};
        // Left stopway points (appears on the right of the runway)
        Double[][] leftClearwayPoints =      {
                {60d+rightClearway+runwayLength, height/2-runwayWidth/2-50},
                {60d+rightClearway+runwayLength, height/2+runwayWidth/2+50},
                {60d+rightClearway+runwayLength+leftClearway, height/2-runwayWidth/2-50},
                {60d+rightClearway+runwayLength+leftClearway, height/2+runwayWidth/2+50}};
        // Left clearway points (appears on the right of the runway)
        Double[][] leftStopwayPoints =     {
                {60d+rightClearway+runwayLength, height/2-runwayWidth/2},
                {60d+rightClearway+runwayLength, height/2+runwayWidth/2},
                {60d+rightClearway+runwayLength+leftStopway, height/2-runwayWidth/2},
                {60d+rightClearway+runwayLength+leftStopway, height/2+runwayWidth/2}};
        // Rotate all points around the center of the screen to match their real world rotation
        obstaclePoint = rotateAroundPoint(angle, width/2, height/2, obstaclePoint);
        for (int i = 0; i < 4; i++){
            rightClearwayPoints[i]  = rotateAroundPoint(angle, width/2, height/2, rightClearwayPoints[i]);
            rightStopwayPoints[i]   = rotateAroundPoint(angle, width/2, height/2, rightStopwayPoints[i]);
            runwayPoints[i]         = rotateAroundPoint(angle, width/2, height/2, runwayPoints[i]);
            leftClearwayPoints[i]   = rotateAroundPoint(angle, width/2, height/2, leftClearwayPoints[i]);
            leftStopwayPoints[i]    = rotateAroundPoint(angle, width/2, height/2, leftStopwayPoints[i]);
        }

        // TODO Draw all elements onto the screen
    }

    /**
     * Uses a rotation matrix to rotate a vector around a given point (x, y)
     * @param angle the angle to rotate through in degrees
     * @param x the x-coordinate to rotate around
     * @param y the y-coordinate to rotate around
     * @param point the point to rotate
     * @return the rotated point
     */
    public static Double[] rotateAroundPoint(Integer angle, Double x, Double y, Double[] point){
        // By drawing all shapes as polygons rather than rectangles, I can apply the following rotation matrix to their
        // coordinate points so the runway is rotated
        point[0] -= x;
        point[1] -= y;
        Double[][] rotationMatrix = {{Math.cos(Math.toRadians(angle)), -Math.sin(Math.toRadians(angle))},
                {Math.sin(Math.toRadians(angle)),  Math.cos(Math.toRadians(angle))}};
        Double[] result = new Double[2];
        result[0] = (double) Math.round(rotationMatrix[0][0] * point[0] + rotationMatrix[0][1] * point[1]);
        result[1] = (double) Math.round(rotationMatrix[1][0] * point[0] + rotationMatrix[1][1] * point[1]);

        result[0] += x;
        result[1] += y;

        return result;
    }

    /**
     * Draws the runway from the top-down view
     * @param canvas the canvas to draw on
     * @param runway the runway to draw
     * @param obstacle the obstacle to draw on the runway
     * @param plane the plane to draw landing on the runway
     * @param params1 the first set of redeclared parameters
     * @param params2 the second set of redeclared parameters
     */
    public static void drawSideOn(Canvas canvas, Runway runway, Obstacle obstacle, Plane plane, RunwayParameters params1, RunwayParameters params2){
        // TODO
    }
}
