import java.awt.Point;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.event.MouseInputAdapter;

import window.select_button;

public class Basicuseclass extends BasicCore {

    public Basicuseclass(Point point, int depth,Newwindow newwindow) {
        super(point, depth,newwindow);
        this.width = 50;
        this.height = 40;
        this.name = "use_class";
    }
    
    @Override
    public void drawgraph(Graphics g){
        g.setColor(Color.BLACK);
        g.drawOval(this.point.x, this.point.y, width, height);
        drawstring(g);
        if(isselect==true){
            drawport(g);
        }
    }
    @Override
    public void drawport(Graphics g){
        int width = 10;
        this.ports[0].setLocation(this.point.x+this.width/2-width/2, this.point.y-width/2);   //top
        this.ports[1].setLocation(this.point.x+this.width-width/2, this.point.y-width/2+this.height/2);  //right
        this.ports[2].setLocation(this.point.x+this.width/2-width/2, this.point.y+this.height-width/2);      //down
        this.ports[3].setLocation(this.point.x-width/2, this.point.y+this.height/2-width/2);     //left
        g.setColor(Color.BLACK);
        for(int i=0;i<4;i++){
            g.fillRect(ports[i].x,ports[i].y, 10, 10);
        }
    }
    @Override
    public void drawstring(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(this.name, this.point.x+5, this.point.y+this.height+10);
    }

    @Override
    public void drawmiddlepoint(Graphics g){
        drawgraph(g);
    }
}
