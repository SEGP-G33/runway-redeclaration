package seg.g33.Helpers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
        double runwayStart = 60d;

        Double leftClearway = section1.getClearWayLength();
        Double rightClearway = section2.getClearWayLength();

        Double leftStopway = section1.getStopWayLength();
        Double rightStopway = section2.getStopWayLength();

        // Normalises scale so that runway will fit onto the canvas
        double totalLength = runwayLength + Math.max(leftClearway, leftStopway) + Math.max(rightClearway, rightStopway) + 120;
        double scale = width / totalLength;
        runwayStart *= scale;
        runwayLength *= scale;
        runwayWidth *= scale;
        leftClearway *= scale;
        rightClearway *= scale;
        leftStopway *= scale;
        rightStopway *= scale;

        // Define the points of all shapes
        double[] obstaclePoint = {runwayStart+rightClearway+obstacle.getLeftDistance()*scale, height/2+obstacle.getCenterDistance()*scale};
        double[] leftIndicatorPoints =  {runwayStart+rightClearway+10, height/2};
        double[] rightIndicatorPoints = {runwayStart+rightClearway+runwayLength-10, height/2};

        double[][] clearedGradedArea = {
                {0d,                 210d*scale,         360d*scale,          width-360d*scale,    width-210d*scale,   width,              width,              width-210d*scale,   width-360d*scale,    360d*scale,          210d*scale,         0d                },
                {height/2-75d*scale, height/2-75d*scale, height/2-105d*scale, height/2-105d*scale, height/2-75d*scale, height/2-75d*scale, height/2+75d*scale, height/2+75d*scale, height/2+105d*scale, height/2+105d*scale, height/2+75d*scale, height/2+75d*scale}};

        double[][] leftTORAPoints = {   {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-30},
                                        {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale+params1.getTORA()*scale, height/2-runwayWidth/2-30}};
        double[][] leftASDAPoints = {   {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-50},
                                        {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale+params1.getASDA()*scale, height/2-runwayWidth/2-50}};
        double[][] leftTODAPoints = {   {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-70},
                                        {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale+params1.getTODA()*scale, height/2-runwayWidth/2-70}};
        double[][] leftLDAPoints  = {   {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale,                         height/2-runwayWidth/2-90},
                                        {runwayStart+rightClearway+section1.getDisplacedThreshold()*scale+params1.getLDA()*scale,  height/2-runwayWidth/2-90}};

        double[][] rightTORAPoints = {  {runwayStart+rightClearway+runwayLength,                            height/2+runwayWidth/2+30},
                                        {runwayStart+rightClearway+runwayLength-params2.getTORA()*scale,    height/2+runwayWidth/2+30}};
        double[][] rightASDAPoints = {  {runwayStart+rightClearway+runwayLength,                            height/2+runwayWidth/2+50},
                                        {runwayStart+rightClearway+runwayLength-params2.getASDA()*scale,    height/2+runwayWidth/2+50}};
        double[][] rightTODAPoints = {  {runwayStart+rightClearway+runwayLength,                            height/2+runwayWidth/2+70},
                                        {runwayStart+rightClearway+runwayLength-params2.getTODA()*scale,    height/2+runwayWidth/2+70}};
        double[][] rightLDAPoints  = {  {runwayStart+rightClearway+runwayLength,                            height/2+runwayWidth/2+90},
                                        {runwayStart+rightClearway+runwayLength-params2.getLDA()*scale,     height/2+runwayWidth/2+90}};

        double[][] centerLine = {       {runwayStart+rightClearway+25,              height/2},
                                        {runwayStart+rightClearway+runwayLength-25, height/2}};

        // Right clearway (appears on the left of the runway)
        double[][] rightClearwayPoints = {  {runwayStart,                            runwayStart,                            runwayStart+rightClearway,                           runwayStart+rightClearway},
                                            {height/2-runwayWidth/2-5,               height/2+runwayWidth/2+5,               height/2+runwayWidth/2-5,                            height/2-runwayWidth/2-5}};
        // Right stopway (appears on the left of the runway)
        double[][] rightStopwayPoints =  {  {runwayStart+rightClearway-rightStopway, runwayStart+rightClearway-rightStopway, runwayStart+rightClearway,                           runwayStart+rightClearway},
                                            {height/2-runwayWidth/2-5,               height/2+runwayWidth/2+5,               height/2+runwayWidth/2-5,                            height/2-runwayWidth/2-5}};
        // Runway points
        double[][] runwayPoints =        {  {runwayStart+rightClearway,              runwayStart+rightClearway,              runwayStart+rightClearway+runwayLength,              runwayStart+rightClearway+runwayLength},
                                            {height/2-runwayWidth/2,                 height/2+runwayWidth/2,                 height/2+runwayWidth/2,                              height/2-runwayWidth/2}};
        // Left stopway points (appears on the right of the runway)
        double[][] leftClearwayPoints =  {  {runwayStart+rightClearway+runwayLength, runwayStart+rightClearway+runwayLength, runwayStart+rightClearway+runwayLength+leftClearway, runwayStart+rightClearway+runwayLength+leftClearway},
                                            {height/2-runwayWidth/2-5,               height/2+runwayWidth/2+5,               height/2+runwayWidth/2-5,                            height/2-runwayWidth/2-5}};
        // Left clearway points (appears on the right of the runway)
        double[][] leftStopwayPoints =   {  {runwayStart+rightClearway+runwayLength, runwayStart+rightClearway+runwayLength, runwayStart+rightClearway+runwayLength+leftStopway,  runwayStart+rightClearway+runwayLength+leftStopway},
                                            {height/2-runwayWidth/2-5,               height/2+runwayWidth/2+5,               height/2+runwayWidth/2-5,                            height/2-runwayWidth/2-5}};


        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(white);
        // Fill the background in white
        gc.fillRect(0, 0, width, height);
        gc.save();
        gc.translate(width/2, height/2);
        gc.rotate(angle);
        gc.translate(-width/2, -height/2);

        // Draw cleared and graded area
        gc.setFill(grey1);
        gc.fillPolygon(clearedGradedArea[0], clearedGradedArea[1], 12);
        // Draw clear-ways
        gc.strokePolygon(rightClearwayPoints[0], rightClearwayPoints[1], 4);
        gc.strokePolygon(leftClearwayPoints[0],  leftClearwayPoints[1], 4 );
        // Draw stop-ways
        gc.setFill(grey2);
        gc.fillPolygon(rightStopwayPoints[0], rightStopwayPoints[1], 4);
        gc.fillPolygon(leftStopwayPoints[0],  leftStopwayPoints[1], 4 );
        // Draw runway
        gc.setFill(grey3);
        gc.fillPolygon(runwayPoints[0], runwayPoints[1], 4);

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
        rotateText(gc, leftIndicatorPoints[0], leftIndicatorPoints[1], String.format("%s", section1.getAngle()), 90);
        rotateText(gc, rightIndicatorPoints[0], rightIndicatorPoints[1], String.format("%s", section2.getAngle()), -90);
        gc.setFill(black);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font(15d/1000*width));
        gc.fillText(String.format("TORA: %sm >", params1.getTORA()), leftTORAPoints[0][0], leftTORAPoints[0][1]-5);
        gc.fillText(String.format("ASDA: %sm >", params1.getASDA()), leftASDAPoints[0][0], leftASDAPoints[0][1]-5);
        gc.fillText(String.format("TODA: %sm >", params1.getTODA()), leftTODAPoints[0][0], leftTODAPoints[0][1]-5);
        gc.fillText(String.format("LDA : %sm >", params1.getLDA()), leftLDAPoints[0][0], leftLDAPoints[0][1]-5);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(String.format("< TORA: %sm", params2.getTORA()), rightTORAPoints[0][0], rightTORAPoints[0][1]+10);
        gc.fillText(String.format("< ASDA: %sm", params2.getASDA()), rightASDAPoints[0][0], rightASDAPoints[0][1]+10);
        gc.fillText(String.format("< TODA: %sm", params2.getTODA()), rightTODAPoints[0][0], rightTODAPoints[0][1]+10);
        gc.fillText(String.format("< LDA : %sm", params2.getLDA()), rightLDAPoints[0][0], rightLDAPoints[0][1]+10);
        gc.restore();
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText("* Obstacle may not be drawn to scale", 5, height-10);
        // Draw the compass
        Image image = new Image(Drawer.class.getResource("/seg/g33/images/compass.png").toExternalForm());
        gc.drawImage(image, 0, 0, 50d, 50d);
        // Draw the scale
        gc.setStroke(black);
        gc.strokeLine(10, height-30, 10+500*scale, height-30);
        gc.strokeLine(10, height-32, 10, height-28);
        gc.strokeLine(10+500*scale, height-32, 10+500*scale, height-28);
        gc.fillText(": 500m", 15+500*scale, height-25);
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
