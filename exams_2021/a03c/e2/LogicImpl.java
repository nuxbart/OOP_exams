package a03c.e2;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public class LogicImpl implements Logic {

	enum Direction {
		UP(0,-1), DOWN(0,1);
		
		int x;
		int y;
		private Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		private Direction next(Direction curr) {
			return curr==UP? DOWN : UP;
		}
	}
	
	private final int size;
	private Set<Pair<Integer, Integer>> obstacles = new HashSet<>();
	private Pair<Integer, Integer> star;
	private Random random = new Random();
	private Direction curr = Direction.UP; 
	
	public LogicImpl(int size) {
		this.size = size;
		star = new Pair<Integer, Integer>(random.nextInt(size),size-1);
		for(int i =0; i<=size; i++) {
			obstacles.add(new Pair<Integer, Integer>(i,random.nextInt(size-2)));
		}
	}

	private Direction changeDir() {
		var t = curr;
		return t.next(t);
	}
	
	@Override
	public void hit() {
		var p = new Pair<>(star.getX()+curr.x, star.getY()+curr.y);
		if(obstacles.contains(p)) {
			obstacles.remove(p);
			
			curr = changeDir();
		}else if(p.getY()==size) {
			curr = changeDir();
			p = new Pair<>(star.getX()+curr.x, star.getY()+curr.y);
			
		}
		star=p;
		
		
	}

	@Override
	public boolean isOver() {
		return star.getY()<0;
	}

	@Override
	public Set<Pair<Integer, Integer>> getObs() {
		return this.obstacles;
	}

	@Override
	public Pair<Integer, Integer> getStarPos() {
		return this.star;
	}

}
