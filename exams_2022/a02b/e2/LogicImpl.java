package a02b.e2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LogicImpl implements Logic {
	
	private final int size;
	private Set<Pair<Integer, Integer>> star = new HashSet<>();
	private Set<Pair<Integer, Integer>> enabled =  new HashSet<>();
	

	public LogicImpl(int size) {
		this.size = size;
	}

	@Override
	public void hit(Pair<Integer, Integer> position) {
		if(this.star.contains(position)) {
			this.star.remove(position);
		}else if (!this.star.contains(position)) {
			this.star.add(position);
		}
	}

	private boolean elemDiag(){
		Set<Pair<Integer, Integer>> cells = new HashSet<>();
		int numStars =0;
		
		for(int i=0; i<size;i++) {
			cells.clear();
			numStars =0;
			for(int j=0; j<=i;j++) {
				var c = new Pair<>(j, (size-1)-i+j );
				if(this.star.contains(c)) {
					cells.add(c);
					numStars++;
				}
			}
			
			if(numStars==3) {
				this.enabled.addAll(cells);
				return true;
			}
		}
		return false;
	} 
	
	@Override
	public void checkButton() {
		if(elemDiag()) {
			
		}
			
	}

	@Override
	public Set<Pair<Integer, Integer>> getStars() {
		return this.star;
	}

	@Override
	public Set<Pair<Integer, Integer>> getEnabled() {
		return this.enabled;
	}
	

}
