package a04.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MyLogicImpl implements MyLogic {
	
	private final int size;
	private Map<Pair<Integer, Integer>, Object> elem = new HashMap<>();
	private int result = 0;
	private boolean num = true;
	private Operation currOp = Operation.PLUS;
	private Random random = new Random();
	 

	public MyLogicImpl(int size) {
		this.size = size;
		
		for(int i=0; i<size;i++) {
			for(int j=0;j<size;j++) {
				if(random.nextBoolean()) {
					this.elem.put(new Pair<Integer, Integer>(i, j), random.nextInt(10));
				}else {
					this.elem.put(new Pair<Integer, Integer>(i, j), random.nextInt(3)==0? 
									Operation.PLUS :  random.nextInt(3)==1 ? Operation.MINUS : Operation.TIMES);
				}
			}
		}
		
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public boolean hit(Pair<Integer, Integer> pos) {
		if(num && this.elem.get(pos).getClass().equals(Integer.class)) {
			result=doOperation(pos,result,currOp);
			num=false;
			return true;
		}else if(!num && !this.elem.get(pos).getClass().equals(Integer.class)){
			currOp = (Operation) this.elem.get(pos);
			num=true;
			return true;
		}
		return false;
	}

	private int doOperation(Pair<Integer, Integer> pos, int r, Operation c) {
		return c.getOp( r,(int) this.elem.get(pos));
	}

	@Override
	public Map<Pair<Integer, Integer>, Object> getElem() {
		return this.elem;
	}

}
