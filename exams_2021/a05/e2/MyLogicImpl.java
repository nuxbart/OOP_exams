package a05.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MyLogicImpl implements MyLogic {
	
	private final int size;
	
	private Map<Pair<Integer, Integer>, Object> elem = new HashMap<>();
	private Random random = new Random();
	private boolean isBoolean = true;
	private Operator currOp = Operator.AND;
	private boolean result = true;
	private boolean curr = true;
	private boolean first = true;

	public MyLogicImpl(int size) {
		this.size = size;
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if((i+j)%2==0) {
					elem.put(new Pair<Integer, Integer>(i, j), random.nextBoolean());
				}else {
					elem.put(new Pair<Integer, Integer>(i, j), random.nextInt(3)==0 ? Operator.AND : 
																random.nextInt(3)==1? Operator.OR : Operator.XOR);
				}
			}
		}
	}

	@Override
	public Map<Pair<Integer, Integer>, Object> getElem() {
		return this.elem;
	}

	@Override
	public void click(Pair<Integer, Integer> position) {
		if(isBoolean && first) {
			if(elem.get(position).equals(false)) {
				curr=false;
				first=false;
			}
			isBoolean=false;
		}else {
			if(isBoolean) {
				curr = getOperation(curr,elem.get(position),currOp);
				result=curr;
				isBoolean=false;
			}else {
				currOp=(Operator) elem.get(position);
				isBoolean=true;
				
			}
		}
		
		
	}

	private boolean getOperation(boolean res, Object object, Operator c) {
		
		return c.getOp(res, (boolean) object);
	}

	@Override
	public boolean isOver() {
		return !result && !first;
	}

}
