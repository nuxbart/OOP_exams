package a06.e2;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class LogicImpl implements Logic {
	
	private final int size;
	private Set<Pair<Integer, Integer>> elem = new HashSet<>();
	private Set<Pair<Integer, Integer>> stars = new HashSet<>();
	private Deque<Pair<Integer, Integer>> listElem = new LinkedList<>();
	private Pair<Integer, Integer> currElem = new Pair<Integer, Integer>(-1, -1);
	private boolean up = true;
	private int quit = 0;

	public LogicImpl(int size) {
		this.size = size;
	}

	@Override
	public void hitQuit() {
		if(stars.size()==(elem.size()*2)) {
			quit++;
		}else {
			if(!up) {
				stars.add(new Pair<Integer, Integer>(currElem.getX()+1, currElem.getY()));
				up=true;
			}else if(up) {
				currElem = listElem.pollFirst();
				stars.add(new Pair<Integer, Integer>(currElem.getX()-1, currElem.getY()));
				up=false;
			}
		}
		
	}

	@Override
	public boolean isOver() {
		return quit==1;
	}
	
	private boolean isOk(Pair<Integer, Integer> p) {
		return p.getX()>0 && p.getY()>0 && p.getX()<size-1 &&  p.getY()<size-1;
	}

	@Override
	public void click(Pair<Integer, Integer> position) {
			if(!this.elem.contains(position) && isOk(position)) {
				elem.add(position);
				listElem.add(position);
			}
	}

	@Override
	public Set<Pair<Integer, Integer>> getElem() {
		return this.elem;
	}

	@Override
	public Set<Pair<Integer, Integer>> getStars() {
		return this.stars;
	}

}
