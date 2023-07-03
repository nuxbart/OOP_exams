package a04.e2;

import javax.swing.*;

import a04.e2.MyLogic.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("QUIT");
    private final MyLogic logic;
        
    public MyGUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new MyLogicImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
        	System.out.println(logic.getResult());
        	System.exit(0);
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var pos = cells.get(jb);
        	if(logic.hit(pos)) {
        		jb.setEnabled(false);
        	}
        	updateVIew();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("  ");
                this.cells.put(jb,new Pair<Integer, Integer>(i, j));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.updateVIew();
        this.setVisible(true);
    }
    
    private void updateVIew() {
    	var elem = this.logic.getElem();
    	cells.forEach((b,p)->{
    		b.setText(elem.get(p).getClass().equals(Integer.class) ? elem.get(p).toString() :  elem.get(p).toString());
    	});
    }
}
