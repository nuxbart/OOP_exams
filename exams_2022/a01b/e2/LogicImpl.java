package a01b.e2;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LogicImpl implements Logic {
	
	private final int size;
	private Set<Pair<Integer, Integer>> stars = new HashSet<>();
	private int addStar=0;
	private int removeStar=0;
	

	public LogicImpl(int size) {
		this.size = size;
	}
	
	private List<Pair<Integer, Integer>> getAdj(Pair<Integer, Integer> p){
		List<Pair<Integer, Integer>> adj = new LinkedList<>();
		adj.add(new Pair<Integer, Integer>(p.getX()-1, p.getY()-1));
		adj.add(new Pair<Integer, Integer>(p.getX()+1, p.getY()+1));
		adj.add(new Pair<Integer, Integer>(p.getX()+1, p.getY()-1));
		adj.add(new Pair<Integer, Integer>(p.getX()-1, p.getY()+1));
		return adj;
	}

	@Override
	public void hit(Pair<Integer, Integer> position) {
		addStar=0;
		removeStar=0;
		for(var e: getAdj(position)) {
			if (!this.stars.contains(e)) {
				this.stars.add(e);
				addStar++;
			}else {
				this.stars.remove(e);
				removeStar++;
			}
		}
		
	}

	@Override
	public boolean isOver() {
		return addStar==1 && removeStar==3;
	}

	@Override
	public Set<Pair<Integer, Integer>> getStars() {
		return this.stars;
	}

}
