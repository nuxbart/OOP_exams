package a06.e1;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


public class CirclerFactoryImpl implements CirclerFactory {

	@Override
	public <T> Circler<T> leftToRight() {
		return new Circler<T>() {

			List<T> el = new LinkedList<>();
			int current = 0;
			
			@Override
			public void setSource(List<T> elements) {
				this.el = elements;
				current = 0;
			}

			@Override
			public T produceOne() {
				if(current>=el.size()) {
					current = 0;
				}
				var temp = el.get(current);
				current++;
				return temp;
			}

			@Override
			public List<T> produceMany(int n) {
				List<T> newList = new LinkedList<>();
				for(int i=0; i<n;i++) {
					newList.add(produceOne());
				}
				return newList;
			}
		};
	}

	@Override
	public <T> Circler<T> alternate() {
		return new Circler<T>() {

			List<T> el = new LinkedList<>();
			int current = 0;
			
			@Override
			public void setSource(List<T> elements) {
				this.el = elements;
				current = 0;
			}

			private List<T> swapList(List<T> l) {
                List<T> swap = new ArrayList<>();
                for (int i = l.size() - 1; i >= 0; i--) {
                    swap.add(l.get(i));
                }
                return swap;
            }
			
			@Override
			public T produceOne() {
				if (current >= el.size()) {
					el = swapList(el);
					 current = 0;
				}
				var result = el.get(current);
                current++;
				return result;
			}

			@Override
			public List<T> produceMany(int n) {
				List<T> newList = new LinkedList<>();
				for(int i=0; i<n;i++) {
					newList.add(produceOne());
				}
				return newList;
			}
		};
	}

	@Override
	public <T> Circler<T> stayToLast() {
		return new Circler<T>() {
				List<T> el = new LinkedList<>();
				int current = 0;
				
				@Override
				public void setSource(List<T> elements) {
					this.el = elements;
					current = 0;
				}

				@Override
				public T produceOne() {
					if(current>=el.size()) {
						return el.get(el.size()-1);
					}
					var temp = el.get(current);
					current++;
					return temp;
				}

				@Override
				public List<T> produceMany(int n) {
					List<T> newList = new LinkedList<>();
					for(int i=0; i<n;i++) {
						newList.add(produceOne());
					}
					return newList;
				}
			};
	}

	@Override
	public <T> Circler<T> leftToRightSkipOne() {
		return new Circler<T>() {
			List<T> el = new LinkedList<>();
			int current = 0;
			
			@Override
			public void setSource(List<T> elements) {
				this.el = elements;
				current = 0;
			}


			@Override
			public T produceOne() {
				var result = el.get(current);
				current = (current + 2) % el.size();
	            return result;
			}

			@Override
			public List<T> produceMany(int n) {
				List<T> newList = new LinkedList<>();
				for(int i=0; i<n;i++) {
					newList.add(produceOne());
				}
				return newList;
			}
		};
	}

	@Override
	public <T> Circler<T> alternateSkipOne() {
		return null;
	}

	@Override
	public <T> Circler<T> stayToLastSkipOne() {
		return null;
	}

}
