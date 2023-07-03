package a03a.e2;

import java.util.HashSet;
import java.util.Set;

public class LogicImpl implements Logic {
	
	private final int size;
	private Set<Pair<Integer, Integer>> stars = new HashSet<>();
	private Pair<Integer, Integer> lastHit = null;

	public LogicImpl(int size) {
		this.size = size;
	}
	
	private boolean isInFirstQuadr(Pair<Integer, Integer> pos) {
		return pos.getX()>=0 && pos.getX()<=((size/2)-1) && pos.getY()>=0 && pos.getY()<=((size/2)-1);
	}
	
	private void addRect(Pair<Integer, Integer> pos1, Pair<Integer, Integer> pos2) {
		for(int i=pos1.getX(); i<=pos2.getX(); i++) {//aggiungo lato superiore rettangolo
			stars.add(new Pair<Integer, Integer>(i, pos1.getY()));
		}
		for(int i=pos1.getY(); i<=pos2.getY(); i++) {//aggiungo lato sinistro rettangolo
			stars.add(new Pair<Integer, Integer>(pos1.getX(),i));
		}
		for(int i=pos1.getY(); i<=pos2.getY(); i++) {//aggiungo lato destro rettangolo
			stars.add(new Pair<Integer, Integer>(pos2.getX(),i));
		}
		for(int i=pos1.getX(); i<=pos2.getX(); i++) {//aggiungo lato inferiore rettangolo
			stars.add(new Pair<Integer, Integer>(i, pos2.getY()));
		}
	}

	@Override
	public void hit(Pair<Integer, Integer> pos) {
		if(stars.isEmpty() && isInFirstQuadr(pos)) {
			lastHit = pos;
			addRect(pos, new Pair<Integer, Integer>((size-1)-pos.getX(),(size-1)-pos.getY()));
		}
		if(!stars.contains(pos) && isInFirstQuadr(pos) && (pos.getX()>lastHit.getX() && pos.getY()>lastHit.getY())) {
			lastHit = pos;
			addRect(pos, new Pair<Integer, Integer>((size-1)-pos.getX(),(size-1)-pos.getY()));
		}
	}

	@Override
	public boolean isOver() {
		return stars.contains(new Pair<Integer, Integer>((size/2)-1, (size/2)-1));
	}

	@Override
	public Set<Pair<Integer, Integer>> getStars() {
		return this.stars;
	}

}
