package seg.g33.Helpers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import seg.g33.Entitites.*;

public class Drawer {

    public static Paint white = Color.WHITE;
    public static Paint black = Color.BLACK;
    public static Paint grey1 = Color.rgb(64, 64, 64);
    public static Paint grey2 = Color.rgb(128, 128, 128);
    public static Paint grey3 = Color.rgb(192, 192, 192);

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
        angle=angle-90;
        double width = canvas.getWidth();
        double height = canvas.getHeight();

        RunwaySection section1 = runway.getRunwaySections().get(0);
        RunwaySection section2 = runway.getRunwaySections().get(1);
        Double runwayLength = Math.max(section1.getDefaultParameters().getTORA(), section2.getDefaultParameters().getTORA());

        // Runway width is an arbitrary value because it is not mentioned in the specification
        double runwayWidth = 100d;

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

        // Define the points of all shapes
        Double[][] clearedGradedArea = {
                {0d,                height/2-75d*scale},
                {210d*scale,        height/2-75d*scale},
                {360d*scale,        height/2-105d*scale},
                {width-360d*scale,  height/2-105d*scale},
                {width-210d*scale,  height/2-75d*scale},
                {width,             height/2-75d*scale},
                {width,             height/2+75d*scale},
                {width-210d*scale,  height/2+75d*scale},
                {width-360d*scale,  height/2+105d*scale},
                {360d*scale,        height/2+105d*scale},
                {210d*scale,        height/2+75d*scale},
                {0d,                height/2+75d*scale}
        };
        Double[][] leftTORAPoints = {
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-30},
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale+params1.getTORA()*scale, height/2-runwayWidth/2-30}};
        Double[][] leftASDAPoints = {
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-50},
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale+params1.getASDA()*scale, height/2-runwayWidth/2-50}};
        Double[][] leftTODAPoints = {
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-70},
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale+params1.getTODA()*scale, height/2-runwayWidth/2-70}};
        Double[][] leftLDAPoints  = {
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale,                          height/2-runwayWidth/2-90},
                {60d*scale+rightClearway+section1.getDisplacedThreshold()*scale+params1.getLDA()*scale,   height/2-runwayWidth/2-90}};

        Double[] leftTORAText = {leftTORAPoints[0][0], leftTORAPoints[0][1]-5};
        Double[] leftASDAText = {leftASDAPoints[0][0], leftASDAPoints[0][1]-5};
        Double[] leftTODAText = {leftTODAPoints[0][0], leftTODAPoints[0][1]-5};
        Double[] leftLDAText = {leftLDAPoints[0][0], leftLDAPoints[0][1]-5};

        Double[][] rightTORAPoints = {
                {60d*scale+rightClearway+runwayLength,                            height/2+runwayWidth/2+30},
                {60d*scale+rightClearway+runwayLength-params2.getTORA()*scale,    height/2+runwayWidth/2+30}};
        Double[][] rightASDAPoints = {
                {60d*scale+rightClearway+runwayLength,                            height/2+runwayWidth/2+50},
                {60d*scale+rightClearway+runwayLength-params2.getASDA()*scale,    height/2+runwayWidth/2+50}};
        Double[][] rightTODAPoints = {
                {60d*scale+rightClearway+runwayLength,                            height/2+runwayWidth/2+70},
                {60d*scale+rightClearway+runwayLength-params2.getTODA()*scale,    height/2+runwayWidth/2+70}};
        Double[][] rightLDAPoints  = {
                {60d*scale+rightClearway+runwayLength,                            height/2+runwayWidth/2+90},
                {60d*scale+rightClearway+runwayLength-params2.getLDA()*scale,    height/2+runwayWidth/2+90}};

        Double[] rightTORAText = {rightTORAPoints[0][0], rightTORAPoints[0][1]+10};
        Double[] rightASDAText = {rightASDAPoints[0][0], rightASDAPoints[0][1]+10};
        Double[] rightTODAText = {rightTODAPoints[0][0], rightTODAPoints[0][1]+10};
        Double[] rightLDAText = {rightLDAPoints[0][0], rightLDAPoints[0][1]+10};

        Double[] obstaclePoint = {60d*scale+rightClearway+obstacle.getLeftDistance()*scale, height/2+obstacle.getCenterDistance()*scale};
        Double[] leftIndicatorPoints =  {60d*scale+rightClearway+10, height/2};
        Double[] rightIndicatorPoints = {60d*scale+rightClearway+runwayLength-10, height/2};
        // Right clearway (appears on the left of the runway)
        Double[][] rightClearwayPoints =    {
                {60d*scale, height/2-runwayWidth/2-5},
                {60d*scale, height/2+runwayWidth/2+5},
                {60d*scale+rightClearway, height/2+runwayWidth/2+5},
                {60d*scale+rightClearway, height/2-runwayWidth/2-5}};
        // Right stopway (appears on the left of the runway)
        Double[][] rightStopwayPoints =     {
                {60d*scale+rightClearway-rightStopway, height/2-runwayWidth/2},
                {60d*scale+rightClearway-rightStopway, height/2+runwayWidth/2},
                {60d*scale+rightClearway, height/2+runwayWidth/2},
                {60d*scale+rightClearway, height/2-runwayWidth/2}};
        // Runway points
        Double[][] runwayPoints =           {
                {60d*scale+rightClearway, height/2-runwayWidth/2},
                {60d*scale+rightClearway, height/2+runwayWidth/2},
                {60d*scale+rightClearway+runwayLength, height/2+runwayWidth/2},
                {60d*scale+rightClearway+runwayLength, height/2-runwayWidth/2}};
        // Left stopway points (appears on the right of the runway)
        Double[][] leftClearwayPoints =      {
                {60d*scale+rightClearway+runwayLength, height/2-runwayWidth/2-5},
                {60d*scale+rightClearway+runwayLength, height/2+runwayWidth/2+5},
                {60d*scale+rightClearway+runwayLength+leftClearway, height/2+runwayWidth/2+5},
                {60d*scale+rightClearway+runwayLength+leftClearway, height/2-runwayWidth/2-5}};
        // Left clearway points (appears on the right of the runway)
        Double[][] leftStopwayPoints =     {
                {60d*scale+rightClearway+runwayLength, height/2-runwayWidth/2},
                {60d*scale+rightClearway+runwayLength, height/2+runwayWidth/2},
                {60d*scale+rightClearway+runwayLength+leftStopway, height/2+runwayWidth/2},
                {60d*scale+rightClearway+runwayLength+leftStopway, height/2-runwayWidth/2}};
        Double[][] centerLine = {
                {60d*scale+rightClearway+25, height/2},
                {60d*scale+rightClearway+runwayLength-25, height/2}
        };
        // Rotate all points around the center of the screen to match their real world rotation
        obstaclePoint = rotateAroundPoint(angle, width/2, height/2, obstaclePoint);
        leftIndicatorPoints = rotateAroundPoint(angle, width/2, height/2, leftIndicatorPoints);
        rightIndicatorPoints = rotateAroundPoint(angle, width/2, height/2, rightIndicatorPoints);
        leftTORAText = rotateAroundPoint(angle, width/2, height/2, leftTORAText);
        leftASDAText = rotateAroundPoint(angle, width/2, height/2, leftASDAText);
        leftTODAText = rotateAroundPoint(angle, width/2, height/2, leftTODAText);
        leftLDAText = rotateAroundPoint(angle, width/2, height/2, leftLDAText);
        rightTORAText = rotateAroundPoint(angle, width/2, height/2, rightTORAText);
        rightASDAText = rotateAroundPoint(angle, width/2, height/2, rightASDAText);
        rightTODAText = rotateAroundPoint(angle, width/2, height/2, rightTODAText);
        rightLDAText = rotateAroundPoint(angle, width/2, height/2, rightLDAText);
        for (int i = 0; i < 2; i++){
            leftTORAPoints[i] = rotateAroundPoint(angle, width/2, height/2, leftTORAPoints[i]);
            leftASDAPoints[i] = rotateAroundPoint(angle, width/2, height/2, leftASDAPoints[i]);
            leftTODAPoints[i] = rotateAroundPoint(angle, width/2, height/2, leftTODAPoints[i]);
            leftLDAPoints[i] =  rotateAroundPoint(angle, width/2, height/2, leftLDAPoints[i] );

            rightTORAPoints[i] = rotateAroundPoint(angle, width/2, height/2, rightTORAPoints[i]);
            rightASDAPoints[i] = rotateAroundPoint(angle, width/2, height/2, rightASDAPoints[i]);
            rightTODAPoints[i] = rotateAroundPoint(angle, width/2, height/2, rightTODAPoints[i]);
            rightLDAPoints[i] =  rotateAroundPoint(angle, width/2, height/2, rightLDAPoints[i] );

            centerLine[i] = rotateAroundPoint(angle, width/2, height/2, centerLine[i] );
        }
        for (int i = 0; i < 4; i++){
            rightClearwayPoints[i]  = rotateAroundPoint(angle, width/2, height/2, rightClearwayPoints[i]);
            rightStopwayPoints[i]   = rotateAroundPoint(angle, width/2, height/2, rightStopwayPoints[i]);
            runwayPoints[i]         = rotateAroundPoint(angle, width/2, height/2, runwayPoints[i]);
            leftClearwayPoints[i]   = rotateAroundPoint(angle, width/2, height/2, leftClearwayPoints[i]);
            leftStopwayPoints[i]    = rotateAroundPoint(angle, width/2, height/2, leftStopwayPoints[i]);
        }

        // TODO Draw all elements onto the screen
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(white);
        // Fill the background in white
        gc.fillRect(0, 0, width, height);

        // Draw cleared and graded area
        gc.setFill(grey1);
        gc.fillPolygon(getFromIndex(0, clearedGradedArea), getFromIndex(1, clearedGradedArea), 12);
        // Draw clear-ways
        gc.strokePolygon(getFromIndex(0, rightClearwayPoints), getFromIndex(1, rightClearwayPoints), 4);
        gc.strokePolygon(getFromIndex(0, leftClearwayPoints), getFromIndex(1, leftClearwayPoints), 4);
        // Draw stop-ways
        gc.setFill(grey2);
        gc.fillPolygon(getFromIndex(0, rightStopwayPoints), getFromIndex(1, rightStopwayPoints), 4);
        gc.fillPolygon(getFromIndex(0, leftStopwayPoints), getFromIndex(1, leftStopwayPoints), 4);
        // Draw runway
        gc.setFill(grey3);
        gc.fillPolygon(getFromIndex(0, runwayPoints), getFromIndex(1, runwayPoints), 4);

        // Draw Lines
        gc.strokeLine(rightTORAPoints[0][0], rightTORAPoints[0][1], rightTORAPoints[1][0], rightTORAPoints[1][1]);
        gc.strokeLine(rightASDAPoints[0][0], rightASDAPoints[0][1], rightASDAPoints[1][0], rightASDAPoints[1][1]);
        gc.strokeLine(rightTODAPoints[0][0], rightTODAPoints[0][1], rightTODAPoints[1][0], rightTODAPoints[1][1]);
        gc.strokeLine(rightLDAPoints[0][0],  rightLDAPoints[0][1],  rightLDAPoints[1][0],  rightLDAPoints[1][1]);

        gc.strokeLine(leftTORAPoints[0][0], leftTORAPoints[0][1], leftTORAPoints[1][0], leftTORAPoints[1][1]);
        gc.strokeLine(leftASDAPoints[0][0], leftASDAPoints[0][1], leftASDAPoints[1][0], leftASDAPoints[1][1]);
        gc.strokeLine(leftTODAPoints[0][0], leftTODAPoints[0][1], leftTODAPoints[1][0], leftTODAPoints[1][1]);
        gc.strokeLine(leftLDAPoints[0][0],  leftLDAPoints[0][1],  leftLDAPoints[1][0],  leftLDAPoints[1][1]);

        // Draw centerLine
        gc.setStroke(white);
        gc.strokeLine(centerLine[0][0], centerLine[0][1], centerLine[1][0], centerLine[1][1]);

        // Draw Obstacle
        gc.setFill(black);
        gc.fillOval(obstaclePoint[0]-5, obstaclePoint[1]-5, 10, 10);

        // Draw Threshold identifiers
        gc.setFill(white);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(new Font(18d/1000*width));
        rotateText(gc, leftIndicatorPoints[0], leftIndicatorPoints[1], String.format("%s", section1.getAngle()), angle+90);
        rotateText(gc, rightIndicatorPoints[0], rightIndicatorPoints[1], String.format("%s", section2.getAngle()), angle-90);
        gc.setFill(black);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font(15d/1000*width));
        rotateText(gc, leftTORAText[0], leftTORAText[1], String.format("TORA: %sm >", params1.getTORA()), angle);
        rotateText(gc, leftASDAText[0], leftASDAText[1], String.format("ASDA: %sm >", params1.getASDA()), angle);
        rotateText(gc, leftTODAText[0], leftTODAText[1], String.format("TODA: %sm >", params1.getTODA()), angle);
        rotateText(gc, leftLDAText[0], leftLDAText[1], String.format("LDA: %sm >", params1.getLDA()), angle);
        gc.setTextAlign(TextAlignment.RIGHT);
        rotateText(gc, rightTORAText[0], rightTORAText[1], String.format("< TORA: %sm", params2.getTORA()), angle);
        rotateText(gc, rightASDAText[0], rightASDAText[1], String.format("< ASDA: %sm", params2.getASDA()), angle);
        rotateText(gc, rightTODAText[0], rightTODAText[1], String.format("< TODA: %sm", params2.getTODA()), angle);
        rotateText(gc, rightLDAText[0], rightLDAText[1], String.format("< LDA: %sm", params2.getLDA()), angle);
    }

    /**
     * Takes in a list of points [[x1,y1],[x2,y2],[x3,y3],[x4,y4]] and returns a list of [x1, x2, x3, x4] depending on which index is given
     * @param i the index to take from each point
     * @param lists the list of points
     * @return the list of ith indexed points
     */
    private static double[] getFromIndex(Integer i, Double[][] lists){
        double[] list = new double[lists.length];
        for (int j = 0; j < lists.length; j ++){
            list[j] = lists[j][i];
        }
        return list;
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

    private static void rotateText(GraphicsContext gc, Double x, Double y, String text, Integer angle){
        gc.save();

        gc.translate(x, y);
        gc.rotate(angle);
        gc.fillText(text, 0, 0);

        gc.restore();
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
