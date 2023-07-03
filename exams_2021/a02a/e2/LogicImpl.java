package a02a.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic {
	
	
	enum Direction {
		UP(-1,0), DOWN(1,0), EAST(0,-1), WEST(0,1);
		
		int x; int y;
		Direction(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		 Direction  getNext() {
			return Direction.values()[(this.ordinal()+1) % Direction.values().length];
			
		}
	}
	

	private final int size;
	private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
	private Random rand = new Random();
	private int count = 0;
	private Direction d = Direction.UP;
	private Pair<Pair<Integer, Integer>, Direction> lastPos;
	
	public LogicImpl(int size) {
		this.size = size;
		
	}

	private boolean isOk(Pair<Integer, Integer> p) {
		return (p.getX() < size) && (p.getX() >= 0) && (p.getY() < size) && (p.getY() >= 0) && (!this.elem.containsKey(p));
	}
	
	@Override
	public boolean hit() {
		if(this.elem.isEmpty()) {
			lastPos = new Pair<Pair<Integer,Integer>, Direction>(new Pair<Integer, Integer>(rand.nextInt(size), rand.nextInt(size)), Direction.UP) ;
			elem.put(lastPos.getX(), count);
			return true;
		}
				
		for(int i=0; i<4;i++) {
			var newP = new Pair<>(lastPos.getX().getX()+d.x, lastPos.getX().getY() + d.y);
			if(isOk(newP)) {
				count++;
				elem.put(newP, count);
				lastPos = new Pair<Pair<Integer,Integer>, Direction>(newP,d);
				return true;
			}
			d = d.getNext();
		}
		return false;
	}
		

	@Override
	public boolean isOver() {
		return !hit();
	}

	@Override
	public Map<Pair<Integer, Integer>, Integer> getElem() {
		return this.elem;
	}
	

}
