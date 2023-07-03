package a02a.e2;

import java.util.HashSet;
import java.util.Set;


public class LogicImpl implements Logic {

	private final int size;
	private Set<Pair<Integer, Integer>> bshops = new HashSet<>();
	private Set<Pair<Integer, Integer>> elemDiag = new HashSet<>();
	
	public LogicImpl(int size) {
		this.size = size;
	}
	
	private void inizializeAdj(Pair<Integer, Integer> p) {
		for(int i=p.getX()-1, j=p.getY()-1; i>=0 ;i--, j--) {
				var e = new Pair<>(i, j);
				if(!elemDiag.contains(e) && isOk(e)) {
					elemDiag.add(e);
			}
		}
		for(int i=p.getX()+1, j=p.getY()+1; i<=size-1 ;i++, j++) {
			var e = new Pair<>(i, j);
			if(!elemDiag.contains(e) && isOk(e)) {
				elemDiag.add(e);
			}
		}
		for(int i=p.getX()+1, j=p.getY()-1; i<=size-1 ;i++, j--) {
			var e = new Pair<>(i, j);
			if(!elemDiag.contains(e) && isOk(e)) {
				elemDiag.add(e);
			}
		}
		for(int i=p.getX()-1, j=p.getY()+1; i>=0 ;i--, j++) {
			var e = new Pair<>(i, j);
			if(!elemDiag.contains(e) && isOk(e)) {
				elemDiag.add(e);
			}
		}
		
	}
	
	private boolean isOk(Pair<Integer, Integer> p) {
		return p.getX()>=0 && p.getY()<=size-1 && p.getY()>=0 && p.getX()<=size-1;
	}

	@Override
	public void hit(Pair<Integer, Integer> position) {
		if(!bshops.contains(position)) {
			bshops.add(position);
			inizializeAdj(position);
		}
	}

	@Override
	public boolean isOver() {
		return bshops.size()+elemDiag.size()==(size*size);
	}

	@Override
	public Set<Pair<Integer, Integer>> getB() {
		return this.bshops;
	}

	@Override
	public Set<Pair<Integer, Integer>> getEnabled() {
		return this.elemDiag;
	}

}
