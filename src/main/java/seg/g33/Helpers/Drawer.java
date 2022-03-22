package seg.g33.Helpers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.w3c.dom.ls.LSOutput;
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
     *
     * @param canvas   the canvas to draw on
     * @param angle    the angle to draw the runway at
     * @param runway   the runway to draw
     * @param obstacle the obstacle to draw on the runway
     * @param params1  the redeclared parameters for the lesser angle runway
     * @param params2  the redeclared parameters for the higher angle runway
     */
    public static void drawTopDown(Canvas canvas, Integer angle, Runway runway, Obstacle obstacle, RunwayParameters params1, RunwayParameters params2){
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
        // TODO Add rotation stuff
//        angle=angle-90;
//        gc.save();
//        gc.translate(width/2, height/2);
//        gc.rotate(angle);
//        gc.translate(-width/2, -height/2);

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
        gc.strokeLine(rightLDAPoints[0][0], rightLDAPoints[0][1], rightLDAPoints[1][0], rightLDAPoints[1][1]);

        gc.strokeLine(leftTORAPoints[0][0], leftTORAPoints[0][1], leftTORAPoints[1][0], leftTORAPoints[1][1]);
        gc.strokeLine(leftASDAPoints[0][0], leftASDAPoints[0][1], leftASDAPoints[1][0], leftASDAPoints[1][1]);
        gc.strokeLine(leftTODAPoints[0][0], leftTODAPoints[0][1], leftTODAPoints[1][0], leftTODAPoints[1][1]);
        gc.strokeLine(leftLDAPoints[0][0], leftLDAPoints[0][1], leftLDAPoints[1][0], leftLDAPoints[1][1]);

        // Draw centerLine
        gc.setStroke(white);
        gc.strokeLine(centerLine[0][0], centerLine[0][1], centerLine[1][0], centerLine[1][1]);

        // Draw Obstacle
        gc.setFill(black);
        gc.fillOval(obstaclePoint[0] - 5, obstaclePoint[1] - 5, 10, 10);

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
        gc.setTextAlign(TextAlignment.LEFT);
        // TODO Make sure all elements are drawn on the screen after rotation
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

    private static void rotateText(GraphicsContext gc, Double x, Double y, String text, Integer angle) {
        gc.save();
        gc.translate(x, y);
        gc.rotate(angle);
        gc.fillText(text, 0, 0);
        gc.restore();
    }

    /**
     * Draws the runway from the top-down view
     *
     * @param canvas   the canvas to draw on
     * @param runway   the runway to draw
     * @param obstacle the obstacle to draw on the runway
     * @param plane    the plane to draw landing on the runway
     * @param params1  the first set of redeclared parameters
     * @param params2  the second set of redeclared parameters
     */
    public static void drawSideOn(Canvas canvas, Runway runway, Obstacle obstacle, Plane plane, RunwayParameters params1, RunwayParameters params2) {
        Double width = canvas.getWidth();
        Double height = canvas.getHeight();

        // Get two sections
        RunwaySection leftSection = runway.getRunwaySections().get(0);
        RunwaySection rightSection = runway.getRunwaySections().get(1);

        // Blank for 60d on each side
        Double runwayLength = Math.max(leftSection.getDefaultParameters().getTORA(), rightSection.getDefaultParameters().getTORA()) + 120d;

        // Runway width is an arbitrary value because it is not mentioned in the specification
        Double leftClearway = rightSection.getClearWayLength();
        Double rightClearway = leftSection.getClearWayLength();

        Double leftStopway = rightSection.getStopWayLength();
        Double rightStopway = leftSection.getStopWayLength();

        // Normalises scale so that runway will fit onto the canvas
        Double totalLength = runwayLength + Math.max(leftClearway, leftStopway) + Math.max(rightClearway, rightStopway);
        Double scale = width / totalLength;
        Double leftLength = Math.max(leftClearway, leftStopway) * scale;
        Double rightLength = Math.max(rightClearway, rightStopway) * scale;
        runwayLength *= scale;
        leftStopway *= scale;
        rightStopway *= scale;

        // Define the points of all shapes
        Double heightUp = height / 2 + 25d * scale;
        Double heightDown = height / 2 - 25d * scale;

        Double[][] leftTORAPoints;
        Double[][] leftASDAPoints;
        Double[][] leftTODAPoints;
        Double[][] leftLDAPoints;
        Double[][] leftTOCSPoints;
        Double[][] leftSE1Points;
        Double[][] leftDTPoints;
        Double[][] leftBlastPoints;
        Double[][] leftRESAPoints;
        Double[][] leftSE2Points;

        Double[][] rightTORAPoints;
        Double[][] rightASDAPoints;
        Double[][] rightTODAPoints;
        Double[][] rightLDAPoints;
        Double[][] rightDTPoints;

        Double[][] slopeAnglesPoints;

        // Judging Take Off Away or Take Off Towards
        Boolean takeOffAway = obstacle.getLeftDistance() < Math.max(leftSection.getDefaultParameters().getTORA(), rightSection.getDefaultParameters().getTORA()) / 2;
        String takeOff1;
        String loading1;
        String takeOff2;
        String loading2;

        // The exact location of the obstacle
        Double[] obstaclePoint = {
                60d * scale + leftLength + obstacle.getLeftDistance() * scale + leftSection.getDisplacedThreshold() * scale, -60d * scale + totalLength * scale - rightLength - obstacle.getRightDistance() * scale
        };
        //The filled location of the obstacle
        double[][] obstaclePoints = {
                {obstaclePoint[0] - 2, obstaclePoint[0] - 2, obstaclePoint[1] + 2, obstaclePoint[1] + 2},
                {heightDown - 2 * obstacle.getHeight(), heightUp, heightUp, heightDown - 2 * obstacle.getHeight()}
        };

        if (takeOffAway) {
            leftTORAPoints = new Double[][]{
                    {60d * scale + (leftSection.getDefaultParameters().getTORA() - params1.getTORA()) * scale, height / 2 - 30},
                    {60d * scale + leftSection.getDefaultParameters().getTORA() * scale, height / 2 - 30}};
            leftASDAPoints = new Double[][]{
                    {60d * scale + (leftSection.getDefaultParameters().getASDA() - params1.getASDA()) * scale, height / 2 - 50},
                    {60d * scale + leftSection.getDefaultParameters().getASDA() * scale, height / 2 - 50}};
            leftTODAPoints = new Double[][]{
                    {60d * scale + leftLength + (leftSection.getDefaultParameters().getTODA() - params1.getTODA()) * scale, height / 2 - 70},
                    {60d * scale + leftSection.getDefaultParameters().getTODA() * scale, height / 2 - 70}};
            leftLDAPoints = new Double[][]{
                    {60d * scale + leftLength + (leftSection.getDisplacedThreshold() + leftSection.getDefaultParameters().getLDA() - params1.getLDA()) * scale, height / 2 - 90},
                    {60d * scale + leftLength + (leftSection.getDisplacedThreshold() + leftSection.getDefaultParameters().getLDA()) * scale, height / 2 - 90}};
            leftDTPoints = new Double[][]{
                    {60d * scale + leftLength, height / 2 - 110},
                    {60d * scale + leftLength + leftSection.getDisplacedThreshold() * scale, height / 2 - 110}};
            leftTOCSPoints = new Double[][]{
                    {obstaclePoint[1], height / 2 - 130},
                    {obstaclePoint[1] + (obstacle.getHeight() * plane.getSlope()) * scale, height / 2 - 130}};
            leftSE1Points = new Double[][]{
                    {obstaclePoint[1] + (obstacle.getHeight() * plane.getSlope()) * scale + 2, height / 2 - 130},
                    {obstaclePoint[1] + (obstacle.getHeight() * plane.getSlope() + leftSection.getStripEndLength()) * scale + 2, height / 2 - 130}};
            leftBlastPoints = new Double[][]{
                    {obstaclePoint[1], height / 2 - 150},
                    {obstaclePoint[1] + plane.getBlastProtection() * scale, height / 2 - 150}};
            leftRESAPoints = new Double[][]{
                    {obstaclePoint[1], height / 2 - 170},
                    {obstaclePoint[1] + leftSection.getRESALength() * scale, height / 2 - 170}};
            leftSE2Points = new Double[][]{
                    {obstaclePoint[1] + leftSection.getRESALength() * scale + 2, height / 2 - 170},
                    {obstaclePoint[1] + (leftSection.getRESALength() + leftSection.getStripEndLength()) * scale, height / 2 - 170}};
            rightTORAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDisplacedThreshold() * scale, height / 2 + 30},
                    {-60d * scale + totalLength * scale - rightLength - params2.getTORA() * scale - rightSection.getDisplacedThreshold() * scale, height / 2 + 30}};
            rightASDAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDisplacedThreshold() * scale, height / 2 + 50},
                    {-60d * scale + totalLength * scale - rightLength - params2.getASDA() * scale - rightSection.getDisplacedThreshold() * scale, height / 2 + 50}};
            rightTODAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDisplacedThreshold() * scale, height / 2 + 70},
                    {-60d * scale + totalLength * scale - rightLength - params2.getTODA() * scale - rightSection.getDisplacedThreshold() * scale, height / 2 + 70}};
            rightLDAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDisplacedThreshold() * scale, height / 2 + 90},
                    {-60d * scale + totalLength * scale - rightLength - params2.getLDA() * scale - rightSection.getDisplacedThreshold() * scale, height / 2 + 90}};
            rightDTPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength, height / 2 + 110},
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDisplacedThreshold() * scale + 2, height / 2 + 110}};
            slopeAnglesPoints = new Double[][]{
                    {obstaclePoints[0][3], obstaclePoints[1][3]},
                    {obstaclePoint[1] + (obstacle.getHeight() * plane.getSlope()) * scale, height / 2}};
            takeOff1 = "Take Off Away";
            loading1 = "Loading Over";
            takeOff2 = "Take Off Towards";
            loading2 = "Loading Towards";
        } else {
            leftTORAPoints = new Double[][]{
                    {60d * scale + leftLength, height / 2 - 30},
                    {60d * scale + leftLength + params1.getTORA() * scale, height / 2 - 30}};
            leftASDAPoints = new Double[][]{
                    {60d * scale + leftLength, height / 2 - 50},
                    {60d * scale + leftLength + params1.getASDA() * scale, height / 2 - 50}};
            leftTODAPoints = new Double[][]{
                    {60d * scale + leftLength, height / 2 - 70},
                    {60d * scale + leftLength + params1.getTODA() * scale, height / 2 - 70}};
            leftLDAPoints = new Double[][]{
                    {60d * scale + leftLength, height / 2 - 90},
                    {60d * scale + leftLength + (params1.getLDA()) * scale, height / 2 - 90}};
            leftDTPoints = new Double[][]{
                    {60d * scale + leftLength, height / 2 - 110},
                    {60d * scale + leftLength + leftSection.getDisplacedThreshold() * scale, height / 2 - 110}};
            leftTOCSPoints = new Double[][]{
                    {obstaclePoint[1] - (obstacle.getHeight() * plane.getSlope()) * scale, height / 2 - 130},
                    {obstaclePoint[1], height / 2 - 130}};
            leftSE1Points = new Double[][]{
                    {obstaclePoint[1] - (obstacle.getHeight() * plane.getSlope() + leftSection.getStripEndLength()) * scale, height / 2 - 130},
                    {obstaclePoint[1] - (obstacle.getHeight() * plane.getSlope()) * scale - 2, height / 2 - 130}};
            leftBlastPoints = new Double[][]{
                    {obstaclePoint[1] - plane.getBlastProtection() * scale, height / 2 - 150},
                    {obstaclePoint[1], height / 2 - 150}};
            leftRESAPoints = new Double[][]{
                    {obstaclePoint[1] - leftSection.getRESALength() * scale, height / 2 - 170},
                    {obstaclePoint[1], height / 2 - 170}};
            leftSE2Points = new Double[][]{
                    {obstaclePoint[1] - (leftSection.getRESALength() + leftSection.getStripEndLength()) * scale, height / 2 - 170},
                    {obstaclePoint[1] - leftSection.getRESALength() * scale - 2, height / 2 - 170}};
            rightTORAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - (rightSection.getDefaultParameters().getTORA() - params2.getTORA()) * scale, height / 2 + 30},
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDefaultParameters().getTORA() * scale, height / 2 + 30}};
            rightASDAPoints = new Double[][]{
                    {totalLength * scale - rightLength - (rightSection.getDefaultParameters().getASDA() - params2.getASDA()) * scale, height / 2 + 50},
                    {totalLength * scale - rightLength - rightSection.getDefaultParameters().getASDA() * scale, height / 2 + 50}};
            rightTODAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - (rightSection.getDefaultParameters().getTODA() - params2.getTODA()) * scale, height / 2 + 70},
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDefaultParameters().getTODA() * scale, height / 2 + 70}};
            rightLDAPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength - (rightSection.getDisplacedThreshold() + rightSection.getDefaultParameters().getLDA() - params2.getLDA()) * scale, height / 2 + 90},
                    {-60d * scale + totalLength * scale - rightLength - (rightSection.getDisplacedThreshold() + rightSection.getDefaultParameters().getLDA()) * scale, height / 2 + 90}};
            rightDTPoints = new Double[][]{
                    {-60d * scale + totalLength * scale - rightLength, height / 2 + 110},
                    {-60d * scale + totalLength * scale - rightLength - rightSection.getDisplacedThreshold() * scale + 2, height / 2 + 110}};
            slopeAnglesPoints = new Double[][]{
                    {obstaclePoint[0] - (obstacle.getHeight() * plane.getSlope()) * scale, height / 2},
                    {obstaclePoints[0][0],obstaclePoints[1][0]}};
            takeOff1 = "Take Off Towards";
            loading1 = "Loading Towards";
            takeOff2 = "Take Off Away";
            loading2 = "Loading Over";
        }


        Double[] leftTORAText = {leftTORAPoints[0][0], leftTORAPoints[0][1] - 5};
        Double[] leftASDAText = {leftASDAPoints[0][0], leftASDAPoints[0][1] - 5};
        Double[] leftTODAText = {leftTODAPoints[0][0], leftTODAPoints[0][1] - 5};
        Double[] leftLDAText = {leftLDAPoints[0][0], leftLDAPoints[0][1] - 5};
        Double[] leftTOCSText = {leftTOCSPoints[0][0], leftTOCSPoints[0][1] - 5};
        Double[] leftBlastText = {leftBlastPoints[0][0], leftBlastPoints[0][1] - 5};
        Double[] leftDTText = {leftDTPoints[0][0], leftDTPoints[0][1] - 5};
        Double[] leftRESAText = {leftRESAPoints[0][0], leftRESAPoints[0][1] - 5};
        Double[] leftDesignatorText = {0d, height / 2 - 195};

        Double[] rightTORAText = {rightTORAPoints[0][0], rightTORAPoints[0][1] + 10};
        Double[] rightASDAText = {rightASDAPoints[0][0], rightASDAPoints[0][1] + 10};
        Double[] rightTODAText = {rightTODAPoints[0][0], rightTODAPoints[0][1] + 10};
        Double[] rightLDAText = {rightLDAPoints[0][0], rightLDAPoints[0][1] + 10};
        Double[] rightDTText = {rightDTPoints[0][0], rightDTPoints[0][1] + 10};
        Double[] rightDesignatorText = {totalLength * scale - 60 * scale, height / 2 + 135};

        // Map symbols and location
        double[][] mapSymbol1Points = new double[][]{
                {-60d * scale + totalLength * scale - 100, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 100},
                {height / 2 + 150, height / 2 + 150, height / 2 + 160, height / 2 + 160}};
        double[] mapSymbol1Text = {mapSymbol1Points[0][0] + 15, mapSymbol1Points[1][3] - 1};

        double[][] mapSymbol2Points = new double[][]{
                {-60d * scale + totalLength * scale - 100, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 100},
                {height / 2 + 170, height / 2 + 170, height / 2 + 180, height / 2 + 180}};
        double[] mapSymbol2Text = {mapSymbol2Points[0][0] + 15, mapSymbol2Points[1][3] - 1};

        double[][] mapSymbol3Points = new double[][]{
                {-60d * scale + totalLength * scale - 100, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 100},
                {height / 2 + 190, height / 2 + 190, height / 2 + 200, height / 2 + 200}};
        double[] mapSymbol3Text = {mapSymbol3Points[0][0] + 15, mapSymbol3Points[1][3] - 1};

        double[][] mapSymbol4Points = new double[][]{
                {-60d * scale + totalLength * scale - 100, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 100},
                {height / 2 + 210, height / 2 + 210, height / 2 + 220, height / 2 + 220}};
        double[] mapSymbol4Text = {mapSymbol4Points[0][0] + 15, mapSymbol4Points[1][3] - 1};

        double[][] mapSymbol5Points = new double[][]{
                {-60d * scale + totalLength * scale - 100, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 90, -60d * scale + totalLength * scale - 100},
                {height / 2 + 230, height / 2 + 230, height / 2 + 240, height / 2 + 240}};
        double[] mapSymbol5Text = {mapSymbol5Points[0][0] + 15, mapSymbol5Points[1][3] - 1};


        // Right and Left stopway points (appears on the right of the runway)
        double[][] leftStopwayPoints = {
                {60d * scale + leftLength - leftStopway, 60d * scale + leftLength - leftStopway, 60d * scale + leftLength, 60d * scale + leftLength},
                {heightUp, heightDown, heightDown, heightUp}
        };
        double[][] rightStopwayPoints = {
                {60d * scale + leftLength + runwayLength, 60d * scale + leftLength + runwayLength, 60d * scale + leftLength + runwayLength + rightStopway, 60d * scale + leftLength + runwayLength + rightStopway},
                {heightDown, heightUp, heightUp, heightDown}
        };
        double[][] runwayPoints = {
                {60d * scale + leftLength, 60d * scale + leftLength, -60d * scale + leftLength + runwayLength, -60d * scale + leftLength + runwayLength},
                {heightDown, heightUp, heightUp, heightDown}
        };
        double[][] clearwayArea = {
                {60d * scale, (totalLength - 60d) * scale, (totalLength - 60d) * scale, 60d * scale},
                {heightUp, heightUp, heightDown, heightDown}
        };

        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(white);
        // Fill the background in white
        gc.fillRect(0, 0, width, height);

        // Draw clearway
        gc.setFill(grey1);
        gc.fillPolygon(clearwayArea[0], clearwayArea[1], 4);
        gc.fillPolygon(mapSymbol1Points[0], mapSymbol1Points[1], 4);
        // Draw stop-ways
        gc.setFill(grey2);
        gc.fillPolygon(rightStopwayPoints[0], rightStopwayPoints[1], 4);
        gc.fillPolygon(leftStopwayPoints[0], leftStopwayPoints[1], 4);
        gc.fillPolygon(mapSymbol2Points[0], mapSymbol2Points[1], 4);
        // Draw runway
        gc.setFill(grey3);
        gc.fillPolygon(runwayPoints[0], runwayPoints[1], 4);
        gc.fillPolygon(mapSymbol3Points[0], mapSymbol3Points[1], 4);
        // Draw Lines
        gc.strokeLine(rightTORAPoints[0][0], rightTORAPoints[0][1], rightTORAPoints[1][0], rightTORAPoints[1][1]);
        gc.strokeLine(rightASDAPoints[0][0], rightASDAPoints[0][1], rightASDAPoints[1][0], rightASDAPoints[1][1]);
        gc.strokeLine(rightTODAPoints[0][0], rightTODAPoints[0][1], rightTODAPoints[1][0], rightTODAPoints[1][1]);
        gc.strokeLine(rightLDAPoints[0][0], rightLDAPoints[0][1], rightLDAPoints[1][0], rightLDAPoints[1][1]);

        gc.strokeLine(leftTORAPoints[0][0], leftTORAPoints[0][1], leftTORAPoints[1][0], leftTORAPoints[1][1]);
        gc.strokeLine(leftASDAPoints[0][0], leftASDAPoints[0][1], leftASDAPoints[1][0], leftASDAPoints[1][1]);
        gc.strokeLine(leftTODAPoints[0][0], leftTODAPoints[0][1], leftTODAPoints[1][0], leftTODAPoints[1][1]);
        gc.strokeLine(leftLDAPoints[0][0], leftLDAPoints[0][1], leftLDAPoints[1][0], leftLDAPoints[1][1]);
        gc.strokeLine(leftTOCSPoints[0][0], leftTOCSPoints[0][1], leftTOCSPoints[1][0], leftTOCSPoints[1][1]);
        gc.strokeLine(leftSE1Points[0][0], leftSE1Points[0][1], leftSE1Points[1][0], leftSE1Points[1][1]);
        gc.strokeLine(leftBlastPoints[0][0], leftBlastPoints[0][1], leftBlastPoints[1][0], leftBlastPoints[1][1]);
        if (leftSection.getDisplacedThreshold() > 0)
            gc.strokeLine(leftDTPoints[0][0], leftDTPoints[0][1], leftDTPoints[1][0], leftDTPoints[1][1]);
        if (rightSection.getDisplacedThreshold() > 0)
            gc.strokeLine(rightDTPoints[0][0], rightDTPoints[0][1], rightDTPoints[1][0], rightDTPoints[1][1]);
        gc.strokeLine(slopeAnglesPoints[0][0], slopeAnglesPoints[0][1], slopeAnglesPoints[1][0], slopeAnglesPoints[1][1]);
        gc.strokeLine(leftRESAPoints[0][0], leftRESAPoints[0][1], leftRESAPoints[1][0], leftRESAPoints[1][1]);
        gc.strokeLine(leftSE2Points[0][0], leftSE2Points[0][1], leftSE2Points[1][0], leftSE2Points[1][1]);

        // Draw Obstacle
        gc.setFill(black);
        gc.fillPolygon(obstaclePoints[0], obstaclePoints[1], 4);
        gc.fillPolygon(mapSymbol4Points[0], mapSymbol4Points[1], 4);
        gc.strokePolygon(mapSymbol5Points[0], mapSymbol5Points[1], 4);

        // Draw Threshold identifiers and Displaced Threshold
        gc.setFill(white);
        gc.setTextAlign(TextAlignment.CENTER);
        if (leftSection.getDisplacedThreshold() > 0) {
            double[][] displacedThreshold = {
                    {60d * scale + leftLength + leftSection.getDisplacedThreshold() * scale - 5, 60d * scale + leftLength + leftSection.getDisplacedThreshold() * scale - 5,60d * scale + leftLength + leftSection.getDisplacedThreshold() * scale + 5, 60d * scale + leftLength + leftSection.getDisplacedThreshold() * scale + 5},
                    {heightUp, heightDown, heightDown, heightUp},
            };
            gc.fillPolygon(displacedThreshold[0], displacedThreshold[1], 4);
        }
        if (rightSection.getDisplacedThreshold() > 0) {
            double[][] displacedThreshold = {
                    {-60d * scale + leftLength + runwayLength - rightSection.getDisplacedThreshold() * scale - 5, -60d * scale + leftLength + runwayLength - rightSection.getDisplacedThreshold() * scale - 5, -60d * scale + leftLength + runwayLength - rightSection.getDisplacedThreshold() * scale + 5, heightDown, -60d * scale + leftLength + runwayLength - rightSection.getDisplacedThreshold() * scale + 5, heightUp},
                    {heightUp, heightDown, heightDown, heightUp},
            };
            gc.fillPolygon(displacedThreshold[0], displacedThreshold[1], 4);
        }

        // Text on top
        gc.setFill(black);
        gc.setTextAlign(TextAlignment.LEFT);
        gc.setFont(new Font(15d / 1000 * width));
        rotateText(gc, leftDesignatorText[0], leftDesignatorText[1], String.format("%s%s", leftSection.getAngle(), leftSection.getDirection()), 0);
        rotateText(gc, leftTORAText[0], leftTORAText[1], String.format("TORA: %sm → %s", params1.getTORA(), takeOff1), 0);
        rotateText(gc, leftASDAText[0], leftASDAText[1], String.format("ASDA: %sm → %s", params1.getASDA(), takeOff1), 0);
        rotateText(gc, leftTODAText[0], leftTODAText[1], String.format("TODA: %sm → %s", params1.getTODA(), takeOff1), 0);
        rotateText(gc, leftLDAText[0], leftLDAText[1], String.format("LDA: %sm → %s", params1.getLDA(), loading1), 0);
        rotateText(gc, leftTOCSText[0], leftTOCSText[1], String.format("TOCS/ALS: %sm", obstacle.getHeight() * plane.getSlope()), 0);
        rotateText(gc, leftBlastText[0], leftBlastText[1], String.format("BD: %sm", plane.getBlastProtection()), 0);
        if (leftSection.getDisplacedThreshold() > 0)
            rotateText(gc, leftDTText[0], leftDTText[1], String.format("DT: %sm", leftSection.getDisplacedThreshold()), 0);
        rotateText(gc, leftRESAText[0], leftRESAText[1], String.format("RESA: %sm", leftSection.getRESALength()), 0);
        rotateText(gc, mapSymbol1Text[0], mapSymbol1Text[1], String.format("Clearway"), 0);
        rotateText(gc, mapSymbol2Text[0], mapSymbol2Text[1], String.format("Stopway"), 0);
        rotateText(gc, mapSymbol3Text[0], mapSymbol3Text[1], String.format("Runway"), 0);
        rotateText(gc, mapSymbol4Text[0], mapSymbol4Text[1], String.format("Obstacle"), 0);
        rotateText(gc, mapSymbol5Text[0], mapSymbol5Text[1], String.format("Displaced Threshold"), 0);

        // Text on bottom
        gc.setTextAlign(TextAlignment.RIGHT);
        rotateText(gc, rightDesignatorText[0], rightDesignatorText[1], String.format("%s%s", rightSection.getAngle(), rightSection.getDirection()), 0);
        rotateText(gc, rightTORAText[0], rightTORAText[1], String.format("%s ← TORA: %sm", takeOff2, params2.getTORA()), 0);
        rotateText(gc, rightASDAText[0], rightASDAText[1], String.format("%s ← ASDA: %sm", takeOff2, params2.getASDA()), 0);
        rotateText(gc, rightTODAText[0], rightTODAText[1], String.format("%s ← TODA: %sm", takeOff2, params2.getTODA()), 0);
        rotateText(gc, rightLDAText[0], rightLDAText[1], String.format("%s ← LDA: %sm", loading2, params2.getLDA()), 0);
        if (rightSection.getDisplacedThreshold() > 0)
            rotateText(gc, rightDTText[0], rightDTText[1], String.format("DT: %sm", rightSection.getDisplacedThreshold()), 0);
    }
}
