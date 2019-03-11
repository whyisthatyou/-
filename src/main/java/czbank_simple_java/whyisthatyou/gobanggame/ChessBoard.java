package czbank_simple_java.whyisthatyou.gobanggame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
public class ChessBoard extends JPanel implements MouseListener{
	public static final int MARGIN=30;
	public static final int GRID_SPAN=35;
	public static final int ROWS=10;
	public static final int COLS=10;
	Point[] chessList =new Point[(ROWS+1)*(COLS+1)];
	boolean isBlack=true;
	boolean gameOver=false;
	int chessCount;
	int xIndex,yIndex;
	public ChessBoard() {
		setBackground(Color.ORANGE);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionListener(){
			public void mouseMoved(MouseEvent e) {
				int x1= (e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				int y1=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				if(x1<0 || x1>ROWS || y1<0 || y1>COLS || gameOver || findChess(x1,y1))
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				else 
					setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<=ROWS;i++) {
			g.drawLine(MARGIN,MARGIN+i*GRID_SPAN,MARGIN+COLS*GRID_SPAN,MARGIN+i*GRID_SPAN);
		}
		for(int i=0;i<COLS;i++) {
g.drawLine(MARGIN+i*GRID_SPAN,MARGIN,MARGIN+i*GRID_SPAN,MARGIN+ROWS*GRID_SPAN);
	
			}
		for(int i=0;i<chessCount;i++) {
			int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;
			int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;
			g.setColor(chessList[i].getColor());
			g.fillOval(xPos-Point.DIAMETTER/2,yPos-Point.DIAMETTER/2,
					Point.DIAMETTER,Point.DIAMETTER);
			if(i==chessCount-1) {
				g.setColor(Color.red);
				g.drawRect(xPos-Point.DIAMETTER/2,yPos-Point.DIAMETTER/2,Point.DIAMETTER,Point.DIAMETTER);
			}
			
		}}
		public void mousePressed(MouseEvent e) {
			if(gameOver)
				return;
			String colorName=isBlack ? "黑棋":"白棋";
			xIndex=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
			yIndex=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
			if(xIndex<0||xIndex>ROWS||yIndex<0||yIndex>COLS)
				return;
			if(findChess(xIndex,yIndex))
				return;
			Point ch =new Point(xIndex,yIndex,isBlack ? Color.black : Color.white);
			chessList[chessCount++]=ch;
			repaint();
			if(isWin()) {
				String msg =String.format("恭喜",colorName);
				JOptionPane.showMessageDialog(this,msg);
				gameOver=true;
			}
			isBlack=!isBlack;
		}
		public void mouseClicked(MouseEvent e){
			
		}
		public void mouseEntered(MouseEvent e) {
			
		}
		public void mouseExited(MouseEvent e) {
			
		}
		public void mouseReleased(MouseEvent e) {
			
		}
		private boolean findChess(int x,int y) {
			for(Point c:chessList) {
				if(c!=null&&c.getX()==x&&c.getY()==y)
					return true;
			}
			return false;
		}
		private boolean isWin() {
			int continueCount=1;
			for(int x=xIndex-1;x>=0;x--) {
				Color c=isBlack ? Color.black : Color.white;
				if(getChess(x,yIndex,c)!=null) {
					continueCount++;
				}else
					break;
			}
			for(int x=xIndex+1;x<=ROWS;x++) {
				Color c=isBlack ? Color.black:Color.white;
				if (getChess(x,yIndex,c)!=null) {
					continueCount++;
					
				}else
					break;
			}
			if(continueCount>=5) {
				return true;
				
			}else
				continueCount=1;
			for (int y=yIndex-1;y>=0;y--) {
				Color c =isBlack ? Color.black:Color.white;
				if(getChess(xIndex,y,c)!=null) {
					continueCount++;
				}else
					break;
			}
			for(int y=yIndex+1;y<=ROWS;y++) {
				Color c=isBlack ? Color.black:Color.white;
				if(getChess(xIndex,y,c)!=null) {
					continueCount++;
				}else
					break;
			}
			if(continueCount>=5) {
				return true;
			}else
				continueCount=1;
			
			for(int x=xIndex-1,y=yIndex+1;y<=ROWS&&x>=0;x--,y++) {
				
				Color c=isBlack ? Color.black:Color.white;
				if(getChess(x,y,c)!=null) {
					continueCount++;
				}else
					break;
			}
			if(continueCount>=5) {
				return true;
			}else
				continueCount=1;
			
			for(int x=xIndex+1,y=yIndex+1;y<=ROWS&&x<=COLS;x++,y++) {
				Color c=isBlack ? Color.black:Color.white;
				if(getChess(x,y,c)!=null) {
					continueCount++;
				}else
					break;
			}
			if(continueCount>=5) {
				return true;
			}else
				continueCount=1;
			return false;
			
		}
		private Point getChess(int xIndex,int yIndex,Color color) {
			for(Point c:chessList) {
				if(c!=null&&c.getX()==xIndex&&c.getY()==yIndex&&c.getColor()==color)
					return c;
			}
			return null;
		}
		public void restartGame() {
			for(int i=0;i<chessList.length;i++)
				chessList[i]=null;
			isBlack=true;
			gameOver=false;
			chessCount=0;
			repaint();
		}
		public void goback() {
			if (chessCount==0)
				return;
			chessList[chessCount-1]=null;
			chessCount--;
			if(chessCount>0) {
				xIndex=chessList[chessCount-1].getX();
				yIndex=chessList[chessCount-1].getY();
			}
			isBlack=!isBlack;
			repaint();
		}
		public Dimension getPreferredSize() {
			return new Dimension(MARGIN*2+GRID_SPAN*COLS,MARGIN*2+GRID_SPAN*ROWS);
		}
		
		
		
		
	}

