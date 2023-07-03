package a01b.e2;

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
        this.logic = new LogicImpl();
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var button = (JButton)e.getSource();
        	var position = cells.get(button);
        	logic.click(position);
        	
        	updateView();
        	
        	if(logic.isOver()) {
        		cells.forEach((b,p)-> {b.setEnabled(false);});
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
    
    void updateView() {
    	var elem = logic.getElem();
    	cells.forEach((b,p)->{
    		b.setText(elem.containsKey(p) ? (
    				elem.get(p).equals(1) ? "1" : elem.get(p).equals(2) ? "2" : elem.get(p).equals(3) ? "3" : "*") 
    				: " ");
    	});
    }
    
}
