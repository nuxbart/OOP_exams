package a01b.e2;


import java.util.HashMap;
import java.util.Map;


public class LogicImpl implements Logic {
	
	private Map<Pair<Integer, Integer>, Integer> elem = new HashMap<>();
	private Pair<Integer, Integer> onePos ;
	private Pair<Integer, Integer> twoPos ;
	private boolean one = true;
	private boolean two = false;

	private void insertStars(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
		for(int i=min(p2.getX(), p1.getX()); i<=max(p2.getX(), p1.getX()); i++) {
			for(int j=min(p2.getY(), p1.getY()); j<=max(p2.getY(), p1.getY()); j++) {
				elem.put(new Pair<Integer, Integer>(i, j), 0);
			}
		}
	}
	
	@Override
	public void click(Pair<Integer, Integer> position) {
		if(one) {
			elem.put(position, 1);
			onePos = position;
			one =false;
			two = true;
		}else if(two) {
			if(isTwoOk(position)) {
				elem.put(position, 2);
				twoPos = position;
				insertStars(twoPos, onePos);
				elem.replace(onePos, 1);
				elem.replace(twoPos, 2);
				two = false;
			}
		}else {
			if(isPerp(position)) {
				insertStars(onePos, position);
				elem.replace(onePos, 1);
				elem.replace(twoPos, 2);
				elem.replace(position, 3);
			}
		}
		
	}

	private boolean isPerp(Pair<Integer, Integer> position) {
		return (onePos.getX() == position.getX() && Math.abs(onePos.getY()-position.getY()) == Math.abs(onePos.getX()-twoPos.getX())+1) ||
				(onePos.getY() == position.getY() && Math.abs(onePos.getX()-position.getX()) == Math.abs(onePos.getY()-twoPos.getY())+1);
	}

	private boolean isTwoOk(Pair<Integer, Integer> p) {
		return p.getY() == onePos.getY() || p.getX() == onePos.getX();
	}
	
	private int min(int a,int b) {
		return a <= b ? a : b; 
	}

	private int max(int a,int b) {
		return a >= b ? a : b; 
	}
	
	@Override
	public boolean isOver() {
		return this.elem.containsValue(3);
	}

	@Override
	public Map<Pair<Integer, Integer>, Integer> getElem() {
		return this.elem;
	}

}
