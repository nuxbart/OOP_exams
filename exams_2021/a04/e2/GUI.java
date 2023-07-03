package a04.e2;

import javax.swing.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.ConsoleHandler;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.invoke.VarHandle;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton, Pair<Integer,Integer>> cells = new HashMap<>();
    private final JButton quit = new JButton("QUIT");
    private final Logic logic;
        
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        this.logic = new LogicImpl(size);
        
        JPanel grid = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,grid);
        this.getContentPane().add(BorderLayout.SOUTH,quit);
        
        quit.addActionListener(e -> {
        	var result = this.logic.hitQuit();
        	System.out.println(result);
        	System.exit(0);
       	});
        
        ActionListener al = e -> {
        	var jb = (JButton)e.getSource();
        	var pos = cells.get(jb);
        	this.logic.clikCell(pos);
        	
        	
        	updateView();
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton("  ");
                this.cells.put(jb, new Pair<>(i,j));
                grid.add(jb);
                jb.addActionListener(al);
            }
        }
        this.updateView();
        this.setVisible(true);
    }
    
    private void updateView() {
    	var num = this.logic.getNum();
    	var op = this.logic.getOp();
    	var hitten = this.logic.getHitten();
    	cells.forEach((b,p)->{
    		b.setText(num.containsKey(p) ? num.get(p).toString() : op.containsKey(p) ? op.get(p).toString() : " ");
    		b.setEnabled(hitten.containsKey(p) ? false : true);
    	});
    }
}