import java.util.ArrayList;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;


import java.awt.event.MouseEvent;

import java.awt.Graphics;
import java.awt.Point;
public class Canvaspanel extends JPanel{
    
    public Newwindow newwindow;
    public int depth;
    public ArrayList<Button> buttonlist;
    public ArrayList<BasicCore> corelist;
    public ArrayList<BasicCore> selectlist;
    public int mode;
    public Point start_point;
    public Point end_point;
    public ArrayList<Composite> comlist = new ArrayList<Composite>();
    
    public MouseInputAdapter mouse = new MouseInputAdapter(){
        public BasicCore t = new BasicCore();       // for local use
        public BasicCore b = new BasicCore();
        public Composite a = new Composite();

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            super.mouseClicked(e);
            System.out.println("canvas clicked");
            change_mode(newwindow);
            Point point = e.getPoint();
            BasicCore temp = new BasicCore();
            switch(mode){
                case 4:
                    temp = new Basicclass(point, depth, newwindow);
                    corelist.add(temp);
                    System.out.println("mode: " + mode);
                    repaintall();
                    break;
                case 5:
                    temp = new Basicuseclass(point, depth, newwindow);
                    corelist.add(temp);
                    System.out.println("mode: " + mode);
                    repaintall();
                    break;
                case 0:
                    System.out.println("mode: " + mode);
                    temp = find_object(point);
                    if(temp!=null){
                        System.out.println("inside");
                        temp.delete_selectmode(corelist,true);
                        temp.isselect = true;
                    }
                    break;
                        
            }
        };

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            end_point = e.getPoint();
            System.out.print("dragg");
            switch(mode){
                case 0:
                    t.setLocation(end_point);
            }
            
            repaintall();
        };

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            super.mousePressed(e);
            System.out.println("canvas pressed");
            switch(mode){
                case 1,2:
                    t = find_object(e.getPoint());
                case 0:
                    t = find_object(e.getPoint());
                    if(t==null){
                        corelist.get(0).delete_selectmode(corelist,false);
                    }
            }
            start_point = e.getPoint();
            if(newwindow.mode==0){
                t = find_object(start_point);
            }
        };

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            end_point = e.getPoint();
            switch(newwindow.mode){
                case 1,2:
                    b = find_object(end_point);
                    if(b!=null && t!=null){
                        BasicCore line = new Basicline(end_point, depth, newwindow, t, b);
                    }
                    System.out.println("line");
            }
            range_select(start_point,end_point,selectlist,corelist);
            System.out.println("canvas realese");
        };
    };

    public Canvaspanel(Newwindow newwindow,int depth,ArrayList<Button>buttonlist,ArrayList<BasicCore>corelist){
        this.newwindow = newwindow;
        this.depth = depth;
        this.buttonlist = buttonlist;
        this.corelist = corelist;
        this.comlist = newwindow.comlist;
        this.mode = newwindow.mode;
        this.selectlist = new ArrayList<BasicCore>();
        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
    }

    public void change_mode(Newwindow newwindow){
        this.mode = newwindow.mode;
    }

    public BasicCore find_object(Point point){
        BasicCore t = new BasicCore();
        for(int i=0;i<corelist.size();i++){
            t = corelist.get(i).range_in(point);            // click point in the range in the BasicCore
            if(t!=null){
                return t;
            }
                 
        }
        return null;
    }

    public void range_select(Point start,Point end,ArrayList<BasicCore> selectlist,ArrayList<BasicCore> corelist){
        for(int i=0;i<corelist.size();i++){
            if(corelist.get(i).range_check(start, end)==true){
                selectlist.add(corelist.get(i));
            } 
        }
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for(int i=0;i<this.corelist.size();i++){
            this.corelist.get(i).drawgraph(g);
        }
        for(int i=0;i<this.comlist.size();i++){
            this.comlist.get(i).drawborder(g);
        }
        // g.drawOval(100, 100, 100, 100);
        this.repaint();
    }
    
    public void repaintall(){
        this.repaint();
    }

}
