package a01a.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

	@Override
	public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
		return new SubsequenceCombiner<Integer, Integer>() {
			
			@Override
			public List<Integer> combine(List<Integer> list) {
				List<Integer> finaList = new ArrayList<>();
				for(int i=0; i<list.size(); i=i+3) {
					int sum =0;
					for(int j=i; j<i+3;j++) {
						if(j<list.size()) {
							sum += list.get(j);
						}
					}
					finaList.add(sum);
				}
				
				return finaList;
			}
		};
	}

	@Override
	public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
		return new SubsequenceCombiner<X, List<X>>() {

			@Override
			public List<List<X>> combine(List<X> list) {
				List<List<X>> finaList = new ArrayList<>();
				for(int i=0; i<list.size(); i=i+3) {
					List<X> acc =new ArrayList<>();
					for(int j=i; j<i+3;j++) {
						if(j<list.size()) {
							acc.add(list.get(j));
						}
					}
					finaList.add(acc);
				}
				
				return finaList;
			}
		};
	}

	@Override
	public SubsequenceCombiner<Integer, Integer> countUntilZero() {
		
		return new SubsequenceCombiner<Integer, Integer>() {
			
			@Override
			public List<Integer> combine(List<Integer> list) {
				List<Integer> finaList = new ArrayList<>();
				int sum =0;
				for(var elem:list) {
					if(elem.equals(0) || elem.equals(list.get(list.size()-1))) {
						if(elem.equals(list.get(list.size()-1)) && elem!=0) {
							sum++;
						}
						finaList.add(sum);
						sum=0;
					}else {
						sum++;;
					}
				}
				return finaList;
			}
		};
	}

	@Override
	public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
		return new SubsequenceCombiner<X, Y>() {

			@Override
			public List<Y> combine(List<X> list) {
				List<Y> finaList = new ArrayList<>();
				for(var el : list) {
					finaList.add(function.apply(el));
				}
				return finaList;
			}
		};
	}

	@Override
	public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
		return new SubsequenceCombiner<Integer, List<Integer>>() {
			
			@Override
			public List<List<Integer>> combine(List<Integer> list) {
				List<List<Integer>> finaList = new ArrayList<>();
				int sum =0;
				List<Integer> acc = new ArrayList<>();
				for(var el :list) {
					if(sum>=100 || el.equals(list.get(list.size()-1))) {
						finaList.add(acc);
						sum=0;
						acc=new ArrayList<>();
					}
						acc.add(el);
						sum+=el;
				}
				finaList.add(acc);
				return finaList;
			}
		};
	}

}
