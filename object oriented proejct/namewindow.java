import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;


public class namewindow {
    public JFrame namechange = new JFrame("change name window");
    public JTextArea namearea = new JTextArea("change name");
    public JPanel namepanel = new JPanel();
    public JButton okbutton = new JButton("OK");
    public JButton cancelbutton = new JButton("取消");
    public Newwindow newwindow;
    public Canvaspanel canvaspanel;
    public ArrayList<BasicCore> corelist;
    ActionListener oklistener;
    ActionListener cancellistener;

    public namewindow(Canvaspanel canvaspanel){
        cancellistener = new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cancel();
            }
        };
        oklistener = new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                changeName();
            }
        };
        this.canvaspanel = canvaspanel;
        okbutton.addActionListener(oklistener);
        cancelbutton.addActionListener(cancellistener);
        cancellistener = new ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent e) {
                cancel();
            }
        };
        this.canvaspanel = canvaspanel;
        this.corelist = canvaspanel.corelist;
        namearea.setSize(100, 150);
        namepanel.add(okbutton);
        namepanel.add(cancelbutton);
        namepanel.add(namearea);
        namechange.add(namepanel);
        namechange.setSize(1000,500);
        namechange.setLocation(300, 100);
        namechange.setVisible(true);
    }


    public void changeName(){
        String newname = this.namearea.getText();
        BasicCore temp = new BasicCore();
        for(int i=0;i<canvaspanel.corelist.size();i++){
            temp = canvaspanel.corelist.get(i);
            if(canvaspanel.corelist.get(i).isselect==true){
                canvaspanel.corelist.get(i).changename(newname);
                break;
            }
        }
        System.out.print("changname");
        this.canvaspanel.repaint();
        this.cancel();
    }

    public void cancel(){
        this.namechange.setVisible(false);
        this.namechange.dispose();
    }
}
