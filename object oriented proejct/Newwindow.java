import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class Newwindow {
    public JFrame frame= new JFrame("UML editor");
    private JPanel buttonpanel = new JPanel();
    public Canvaspanel canvaspanel;
    private Button selectbutton = new Button("select");
    private Button associationline = new Button("association line");
    private Button generationline = new Button("generation line");
    private Button compositionline = new Button("composition line");
    private Button classbutton = new Button("class");
    private Button usecase = new Button("use_case");

    private JMenuBar mainmenubar = new JMenuBar();
    private JMenu edit = new JMenu("edit");
    private JMenu file = new JMenu("file");
    private JMenuItem group = new JMenuItem("group");
    private JMenuItem ungroup = new JMenuItem("ungroup");
    private JMenuItem changename = new JMenuItem("changename");
    private int depth = 0;
    public int mode;
    ArrayList<Composite> comlist = new ArrayList<Composite>();
    ArrayList<Button> buttonlist = new ArrayList<Button>();      //controll all the button in the buttonlist
    ArrayList<BasicCore> corelist = new ArrayList<BasicCore>();
    ArrayList<BasicCore> selectlist = new ArrayList<BasicCore>();

    ActionListener namelistener = new ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent e){
            namewindow changename = new namewindow(canvaspanel); 
        }
    };


    ActionListener grouplistener = new ActionListener(){
        public void actionPerformed(java.awt.event.ActionEvent e){
            setgroup();
        }
    };


    private void initailNwewindow(){
        this.edit.add(group);
        this.edit.add(ungroup);
        this.edit.add(changename);

        this.mainmenubar.add(file);
        this.mainmenubar.add(edit);

        this.buttonlist.add(selectbutton);
        this.buttonlist.add(associationline);
        this.buttonlist.add(generationline);
        this.buttonlist.add(compositionline);
        this.buttonlist.add(classbutton);
        this.buttonlist.add(usecase);

        for(int i=0;i<6;i++){
            buttonpanel.add(buttonlist.get(i));
        }
        changename.addActionListener(namelistener);
        group.addActionListener(grouplistener);
        canvaspanel = new Canvaspanel(this, depth, buttonlist, corelist);
        this.buttonpanel.setSize(100,100);
        this.canvaspanel.setBackground(Color.LIGHT_GRAY);
        buttonpanel.setLayout(new BoxLayout(this.buttonpanel, BoxLayout.Y_AXIS));
        this.frame.getContentPane().add(mainmenubar,BorderLayout.PAGE_START);
        this.frame.getContentPane().add(buttonpanel,BorderLayout.WEST);
        this.frame.getContentPane().add(canvaspanel,BorderLayout.CENTER);
        this.frame.setSize(1000, 1000);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public Newwindow(){
        initailNwewindow();
        addtolistener();
    }
    
    public void addtolistener(){                //add button to listener
        for(int i=0;i<buttonlist.size();i++){
            buttonlist.get(i).addActionListener(new ButtonListener());
        }
    }

    public Composite setgroup(){
        Composite temp = new Composite(this);
        this.canvaspanel.comlist.add(temp);
        this.canvaspanel.repaint();
        return temp;
    }

    public void change_mode(ArrayList<Button> buttonlist,int modenum){
            this.mode = modenum;
            System.out.println((this.mode));
            return;
    }
    

    class ButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            Object temp = e.getSource();
            for(int i=0;i<6;i++){
                Object test = buttonlist.get(i);
                if(temp.equals(test)){
                    System.out.println(e.getActionCommand());
                    buttonlist.get(i).setBackground(Color.LIGHT_GRAY);
                    buttonlist.get(i).select = true;
                    change_mode(buttonlist,i);
                    for(int j=0;j<6;j++){
                        if(j!=i){
                            buttonlist.get(j).setBackground(Color.WHITE);
                        }
                    }
                    return ;
                }
            }
        }
    }


}
