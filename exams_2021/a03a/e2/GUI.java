package a03a.e2;

import javax.swing.*;


import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer, Integer>> cells = new HashMap<>();
    private final Logic logic;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        this.logic = new LogicImpl(size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	var pos = cells.get(button);
        	
        	this.logic.hit(pos);
        	updateView();
        	if(this.logic.isOver()) {
        		System.exit(0);
        	}
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.updateView();
        this.setVisible(true);
    }
    void updateView() {
    	var stars = this.logic.getStars();
    	cells.forEach((b,p)->{
    		b.setText(stars.contains(p) ? "*" : " ");
    	});
    }
}
