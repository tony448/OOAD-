import java.util.ArrayList;
import javax.swing.event.MouseInputAdapter;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Graphics;



public class Composite {
    public Newwindow newwindow;
    public Canvaspanel canvaspanel;
    public ArrayList<BasicCore> corelist;
    public ArrayList<BasicCore> selectlist;
    public boolean isselect = false;
    public Point start_point;
    public Point end_point;
    public Point []ports = new Point[4];

    public Composite(Newwindow newwindow){
        this.newwindow = newwindow;
        this.corelist = newwindow.corelist;
        this.canvaspanel = newwindow.canvaspanel;
        this.selectlist = new ArrayList<BasicCore>();
        for(int i=0;i<4;i++){
            ports[i] = new Point();
        }
        init();
    }

    public Composite(){}
    
    public MouseInputAdapter mouse = new MouseInputAdapter(){
        
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

        };

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);

        };

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            super.mousePressed(e);
            start_point = e.getPoint();
        };

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            end_point = e.getPoint();
            setLocation(start_point, end_point);
        };
    };

    public void setLocation(Point start , Point end){
        int movex = end.x - start.x;
        int movey = end.y - start.y;
        for(int i=0;i<this.selectlist.size();i++){
            BasicCore temp = this.selectlist.get(i);
            Point newlocation = new Point(temp.point.x+movex,temp.point.y+movey);
            temp.setLocation(newlocation);
        }
        this.newwindow.canvaspanel.repaint();
    }

    public void init(){
        for(int i=0;i<this.corelist.size();i++){
            BasicCore temp = this.corelist.get(i);
            if(temp.isselect==true){
                this.selectlist.add(temp);
            }
        }
        this.newwindow.canvaspanel.repaint();
    }

    public void drawborder(Graphics g){
        int lx = 10000000,rx = 0,down = 0,top = 1000000;
        for(int i=0;i<this.selectlist.size();i++){
            BasicCore temp = this.selectlist.get(i);
            for(int j=0;j<4;j++){
                lx = Math.min(temp.ports[j].x-30,lx);
                rx = Math.max(temp.ports[j].x+30,rx);
                down = Math.max(temp.ports[j].y+30,down);
                top = Math.min(temp.ports[j].y-30,top);
            }
        }
        this.ports[0].setLocation(lx, top);
        this.ports[1].setLocation(rx, top);
        this.ports[2].setLocation(lx, down);
        this.ports[3].setLocation(rx, down);
        for(int i=0;i<4;i++){
            g.fillRect(ports[i].x, ports[i].y, 10, 10);
        }
        g.drawRect(lx, top, rx-lx, down-top);
        this.newwindow.canvaspanel.repaint();
    }

    
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        if(isselect==true){
            for(int i=0;i<this.selectlist.size();i++){
                BasicCore temp = this.selectlist.get(i);
                temp.drawport(g);
            }
        }
        drawborder(g);
        g.drawRect(100, 100, 10000, 10000);
    }
}
