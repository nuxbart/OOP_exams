package a01c.e2;

import java.util.HashSet;
import java.util.Set;


public class LogicImpl implements Logic {
	
	Set<Pair<Integer, Integer>> stars = new HashSet<>();
	private boolean first;
	private Pair<Integer, Integer> currPos;
	private boolean vertical = false;
	private boolean horizontal = false;

	@Override
	public void click(Pair<Integer, Integer> position) {
		if(!stars.contains(position)) {
			if(stars.isEmpty()) {//primissima stella
				currPos = position;
				first=true;
				stars.add(currPos);
			}else {
				direction(position);
			}
		}
	}
	
	private void direction(Pair<Integer, Integer> position) {
		if(vertical) {//se prec era verticale ora faccio in orizzontale
			
			if(currPos.getY() == position.getY()) {
				vertical= false;
				horizontal = true;
				getAdjacent(currPos, position);
				currPos = position;
			}
		}else if(horizontal) {//se prec era orizzontale ora faccio in verticale
			
			if(currPos.getX() == position.getX()) {
				vertical= true;
				horizontal = false;
				getAdjacent(currPos, position);
				currPos = position;
			}
		}else if(first){
			if(currPos.getY() == position.getY()) {
				horizontal = true;
				getAdjacent(currPos, position);
				first = false;
				currPos = position;
			}else if(currPos.getX() == position.getX()) {
				vertical= true;
				getAdjacent(currPos, position);
				first = false;
				currPos = position;
				}
			}
		}
		
	
	
	private void getAdjacent(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2 ) {
		for(int i=min(p1.getX(), p2.getX()); i<=max(p1.getX(), p2.getX()); i++) {
			for(int j=min(p1.getY(), p2.getY()); j<=max(p1.getY(), p2.getY()); j++) {
				stars.add(new Pair<Integer, Integer>(i, j));
			}
		}
	}

	private int max(int a, int b) {
		return a >= b ? a : b;
	}
	
	private int min(int a, int b) {
		return a <= b ? a : b;
	}
	
	@Override
	public boolean isOver() {
		return false;
	}

	@Override
	public Set<Pair<Integer, Integer>> getStars() {
		return this.stars;
	}

}
