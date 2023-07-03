package a05.e2;

import javax.swing.*;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer, Integer>> cells = new HashMap<>();
    private final MyLogic logic;
    
    public MyGUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new MyLogicImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var position = cells.get(jb);
        	this.logic.click(position);
        	if(logic.isOver()) {
        		System.exit(0);
        	}
        	jb.setEnabled(false);
        	this.updateView();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(""+(i+j));
                this.cells.put(jb, new Pair<>(i, j));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.updateView();
        this.setVisible(true);
    }
    
    private void updateView() {
    	var elem = this.logic.getElem();
    	cells.forEach((b,p)->{
    		b.setText(elem.containsKey(p) ? elem.get(p).toString() : "");
    	});
    }
}