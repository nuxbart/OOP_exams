package a02c.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {
	
	private final int size;
	private List<Pair<Integer, Integer>> ob = new ArrayList<>();
	private Pair<Integer, Integer> star;
	private Random random = new Random();
	private boolean over = false;

	public LogicImpl(int size) {
		this.size = size;
		star = new Pair<Integer, Integer>(random.nextInt(size), 0);
		while(ob.size()<=20) {
			var curr = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size)+1);
			if(!ob.contains(curr)) {
				ob.add(curr);
			}
		}
	}
	
	private Pair<Integer, Integer> getPos(Pair<Integer, Integer> curr, int delta){
		
		return new Pair<Integer, Integer>(curr.getX()+delta, curr.getY());
	}
	
	@Override
	public void hit() {
		var newCell = new Pair<>(this.star.getX(), this.star.getY()+1);
		if(ob.contains(newCell)) {
			Pair<Integer, Integer> left =getPos(newCell,-1);
			Pair<Integer, Integer> right = getPos(newCell,1);
			if(ob.contains(left) && !ob.contains(right)){
				this.star=right;
			}else if(!ob.contains(left) && ob.contains(right)) {
				this.star=left;
			}else if(ob.contains(left) && ob.contains(right) ) {
				over = true;
			}
			if(left.getX()>=0) {
				this.star=left;
			}else if(right.getX()<size) {
				this.star=right;
			}
			
		}else {
			this.star=newCell;
		}
		if(this.star.getY()>=size) {
			var p = new Pair<Integer, Integer>(star.getX(),0);
			this.star = p;
		}
		
	}

	@Override
	public boolean isOver() {
		return over;
	}

	@Override
	public List<Pair<Integer, Integer>> getElem() {
		return this.ob;
	}

	@Override
	public Pair<Integer, Integer> getStarPos() {
		return this.star;
	}

}
