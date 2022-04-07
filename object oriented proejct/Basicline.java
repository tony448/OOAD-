import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Basicline extends BasicCore{
    
    public ArrayList<BasicCore> connectcore;
    public ArrayList<Point> connectport;

    public Basicline(Point point, int depht, Newwindow newwindow,BasicCore connect1,BasicCore connect2) {
        super(point, depht, newwindow);
        this.connectcore = new ArrayList<BasicCore>();
        this.connectport = new ArrayList<Point>();
        this.connectcore.add(connect1);
        this.connectcore.add(connect2);
        for(int i=0;i<2;i++){
            connectport.add(new Point());
        }
    }
    public int calmindistance(BasicCore connect1, BasicCore connect2, ArrayList<Point>connectport){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                int mindistance = Integer.MAX_VALUE;
                int x1 = connect1.ports[i].x;
                int y1 = connect1.ports[i].y;
                int x2 = connect2.ports[j].x;
                int y2 = connect2.ports[j].y;
                if(mindistance>Math.abs((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))){
                    mindistance = Math.abs((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
                    connectport.set(0,connect1.ports[i]);
                    connectport.set(1,connect2.ports[j]);
                }
            }
        }
        System.out.println("calmin");

        return 0;
    }

    public void drawgraph(Graphics g){
        drawport(g);
        drawstring(g);
    }

    @Override
    public boolean range_check(Point start,Point end){               // check this core is in the select range
        return false;
    }

    @Override
    public void drawport(Graphics g){           // draw connection port
        calmindistance(this.connectcore.get(0), this.connectcore.get(1), connectport);
        g.setColor(Color.BLACK);
        g.drawLine(connectport.get(0).x,connectport.get(0).y,connectport.get(1).x,connectport.get(1).y);
    }
    public void drawstring(Graphics g){}
    public void drawmiddlepoint(Graphics g){}
    public void paintComponent(Graphics g){
        drawgraph(g);
        System.out.println("draw");

    }
}
