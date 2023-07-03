package a03b.e1;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LazyTreeFactoryImpl implements LazyTreeFactory {

	@Override
	public <X> LazyTree<X> constantInfinite(X value) {
		return new LazyTree<X>() {

			@Override
			public boolean hasRoot() {
				return true;
			}

			@Override
			public X root() {
				return value;
			}

			@Override
			public LazyTree<X> left() {
				return constantInfinite(value);
			}

			@Override
			public LazyTree<X> right() {
				return constantInfinite(value);
			}
		};
	}

	@Override
	public <X> LazyTree<X> fromMap(X root, Map<X, Pair<X, X>> map) {
		return new LazyTree<X>() {

			@Override
			public boolean hasRoot() {
				return true;
			}

			@Override
			public X root() {
				return root;
			}

			@Override
			public LazyTree<X> left() {
				if(map.get(root).getX()==null) {
					throw new NoSuchElementException();
					
				}
				return fromMap(map.get(root).getX(), map);
			}

			@Override
			public LazyTree<X> right() {
				if(map.get(root).getY()==null) {
					throw new NoSuchElementException();
					
				}
				return fromMap(map.get(root).getY(), map);
			}
		};
	}

	@Override
	public <X> LazyTree<X> cons(Optional<X> root, Supplier<LazyTree<X>> leftSupp, Supplier<LazyTree<X>> rightSupp) {
		return new LazyTree<X>() {

			@Override
			public boolean hasRoot() {
				if(root.isEmpty()) {
					return false;
				}
				return true;
			}

			@Override
			public X root() {
				
				return root.get();
			}

			@Override
			public LazyTree<X> left() {
				return leftSupp.get();
			}

			@Override
			public LazyTree<X> right() {
				return rightSupp.get();
			}
		};
	}

	@Override
	public <X> LazyTree<X> fromTwoIterations(X root, UnaryOperator<X> leftOp, UnaryOperator<X> rightOp) {
		return new LazyTree<X>() {

			@Override
			public boolean hasRoot() {
				
				return true;
			}

			@Override
			public X root() {
				
				return root;
			}

			@Override
			public LazyTree<X> left() {
				
				return fromTwoIterations(leftOp.apply(root),leftOp,rightOp);
			}

			@Override
			public LazyTree<X> right() {
				
				return fromTwoIterations(rightOp.apply(root),leftOp,rightOp);
			}
		};
	}

	private <X> LazyTree<X> falseTree(LazyTree<X> tree) {
		return new LazyTree<X>() {

			@Override
			public boolean hasRoot() {
				return false;
			}

			@Override
			public X root() {
				return null;
			}

			@Override
			public LazyTree<X> left() {
				return falseTree(tree);
			}

			@Override
			public LazyTree<X> right() {
				return falseTree(tree);
			}
		};
	}
	
	
	@Override
	public <X> LazyTree<X> fromTreeWithBound(LazyTree<X> tree, int bound) {
		return new LazyTree<X>() {

			int count =0;
			
			@Override
			public boolean hasRoot() {
				return tree.hasRoot();
			}

			@Override
			public X root() {
				
				return tree.root();
			}

			@Override
			public LazyTree<X> left() {
				count++;
				if(count<=bound) {
					return tree.left();
				}
				return falseTree(tree);
			}

			@Override
			public LazyTree<X> right() {
				count++;
				if(count<=bound) {
					return tree.right();
				}
				return  falseTree(tree);
			}
		};
	}

}
