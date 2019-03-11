package czbank_simple_java.whyisthatyou.gobanggame;
import java.awt.Color;
public class Point {
private int x;
private int y;
private Color color;
public static final int DIAMETTER=30;
public Point(int x,int y,Color color) {
	this.x=x;
	this.y=y;
	this.color=color;
	
}
public int getX() {
	return x;
	
}
public int getY() {
	return y;
	
}
public Color getColor() {
	return color;
	
}
}
