package a05.e2;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;



public class LogicImpl implements Logic {
	
	private final int size;
	private final Map<Pair<Integer, Integer>, Object> elem = new HashMap<>();
	private Optional<Operation> current = Optional.of(Operation.OR);
	private Random random = new Random();
	private boolean flag = false;
	private boolean status = true;
	private boolean first = true;
	private Boolean res = true;

	public LogicImpl(int size) {
		this.size = size;
		for (int j=0;j<=size;j++) {
			for(int i=0; i<=size; i++) {
				if(!flag) {
					elem.put(new Pair<Integer, Integer>(j, i), random.nextBoolean());
					flag=true;
				}else {
					flag=false;
					elem.put(new Pair<Integer, Integer>(j, i),random.nextInt(3)==0 ? Operation.AND : random.nextInt(3)==1? Operation.OR : Operation.XOR );
				}
			}
			if(j%2!=0) {
				flag=false;
			}else {
				flag=true;
			}
		}
		
	}
	
	private boolean isBoolean(Pair<Integer, Integer> p) {
		return this.elem.get(p) instanceof Boolean;
	}

	
	private Optional<Operation> getCellAsOperation(Pair<Integer, Integer> p) {
		return Optional.of(elem.get(p)).filter(o -> o instanceof Operation).map(o -> (Operation)o);
	}
	
	@Override
	public boolean click(Pair<Integer, Integer> position) {
		if(first && isBoolean(position)) {
			status=(Boolean) elem.get(position);
			current = Optional.empty();
			first=false;
			return true;
		}else {

			if(isBoolean(position) && current.isPresent()) {
				res = this.current.get().getOperator().apply(status,(Boolean) elem.get(position));
				current = Optional.empty();
				status=res;
				return true;
			}else if(!isBoolean(position) && current.isEmpty()) {
				current=getCellAsOperation(position);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isOver() {
		return !res;
	}

	@Override
	public Map<Pair<Integer, Integer>, Object> getElem() {
		return this.elem;
	}

}
