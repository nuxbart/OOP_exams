package a02b.e2;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    
    private final Map<JButton,Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl(size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        JButton checkButton = new JButton("Check > Restart");
        main.add(BorderLayout.CENTER, panel);
        main.add(BorderLayout.SOUTH, checkButton);
        
        checkButton.addActionListener(e->{
        	logic.checkButton();
        	this.updateView();
        	
        });
        
        ActionListener al = new ActionListener(){
            public void actionPerformed(ActionEvent e){
        	    var button = (JButton)e.getSource();
        	    var position = cells.get(button);
                logic.hit(position);
                updateView();
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.put(jb, new Pair<>(i, j));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.updateView();
        this.setVisible(true);
    }    
    
    private void updateView() {
    	var stars = this.logic.getStars();
    	var enabled = this.logic.getEnabled();
    	cells.forEach((b,p)->{
    		b.setText(stars.contains(p) ? "*" : "");
    		b.setEnabled(enabled.contains(p) ? false :true);
    	});
    }
}
