package a05.e2;

import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public interface MyLogic {

	Map<Pair<Integer, Integer>, Object> getElem();

	void click(Pair<Integer, Integer> position);

	boolean isOver();

	
	enum Operator{
		AND((x,y)-> x&&y),OR((x,y)->x||y), XOR((x,y)->!(x&&y));
		
		BinaryOperator<Boolean> operator;

		private Operator(BinaryOperator<Boolean> operator) {
			this.operator = operator;
		}
		
		public boolean getOp(boolean n1,boolean n2) {
			return operator.apply(n1, n2);
		}
		
	}
}
