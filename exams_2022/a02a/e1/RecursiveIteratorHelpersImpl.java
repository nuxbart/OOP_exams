package a02a.e1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers {
	@Override
	public <X> RecursiveIterator<X> fromList(List<X> list) {
		if(list.isEmpty()) {
			return null;
		}
	    return new RecursiveIterator<X>() {
	    	 Iterator<X> iterator = list.iterator();
	         X currentElement = iterator.hasNext() ? iterator.next() : null;

	         @Override
	         public X getElement() {
	             return currentElement;
	         }

	         @Override
	         public RecursiveIterator<X> next() {
	             if (iterator.hasNext()) {
	                 currentElement = iterator.next();
	                 return this;
	             } else {
	                 return null;
	             }
	         }
	     };
	}
	
	@Override
	public <X> List<X> toList(RecursiveIterator<X> input, int max) {
		List<X> newList = new LinkedList<>();
		for(int i=0;  i < max && input != null;i++) {
			newList.add(input.getElement());
			input=input.next();
		}
		return newList;
	}

	@Override
	public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
		if(first== null || second==null) {
			return null;
		}
		return new RecursiveIterator<Pair<X,Y>>() {
			@Override
			public Pair<X, Y> getElement() {
				return new Pair<X, Y>(first.getElement(), second.getElement());
			}

			@Override
			public RecursiveIterator<Pair<X, Y>> next() {
				return zip(first.next(), second.next());
			}
		};
	}


	private <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex2(RecursiveIterator<X> iterator, int index) {
	    if (iterator == null) {
	        return null;
	    }
	
	    final int currentIndex = index;
	    
	    return new RecursiveIterator<Pair<X, Integer>>() {
	        @Override
	        public Pair<X, Integer> getElement() {
	            return new Pair<X, Integer>(iterator.getElement(), currentIndex);
	        }
	
	        @Override
	        public RecursiveIterator<Pair<X, Integer>> next() {
	            return zipWithIndex2(iterator.next(), currentIndex + 1);
	        }
	    };
	}
	
	@Override
	public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
		return zipWithIndex2(iterator, 0);
	}

	@Override
	public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
		return new RecursiveIterator<X>() {
	        boolean isFirstIterator = true;

	        @Override
	        public X getElement() {
	            if (isFirstIterator) {
	                return first.getElement();
	            } else {
	                return second.getElement();
	            }
	        }

	        @Override
	        public RecursiveIterator<X> next() {
	            if (isFirstIterator) {
	                isFirstIterator = false;
	                if (first.next() == null) {
	                    return second;
	                }
	            } else {
	                isFirstIterator = true;
	                if (second.next() == null) {
	                    return first;
	                }
	            }
	            return this;
	        }
	    };
	}

}
