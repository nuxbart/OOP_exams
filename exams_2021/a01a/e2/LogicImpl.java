package a01a.e2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class LogicImpl implements Logic {
	
	private final int size;
	private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<Pair<Integer, Integer>, Integer>();
	private boolean one = true; //flag per gestire il click dell'1
	private Pair<Integer, Integer> lastOne ;
	
	public LogicImpl(int size) {
		this.size = size;
	}
	
	private Set<Pair<Integer, Integer>> getRect(Pair<Integer, Integer> cell){
		Set<Pair<Integer, Integer>> rect = new HashSet<Pair<Integer, Integer>>();
		for(int i =min(cell.getX(), lastOne.getX()); i<=max(cell.getX(), lastOne.getX()); i++ ) {
			for(int j = min(cell.getY(), lastOne.getY()); j<=max(cell.getY(), lastOne.getY()); j++) {
				var p = new Pair<>(i,j);
				if(!elem.containsKey(p)) {
					rect.add(p);
				}
				
			}
		}
		return rect;
	}
	
	private int max(int a,int b) {
		return a >= b ? a : b;
	}
	
	private int min(int a,int b) {
		return a <= b ? a : b;
	}
	

	@Override
	public void click(Pair<Integer, Integer> cell) {
		if(one && !elem.containsKey(cell)) {
			elem.put(cell, 1);
			lastOne = cell;
			one = false;
		}else {
			if(!elem.containsKey(cell)) {
				for(var e :  getRect(cell)) {
					elem.put(e, 0);
				}
				elem.replace(lastOne, 0);
				one = true;
				lastOne = null;
			}
		}
		
	}

	@Override
	public boolean isOver() {
		if(elem.size() == (size*size)){
			return true;
		}
		return false;
	}

	@Override
	public Map<Pair<Integer, Integer>, Integer> getElem() {
		return this.elem;
	}

}
