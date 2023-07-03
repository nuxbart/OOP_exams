package a05.e2;

import java.util.Map;
import java.util.function.BinaryOperator;

public interface Logic {

	boolean click(Pair<Integer, Integer> position);

	boolean isOver();

	Map<Pair<Integer, Integer>, Object> getElem();
	
	enum Operation{
		AND((x,y) -> x&&y ), OR((x,y) -> x==true || y==true), XOR((x,y) -> x==false && x==y);
		
		
		BinaryOperator<Boolean> operator;
		
		private Operation( BinaryOperator<Boolean> operator) {
			this.operator = operator;
		}
		
		public BinaryOperator<Boolean> getOperator(){
			return this.operator;
		}
		
	}

}
