package application;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {
	  public Controller() {}

	private boolean loaded = false;
	  
	@FXML
	private TextField ValueA;
	@FXML
	private TextField ValueB;
	@FXML
	private TextField ValueC;
	
	@FXML
	private Canvas canvas;
	
	private GraphicsContext g;
	private int gridSize = 25;
	private double scaleX, scaleY, width, height;
	
	
	private void init() {
		g = canvas.getGraphicsContext2D();
		
		width = canvas.getWidth();
		height = canvas.getHeight();
		scaleX = scaleY = gridSize;
		
		loaded = true;
	}
	
	@FXML
	private void calculate() {
		if(!loaded) {init();}
		
		double A,B,C;
		try {
			A = Double.parseDouble(ValueA.getText());
			B = Double.parseDouble(ValueB.getText());
			C = Double.parseDouble(ValueC.getText());
		} catch (NumberFormatException e) {
			A = B = C = 0;
		}
		
		drawGrid();
		drawLine(A,B,C);
		
	}
	
	private void drawLine(double A, double B, double C) {
		boolean firstPoint = true;
		
		g.setStroke(Color.BLUE);
		g.setLineWidth(1.6);
		
		g.beginPath();//{
		
		for(double X = -50; X <= 50; X+=0.1) {
			/* A*X^2 + B*X + C */
			double n1 = A*Math.pow(X, 2);
			double n2 = B*X;
			double n3 = C;
			
			double Y = n1+n2+n3;
			
			double canvasX = width/2 + X * scaleX;
			double canvasY = height/2 - Y * scaleY;
			
			if(firstPoint) {
				g.moveTo(canvasX, canvasY);
				firstPoint = false;
			} else {
				g.lineTo(canvasX, canvasY);
			}
			
		}
		
		g.stroke();//}
	}
	
	private void drawGrid() {
		g.setStroke(Color.WHITE);
		g.clearRect(0, 0, width, height); //clear screen
		
		g.setStroke(Color.LIGHTGRAY);
		g.setLineWidth(0.5);
		
		for(int i = 0; i<=width; i+=gridSize) {
			g.strokeLine(i,0,i,height);
		}
		for(int i = 0; i<=height; i+=gridSize) {
			g.strokeLine(0,i,width,i);
		}
		
        g.setStroke(Color.BLACK);
        g.setLineWidth(1);

        g.strokeLine(0, height / 2, width, height / 2);
        g.strokeLine(width / 2, 0, width / 2, height);
	}
}
