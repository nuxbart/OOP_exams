package a03b.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {
	
	private final int size;
	private Set<Pair<Integer, Integer>> stars = new HashSet<>();
	private Set<Pair<Integer, Integer>> obstacles = new HashSet<>();
	private Random random = new Random();

	public LogicImpl(int size) {
		this.size = size;
		while (stars.size()<4) {
			var elem = new Pair<>(size-1,random.nextInt(size));
			if(!stars.contains(elem)) {
				stars.add(elem);
			}
		}
		while (obstacles.size()<4) {
			var elem = new Pair<>(random.nextInt(2),random.nextInt(size));
			if(!obstacles.contains(elem)) {
				obstacles.add(elem);
			}
		}
	}

	private List<Pair<Integer, Integer>> eat(Pair<Integer, Integer> p){
		//controllo se posso mangiare e metto eventualmente i due ostacoli nella lista 
		List<Pair<Integer, Integer>> list = new ArrayList<>();
		var dxElem = new Pair<>(p.getX()-1, p.getY()+1);
		if(obstacles.contains(dxElem)) {
			list.add(dxElem);
		}
		var sxElem = new Pair<>(p.getX()-1, p.getY()-1);
		if(obstacles.contains(sxElem)) {
			list.add(sxElem);
		}
		
		return list;
		
	}
	
	 
	
	@Override
	public void hit(Pair<Integer, Integer> position) {
		if(this.stars.contains(position)) {
			if(position.getX()>0) {
				if(!eat(position).isEmpty()) {//se posso mangiare
					stars.remove(position);
					position=eat(position).get(random.nextInt(eat(position).size()));
					stars.add(position);
					obstacles.remove(position);
				}else {//se non posso mangiare, se riesco avanzo in alto 
					var newPos = new Pair<>(position.getX()-1, position.getY());
					if(!obstacles.contains(newPos) && !stars.contains(newPos)) {
						stars.remove(position);
						stars.add(newPos);
					}
				}
			}
		}
	}

	@Override
	public Set<Pair<Integer, Integer>> getStars() {
		return this.stars;
	}

	@Override
	public Set<Pair<Integer, Integer>> getObst() {
		return this.obstacles;
	}

	@Override
	public boolean isOver() {
		return this.obstacles.isEmpty();
	}

}
