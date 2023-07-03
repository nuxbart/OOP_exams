package a06.e2;

import javax.swing.*;


import java.util.HashMap;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("ADVANCE");
    private final Logic logic;
        
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
        	cells.forEach((b,p)->{
        		b.setEnabled(false);
        	});
        	this.logic.hitQuit();
        	updateView();
        	if(this.logic.isOver()) {
            	System.exit(0);
        	}
        	
        	
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var position = cells.get(jb);
        	this.logic.click(position);
        	updateView();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("  ");
                this.cells.put(jb, new Pair<Integer, Integer>(i, j));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.updateView();
        this.setVisible(true);
    }
    
    private void updateView() {
    	var elem = this.logic.getElem();
    	var stars = this.logic.getStars();
    	cells.forEach((b,p)->{
    		b.setText(elem.contains(p) ? "O" : stars.contains(p) ? "*" : " ");});
    }
}