import java.awt.Color;
import java.awt.Point;

import javax.swing.JButton;

public class Button extends JButton{
    Button(String name){
        super(name);
        this.select = false;
        this.depth = 0;
        this.name = name;
        this.setBackground(Color.WHITE);
    }

    Boolean isselect(){
        return this.select;
    }
    
    int getdepth(){
        return this.depth;
    }

    String getname(){
        return this.name;
    }

    void changemode(){
        this.setBackground(Color.WHITE);
        this.select = false;
    }
    BasicCore create(Point point,int depth,Newwindow newwindow){
        BasicCore temp = new BasicCore(point, depth, newwindow);
        return temp;
    }
    Boolean select;
    int depth;
    String name;
    
}

