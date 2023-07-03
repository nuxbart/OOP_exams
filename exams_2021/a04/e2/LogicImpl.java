package a04.e2;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class LogicImpl implements Logic {
	
	private final int size;
	private Map<Pair<Integer, Integer>, Integer> num = new HashMap<>();
	private Map<Pair<Integer, Integer>, String> op = new HashMap<>();
	private Map<Pair<Integer, Integer>, String> hitten = new HashMap<>(); 
	private Random random = new Random();
	private Pair<Integer, Integer> lastHitten = new Pair<Integer, Integer>(-1, -1);
	private boolean number = false;
	private boolean flag = false;
	private int result = 0;

	public LogicImpl(int size) {
		this.size = size;
		for (int i =0; i<=size;i++) {
			for(int j =0; j<=size;j++) {
				var choice = random.nextInt(2);
				if(choice==1) {
					num.put(new Pair<Integer, Integer>(i, j), random.nextInt(10));
				}else {
					var operator = random.nextInt(3);
					op.put(new Pair<Integer, Integer>(i, j), operator==0 ? "+" : operator==1 ? "-" :"*");
				}
			}
		}
	}
	
	private int calculateOp() {
		Deque<Pair<Integer, Integer>> list = new LinkedList<>(hitten.keySet());
		int j = 0;
		int n1 = 0; String op3 = null; int n2 = 0;
		while(list.size()>1) {
			
			if(flag) {j=1;}
			for(int i=j; i<=3;i++) {
				var x =  list.pollFirst();
				if(num.containsKey(x) && i==0) {
					n1=num.get(x);
				}else if(op.containsKey(x)) {
					op3 = op.get(x);
				}else if(num.containsKey(x)){
					n2= num.get(x);
				}
			}
			result = op3.equals("-") ? (n1-n2) : op3.equals("+")? (n1+n2) : (n1*n2);
			n1 = result;
			flag=true;
		}
		return result;
	}

	@Override
	public int hitQuit() {
		return calculateOp();
	}

	@Override
	public void clikCell(Pair<Integer, Integer> pos) {
		if(lastHitten.equals(new Pair<Integer, Integer>(-1, -1))) {//prima cella cliccata
			if(num.containsKey(pos)) {
				number=true;
				hitten.put(pos, num.get(pos).toString());
				lastHitten = pos;
			} 
		}else {
			if(number && op.containsKey(pos)) {//cella precedente era un numero e clicchiamo un operatore
				number=false;
				hitten.put(pos, op.get(pos));
				lastHitten=pos;
				
			}else if(!number && num.containsKey(pos)) {//cella prec era un operatore e clicchiamo un numero
				number=true;
				hitten.put(pos, num.get(pos).toString());
				lastHitten=pos;
			}
		}
	}

	@Override
	public Map<Pair<Integer, Integer>, Integer> getNum() {
		return this.num;
	}

	@Override
	public Map<Pair<Integer, Integer>, String> getOp() {
		return this.op;
	}

	@Override
	public Map<Pair<Integer, Integer>, String> getHitten() {
		return this.hitten;
	}

}
