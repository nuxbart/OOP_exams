package a04.e2;

import java.util.Map;
import java.util.function.BinaryOperator;

public interface MyLogic {

	int getResult();

	boolean hit(Pair<Integer, Integer> pos);

	Map<Pair<Integer, Integer>, Object> getElem();

	enum Operation{
		PLUS("+",(a,b)->a+b),MINUS("-",(a,b)->a-b),TIMES("*",(a,b)->a*b);
		
		BinaryOperator<Integer> operator;
		String name;

		private Operation(String name, BinaryOperator<Integer> operator) {
			this.operator = operator;
			this.name=name;
		}
		
		public String getName() {
			return name;
		}

		
		public Integer getOp(int a,int b) {
			return this.operator.apply(a, b);
		}
	}
	
}
