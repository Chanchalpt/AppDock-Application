/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author CHanchal pt
 */
public class Main extends JFrame{
    
    //Double Buffering
    Image dbImage;
    Graphics dbg;
    
    //Ball objects
    static Ball b = new Ball(193, 143);

    //Variable for screen size
    int 
    GWIDTH = 400,
    GHEIGHT = 300;
    
    //Dimension of GWIDTH*GHEIGHT
    Dimension screenSize = new Dimension(GWIDTH,GHEIGHT);
    
    //Create constructor for spawn window
    public Main(){
        this.setTitle("Pong Game");
        this.setSize(screenSize);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBackground(Color.DARK_GRAY);
        this.addKeyListener(new AL());
        this.setBounds(475, 200, 400, 300);
    }
   
    public static void main(String[] args) {
        Main m = new Main();
        //Create and Start Threads
        Thread ball = new Thread(b);
        ball.start();
        Thread p1 = new Thread(b.p1);
        Thread p2 = new Thread(b.p2);
        p1.start();
        p2.start();
    }
    
    @Override
    public void paint(Graphics g){
        dbImage = createImage(getWidth(),getHeight());
        dbg = dbImage.getGraphics();
        draw(dbg);
        g.drawImage(dbImage, 0, 0, this);
    }
    
    public void draw(Graphics g){
        b.draw(g);
        b.p1.draw(g);
        b.p2.draw(g);
        
        
        g.setColor(Color.WHITE);
        g.drawString(""+b.p1Score, 15, 50);
        g.drawString(""+b.p2Score, 370, 50);
        repaint();
    }
    
    
    ////EVENT LISTENER CLASS/////
    public class AL extends  KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            b.p1.keyPressed(e);
            b.p2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            b.p1.keyReleased(e);
            b.p2.keyReleased(e);
        }
    }
    
}
