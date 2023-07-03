package a05.e1;

import java.util.Iterator;
import java.util.function.Function;

public class StateFactoryImpl implements StateFactory {

	@Override
	public <S, A> State<S, A> fromFunction(Function<S, Pair<S, A>> fun) {
		return new State<S, A>() {

			@Override
			public S nextState(S s) {
				return fun.apply(s).get1();
			}

			@Override
			public A value(S s) {
				return fun.apply(s).get2();
			}

			@Override
			public <B> State<S, B> map(Function<A, B> fun) {
				return fromFunction(f->new Pair<>(nextState(f), fun.apply(value(f))));
			}

			@Override
			public Iterator<A> iterator(S s0) {
				return  new Iterator<A>() {
					
					private A currValue;
					private S currState;
					
					@Override
					public boolean hasNext() {
						return true;
					}

					@Override
					public A next() {
						if(currValue==null) {
							currState = s0;
							currValue = value(s0);
							return currValue;
						}else {
							currState = nextState(currState);
							currValue = value(currState);
							return currValue;
						}
						
					}
				};
			
			}
		};
	}

	@Override
	public <S, A, B> State<S, B> compose(State<S, A> state1, State<S, B> state2) {
		return new State<S, B>() {

			@Override
			public S nextState(S s) {
				return state2.nextState(state1.nextState(s));
			}

			@Override
			public B value(S s) {
				return state2.value(state1.nextState(s));
			}

			@Override
			public <B> State<S, B> map(Function<B, B> fun) {
				return null;
			}

			@Override
			public Iterator<B> iterator(S s0) {
				return null;
			}
		};
	}

	@Override
	public State<Integer, String> incSquareHalve() {
		return new State<Integer, String>() {
			
			int currValue = 0;
			
			@Override
			public String value(Integer s) {
				return Integer.toString(s);
			}
			
			@Override
			public Integer nextState(Integer s) {
				return null;
			}
			
			@Override
			public <B> State<Integer, B> map(Function<String, B> fun) {
				return null;
			}
			
			@Override
			public Iterator<String> iterator(Integer s0) {
				currValue = s0;
				return new Iterator<String>() {

					@Override
					public boolean hasNext() {
						return true;
					}

					@Override
					public String next() {
						currValue++;
						currValue*=currValue;
						currValue/=2;
						return Integer.toString(currValue);
					}
					
				};
			}
		};
	}

	@Override
	public State<Integer, Integer> counterOp(CounterOp op) {
		return fromFunction(
				op == CounterOp.INC ? i-> new Pair<>(i+1, null) :
				op == CounterOp.GET ? i-> new Pair<>(i, i) :
				op == CounterOp.RESET ? i-> new Pair<>(0, null) : null);
	}

}
