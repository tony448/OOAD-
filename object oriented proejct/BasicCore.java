
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.MouseInputListener;
import java.awt.MouseInfo;
import javax.swing.event.MouseInputAdapter;
import javax.swing.JPanel;


public class BasicCore{
    public Point point;             //record the left top coordinaiton
    public String name;
    public int depth;
    public boolean isselect;
    public int width = 20;          
    public int height = 40;
    public boolean dragging = false;
    public Newwindow newwindow;
    public Point []ports = new Point[4];

    public BasicCore(){
        this.name = "";
        this.depth = 0;
    };
    
    public BasicCore(String name,Point point,int depth,Newwindow newwindow){
        this.name = name;
        this.point = point;
        this.depth = depth;
        this.newwindow = newwindow;
        this.isselect = false;
        this.newwindow.corelist.add(this);
        for(int i=0;i<4;i++){
            this.ports[i] = new Point();
        }
    };
    
    public BasicCore(Point point,int depth,Newwindow newwindow){
        this.name = "class";
        this.newwindow = newwindow;
        this.point = point;
        this.depth = depth;
        this.isselect = false;
        this.newwindow.corelist.add(this);
        int width = 10;
        for(int i=0;i<4;i++){
            this.ports[i] = new Point();
        }
    };

    public void movecore(Point p){                      // move the basicCore to another point
        this.point = p;
        this.setLocation(this.point);
        this.newwindow.canvaspanel.repaint();            //repaint the canvaspanel
    }   
    
    public void delete_selectmode(ArrayList<BasicCore> corelist,boolean check){         // delete the select mode of anyother core
        for(int i=0;i<corelist.size();i++){
            corelist.get(i).isselect = false;
        }
        System.out.println("delete mode");
        this.newwindow.canvaspanel.repaint();            //repaint the canvaspanel
    }

    public void setLocation(Point p){
        if(this.isselect==true)
            this.point = p;
    }

    public void changename(String name){
        this.name = name;
    }
    
    public boolean range_check(Point start,Point end){               // check this core is in the select range
        // System.out.println("range_check");
        // System.out.println(this.point);
        // System.out.println(start);
        // System.out.println(end);
        
        Point []corner = new Point[4];
        for(int i=0;i<4;i++){
            corner[i] = new Point();
        }
        corner[0].setLocation(this.point.x, this.point.y);                //left-top
        corner[1].setLocation(this.point.x, this.point.y+this.height);     //left-down  
        corner[2].setLocation(this.point.x+this.width, this.point.y);     //right-top
        corner[3].setLocation(this.point.x+this.width, this.height+this.point.y);     //right-down
        for(int i=0;i<4;i++){
            if(corner[i].x>=start.x && corner[i].x<=end.x+this.width){
                if(corner[i].y>=start.y && corner[i].y<=end.y+this.height){
                    continue;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        this.isselect = true;
        newwindow.canvaspanel.repaint();
        return true;
    }

    public BasicCore range_in(Point point){
        if(point.x>this.point.x && this.point.x+this.width>point.x){
            if(point.y>this.point.y && this.point.y+this.height>point.y){
                this.isselect = true;
                newwindow.canvaspanel.repaint();
                return this;
            }
        }
        return null;
    }

    public void drawgraph(Graphics g){}
    public void drawport(Graphics g){               //draw 4 port outside the graph
        g.setColor(Color.BLACK);
        for(int i=0;i<4;i++){
            g.fillRect(ports[i].x,ports[i].y, 10, 10);
        }
    }
    public void drawstring(Graphics g){}
    public void drawmiddlepoint(Graphics g){}
    public void paintComponent(Graphics g){}
}
