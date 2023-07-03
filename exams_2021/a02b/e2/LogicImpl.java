package a02b.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class LogicImpl implements Logic {

	private final int size;
	private Map<Pair<Integer, Integer>, String> elem = new HashMap<>();
	private Random random = new Random();
	private Pair<Integer, Integer> star;
	private boolean left = false;
	private boolean right = false;
	private static String STAR = "*";
	private int count = 0;
	
	public LogicImpl(int size) {
		this.size = size;
		this.star = new Pair<Integer, Integer>(random.nextInt(size), size-1);
		elem.put(star, STAR);
		while (elem.size()<22) {
			var p = new Pair<>(random.nextInt(size), random.nextInt(size));
			if(!elem.containsKey(p)) {
				elem.put(p, (random.nextInt(2)) == 1 ? "L" : "R");
			}
		}
	}
	
	private Pair<Integer, Integer> calculatePair(Pair<Integer, Integer> dir){
		return new Pair<Integer, Integer>(star.getX() + dir.getX(), star.getY() + dir.getY());
	}

	private Pair<Integer, Integer> changeDir(Pair<Integer, Integer> curr) {
		if(elem.get(curr).equals("R")) {
			right = true;
			left = false;
			return calculatePair(new Pair<>(1, 0));
		}else{
			left = true;
			right = false;
			return calculatePair(new Pair<>(-1, 0));
		}
	}
	private boolean putElem (Pair<Integer, Integer> p) {
		if (!elem.containsKey(p)) {
			elem.remove(star);
			star = p;
			elem.put(star, STAR);
			return true;
		}
		return false;
	}
	
	@Override
	public void hit() {
		if(!left && !right) {
			var curr = calculatePair(new Pair<>(0, -1));
			Pair<Integer, Integer> n = null;
			if (!putElem(curr)) {
				n  = changeDir(curr);
				count++;
				if (!putElem(n)) {
					n  = changeDir(curr);
					if (!putElem(n)) {
						count++;
					}
					
				}
				if(count<2) {
					putElem(n);
				}
			}else {
				putElem(curr);
			}
		}
		if(left) {
			var curr = calculatePair(new Pair<>(-1, 0));
			Pair<Integer, Integer> n = null;
			if (!putElem(curr)) {
				n  = changeDir(curr);
				count++;
				if (!putElem(n)) {
					n  = changeDir(curr);
					if (!putElem(n)) {
						count++;
					}
					
				}
				if(count<2) {
					putElem(n);
				}
			}else {
				putElem(curr);
			}
		}
		
		if(right) {
			var curr = calculatePair(new Pair<>(1, 0));
			Pair<Integer, Integer> n = null;
			if (!putElem(curr)) {
				n  = changeDir(curr);
				count++;
				if (!putElem(n)) {
					n  = changeDir(curr);
					if (!putElem(n)) {
						count++;
					}
					
				}
				if(count<2) {
					putElem(n);
				}
			}else {
				putElem(curr);
			}
		}
		
	}

	@Override
	public boolean isOver() {
		return !(star.getX() < size && star.getY() < size && star.getX()>=0 && star.getY()>=0);
	}

	@Override
	public Map<Pair<Integer, Integer>, String> getElem() {
		return this.elem;
	}

}
