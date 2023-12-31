package a03a.e1;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DecisionChainFactoryImpl implements DecisionChainFactory {

	@Override
	public <A, B> DecisionChain<A, B> oneResult(B b) {
		return new DecisionChain<A, B>() {

			@Override
			public Optional<B> result(A a) {
				return Optional.of(b);
			}

			@Override
			public DecisionChain<A, B> next(A a) {
				return null;
			}
		};
	}

	@Override
	public <A, B> DecisionChain<A, B> simpleTwoWay(Predicate<A> predicate, B positive, B negative) {
		return new DecisionChain<A, B>() {

			@Override
			public Optional<B> result(A a) {
				
				return Optional.empty();
			}

			@Override
			public DecisionChain<A, B> next(A a) {
				return new DecisionChain<A, B>() {

					@Override
					public Optional<B> result(A a) {
						return predicate.test(a) ? Optional.of(positive) : Optional.of(negative);
					}

					@Override
					public DecisionChain<A, B> next(A a) {
						return null;
					}
				};
			}
		};
	}

	@Override
	public <A, B> DecisionChain<A, B> enumerationLike(List<Pair<A, B>> mapList, B defaultReply) {
		
		return new DecisionChain<A, B>() {

			@Override
			public Optional<B> result(A a) {
				 
				return mapList.isEmpty() ? Optional.of(defaultReply) :
					   mapList.get(0).get1().equals(a) ? Optional.of(mapList.get(0).get2()) : Optional.empty();
			}

			@Override
			public DecisionChain<A, B> next(A a) {
				var list = new LinkedList<>(mapList);
				list.poll();
				return enumerationLike(list, defaultReply);
			}
		};
	}

	@Override
	public <A, B> DecisionChain<A, B> twoWay(Predicate<A> predicate, DecisionChain<A, B> positive,
			DecisionChain<A, B> negative) {
		return new DecisionChain<A, B>() {

			@Override
			public Optional<B> result(A a) {
				return  Optional.empty();
			}

			@Override
			public DecisionChain<A, B> next(A a) {
				return predicate.test(a) ? positive : negative;
			}
		};
	}

	@Override
	public <A, B> DecisionChain<A, B> switchChain(List<Pair<Predicate<A>, B>> cases, B defaultReply) {
		return new DecisionChain<A, B>() {

			@Override
			public Optional<B> result(A a) {
				return cases.isEmpty() ? Optional.of(defaultReply) : 
					   cases.get(0).get1().test(a) ? Optional.of(cases.get(0).get2()) : Optional.empty() ;
			}

			@Override
			public DecisionChain<A, B> next(A a) {
				var list = new LinkedList<>(cases);
				list.poll();
				return switchChain(list, defaultReply);
			}
		};
	}

}
