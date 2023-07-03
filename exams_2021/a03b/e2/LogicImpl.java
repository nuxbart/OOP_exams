package a03b.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic {
	
	private final int size;
	private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
	private Random random = new Random();
	private Direction currDir = Direction.UP;
	private Pair<Integer, Integer> lastPos = null;
	private int counter = 0;
	private int dir ;
	
	public LogicImpl(int size) {
		this.size = size;
		lastPos = new Pair<Integer, Integer>(random.nextInt((size-2)-2)+2, random.nextInt((size-2)-2)+2);
		elem.put(lastPos, counter);
	}
	
	enum Direction{
		UP(0,-1), RIGHT(1,0), DOWN(0,1), LEFT(-1,0);
		
		int x;
		int y;
		
		private Direction(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
		private Direction next(Direction dir) {
			return  dir==Direction.UP ? Direction.RIGHT : 
					dir==Direction.RIGHT ? Direction.DOWN : 
					dir==Direction.DOWN ? Direction.LEFT : 
					dir==Direction.LEFT ? Direction.UP : null;
		}
	}

	@Override
	public void hit() {
		var newPos = new Pair<>(lastPos.getX()+currDir.x, lastPos.getY()+currDir.y);
		dir=1;
		if(isOk(newPos)) {
			counter++;
			elem.put(newPos, counter);
			lastPos=newPos;
			
		}else {
			for(int i =0; i<3; i++) {
				dir++;
				Direction newDirection = currDir.next(currDir);
				newPos = new Pair<>(lastPos.getX()+newDirection.x, lastPos.getY()+newDirection.y);
				if(isOk(newPos)) {
					counter++;
					elem.put(newPos, counter);
					lastPos=newPos;
					i=3;
					currDir = newDirection;
					
				}
				
		}
		
	}}

	private boolean isOk(Pair<Integer, Integer> newPos) {
		return newPos.getX()>=0 && newPos.getX()<size && newPos.getY()>=0 && newPos.getY()<size && !elem.containsKey(newPos);
	}

	@Override
	public boolean isOver() {
		return dir==4;
	}

	@Override
	public Map<Pair<Integer, Integer>, Integer> getElem() {
		return this.elem;
	}

}
