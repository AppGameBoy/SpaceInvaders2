package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Graphics;


import javax.swing.JPanel;

import model.GameElements;



public class MyCanvas extends JPanel {

    private GameBoard gameBoard;
    private ArrayList<GameElements> gameElements = new ArrayList<>();

    public MyCanvas(GameBoard gameBoard,int width, int height){
        this.gameBoard = gameBoard;
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width,height) );
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for(var e: gameElements){
            e.render(g2);
        }
    }

    public ArrayList<GameElements> getGameElements() {
        return gameElements;
    }
    
}
