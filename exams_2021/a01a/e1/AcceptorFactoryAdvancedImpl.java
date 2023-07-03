package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

	@Override
	public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
		return new Acceptor<String, Integer>() {
			private int count =0;
			@Override
			public Optional<Integer> end() {
				return Optional.of(count);
			}
			
			@Override
			public boolean accept(String e) {
				if(!e.isEmpty()) {
					count++;
				}
				return true;
			}
		};
	}

	@Override
	public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
		return new Acceptor<Integer, String>() {
			
			private String output = ""; 
			private List<Integer> list = new ArrayList<Integer>();
			
			@Override
			public Optional<String> end() {
				list.forEach(e->{output += e.toString();
							if (e != list.get(list.size() - 1)) {
					            output += ":";
					        }
							});
				if (list.size() < 2) {
					return Optional.empty();
				}
				return Optional.of(output) ;
			}
			
			@Override
			public boolean accept(Integer e) {
				if(list.isEmpty() || list.get(list.size()-1) < e) {
					list.add(e);
					return true;
				}
				return false;
			}
		};
	}

	@Override
	public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
		return new Acceptor<Integer, Integer>() {
			
			private List<Integer> list = new ArrayList<Integer>();
			private int sum =0;
			
			@Override
			public Optional<Integer> end() {
				if(list.size()==3) {
					list.forEach(e->{sum += e;});
					return Optional.of(sum);
				}
				return Optional.empty();
			}
			
			@Override
			public boolean accept(Integer e) {
				if(list.size()< 3) {
					list.add(e);
					return true;
				}
				list = new ArrayList<Integer>();
				return false;
			}
		};
	}

	@Override
	public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
		return new Acceptor<E, Pair<O1,O2>>() {

			@Override
			public boolean accept(E e) {
				
				return a1.accept(e) && a2.accept(e);
			}

			@Override
			public Optional<Pair<O1, O2>> end() {
				var p1 = a1.end();
				var p2 = a2.end();
				if(!p1.equals(Optional.empty()) && !p2.equals(Optional.empty())) {
					return Optional.of(new Pair<>(p1.get(), p2.get()));
				}
				return Optional.empty();
			}
		};
	}

	@Override
	public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
			Function<S, Optional<O>> outputFun) {
		return null;
	}

}
