import java.awt.Point;

import javax.swing.plaf.basic.BasicSplitPaneUI.BasicVerticalLayoutManager;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;
import java.awt.MouseInfo;
import javax.swing.event.MouseInputAdapter;


public class Basicclass extends BasicCore{
    
    public Basicclass(Point point, int depth,Newwindow newwindow) {
        super(point, depth,newwindow);
        this.name = "class";
        this.width = 40;
        this.ports[0].setLocation(this.point.x-width/2, this.point.y-width/2);
        this.ports[1].setLocation(this.point.x+this.width-width/2, this.point.y-width/2);
        this.ports[2].setLocation(this.point.x-width/2, this.point.y+this.height*3-width/2);
        this.ports[3].setLocation(this.point.x+this.width-width/2, this.point.y+this.height*3-width/2);
    }
    @Override    
    public BasicCore range_in(Point point){
        if(point.x>this.point.x && this.point.x+this.width>point.x){
            if(point.y>this.point.y && this.point.y+this.height*3>point.y){
                System.out.println("range true");
                return this;
            }
        }
        return null;
    }
    @Override
    public void drawgraph(Graphics g){
        g.setColor(Color.BLACK);
        for(int i=0;i<3;i++){
            g.drawRect(this.point.x, this.point.y+i*this.height, width, height);
        }
        drawstring(g);
        if(isselect==true){
            drawport(g);
        }
    }
    @Override
    public void drawstring(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(this.name, this.point.x, this.point.y+this.height*3+10);
    }
    @Override
    public void drawmiddlepoint(Graphics g){}

    @Override
    public void drawport(Graphics g){           // draw port
        int width = 10;
        this.ports[0].setLocation(this.point.x-width/2, this.point.y-width/2);
        this.ports[1].setLocation(this.point.x+this.width-width/2, this.point.y-width/2);
        this.ports[2].setLocation(this.point.x-width/2, this.point.y+this.height*3-width/2);
        this.ports[3].setLocation(this.point.x+this.width-width/2, this.point.y+this.height*3-width/2);
        g.setColor(Color.BLACK);
        for(int i=0;i<4;i++){
            g.fillRect(ports[i].x,ports[i].y, 10, 10);
        }
    }

    @Override
    public void paintComponent(Graphics g){
        drawgraph(g);
    }
}
