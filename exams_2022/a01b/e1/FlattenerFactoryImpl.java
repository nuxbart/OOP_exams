package a01b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlattenerFactoryImpl implements FlattenerFactory {

	@Override
	public Flattener<Integer, Integer> sumEach() {
		return new Flattener<Integer, Integer>() {
			
			@Override
			public List<Integer> flatten(List<List<Integer>> list) {
				List<Integer> finalList = new ArrayList<>();
				for(var l : list) {
					int sum=0;
					for(var num :l) {
						sum+=num;
					}
					finalList.add(sum);
				}
				return finalList;
			}
		};
	}

	@Override
	public <X> Flattener<X, X> flattenAll() {
		return new Flattener<X, X>() {

			@Override
			public List<X> flatten(List<List<X>> list) {
				List<X> output = new ArrayList<>();
				for(var l : list) {
					for(var elem:l) {
						output.add(elem);
					}
					
				}
				return output;
			}
		};
	}

	@Override
	public Flattener<String, String> concatPairs() {
		return new Flattener<String, String>() {
			
			@Override
			public List<String> flatten(List<List<String>> list) {
				List<String> finalList = new ArrayList<>();
				for(int i=0; i<list.size();i=i+2) {
					if(i<list.size()-2) {
						var c = Stream.concat(list.get(i).stream(), list.get(i+1).stream()).collect(Collectors.toList()) ;
						finalList.add(c.toString());
					}else {
						finalList.add(list.get(i).toString());
					}
					
					
				}
				return finalList;
			}
		};
	}

	@Override
	public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
		return new Flattener<I, O>() {

			@Override
			public List<O> flatten(List<List<I>> list) {
				List<O> finaList= new ArrayList<>();
				for(var l : list) {
					finaList.add(mapper.apply(l));
				}
				return finaList;
			}
		};
	}

	@Override
	public Flattener<Integer, Integer> sumVectors() {
		return new Flattener<Integer, Integer>() {
			
			@Override
			public List<Integer> flatten(List<List<Integer>> list) {
				List<Integer> output = new ArrayList<>();
				int x=0;
				int y=0;
				int w=0;
				for(var l:list) {
					for(var e:l) {
						if(e.equals(l.get(0))){
							x+=e;
						}else if(e.equals(l.get(1))) {
							y+=e;
						}else if(e.equals(l.get(2))) {
							w+=e;
						}
					}
				}
				output.add(x);
				output.add(y);
				output.add(w);
				return output;
			}
		};
	}

}
