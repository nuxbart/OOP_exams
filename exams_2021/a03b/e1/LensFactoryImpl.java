package a03b.e1;

import java.util.ArrayList;
import java.util.List;

public class LensFactoryImpl implements LensFactory {

	@Override
	public <E> Lens<List<E>, E> indexer(int i) {
		return new Lens<List<E>, E>() {

			@Override
			public E get(List<E> s) {
				return s.get(i);
			}

			@Override
			public List<E> set(E a, List<E> s) {
				List<E> n = new ArrayList<>();
				 s.forEach(e->{
					if(s.get(i).equals(e)) {
						n.add(a);
					}else {
						 n.add(e);
					}
				});
				return n;
			}
	
		};
		}

	@Override
	public <E> Lens<List<List<E>>, E> doubleIndexer(int i, int j) {
		
		return new Lens<List<List<E>>, E>() {

			@Override
			public E get(List<List<E>> s) {
				return s.get(i).get(j);
			}

			@Override
			public List<List<E>> set(E a, List<List<E>> s) {
				List<List<E>> n = new ArrayList<>();
				 s.forEach(l->{
					 List<E> intL = new ArrayList<>();
					if(s.get(i).equals(l)) {
						l.forEach(e->{
							if(l.get(j).equals(e)) {
								intL.add(a);
							}else{
								intL.add(e);
							}
						}
						);
						
					}else {
						l.forEach(e->{
							intL.add(e);
						});
						
					}
					n.add(intL);
				});
				return n;
			}
		};
	}

	@Override
	public <A, B> Lens<Pair<A, B>, A> left() {
		return new Lens<Pair<A,B>, A>() {

			@Override
			public A get(Pair<A, B> s) {
				
				return s.get1();
			}

			@Override
			public Pair<A, B> set(A a, Pair<A, B> s) {
				return new Pair<>(a, s.get2());
			}
		};
	}

	@Override
	public <A, B> Lens<Pair<A, B>, B> right() {
		return new Lens<Pair<A,B>, B>() {

			@Override
			public B get(Pair<A, B> s) {
				return s.get2();
			}

			@Override
			public Pair<A, B> set(B a, Pair<A, B> s) {
				return new Pair<>(s.get1(), a);
			}
		};
	}

	@Override
	public <A, B, C> Lens<List<Pair<A, Pair<B, C>>>, C> rightRightAtPos(int i) {
		
		return new Lens<List<Pair<A,Pair<B,C>>>, C>() {

			@Override
			public C get(List<Pair<A, Pair<B, C>>> s) {
				return s.get(i).get2().get2();
			}

			@Override
			public List<Pair<A, Pair<B, C>>> set(C a, List<Pair<A, Pair<B, C>>> s) {
				List<Pair<A, Pair<B, C>>> sNew = new ArrayList<>();
				for(var l :s) {
					if(s.get(i).equals(l)) {
						var p = new Pair<>(l.get2().get1(), a);
						sNew.add(new Pair<A, Pair<B,C>>(l.get1(), p));
					}else {
						sNew.add(new Pair<A, Pair<B,C>>(l.get1(), l.get2()));
					}
					
				}
				return sNew;
			}
		};
	}

}
