package czbank_simple_java.whyisthatyou.gobanggame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class StartChessJFrame extends JFrame {
private ChessBoard chessBoard;
private JPanel toolbar;
private JButton startButton;
private JButton backButton;
private JButton exitButton;
private JMenuBar menuBar;
private JMenu sysMenu;
private JMenuItem startMenuItem;
private JMenuItem exitMenuItem;
private JMenuItem backMenuItem;
public StartChessJFrame() {
	setTitle("单机版五子棋");
	chessBoard=new ChessBoard();
	menuBar=new JMenuBar();
	sysMenu=new JMenu("系统");
	startMenuItem=new JMenuItem("重新开始");
	exitMenuItem=new JMenuItem("退出");
	backMenuItem=new JMenuItem("悔棋");
	sysMenu.add(startMenuItem);
	sysMenu.add(backMenuItem);
	sysMenu.add(exitMenuItem);
	MyItemListener lis =new MyItemListener();
	this.startMenuItem.addActionListener(lis);
	backMenuItem.addActionListener(lis);
	exitMenuItem.addActionListener(lis);
	menuBar.add(sysMenu);
	setJMenuBar(menuBar);
	toolbar=new JPanel();
	startButton=new JButton("重新开始");
	backButton=new JButton("悔棋");
	exitButton=new JButton("退出");
	toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
	toolbar.add(startButton);
	toolbar.add(backButton);
	toolbar.add(exitButton);
	startButton.addActionListener(lis);
	backButton.addActionListener(lis);
	exitButton.addActionListener(lis);
	add(toolbar,BorderLayout.SOUTH);
	add(chessBoard);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(600,650);
	pack();	
}
private class MyItemListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		Object obj =e.getSource();
		if(obj==StartChessJFrame.this.startMenuItem || obj==startButton)
		{
			System.out.println("重新开始。。。");
			chessBoard.restartGame();
		}
		else if (obj==exitMenuItem || obj==exitButton) {
			System.exit(0);
		}
		else if (obj==backMenuItem || obj==backButton)
		{
			System.out.println("悔棋...");
			chessBoard.goback();
		}
		}
}
public static void main(String[] args) {
	StartChessJFrame f=new StartChessJFrame();
	f.setVisible(true);
}
}
 