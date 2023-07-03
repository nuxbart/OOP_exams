package a03c.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class DeterministicParserFactoryImpl implements DeterministicParserFactory {

	@Override
	public DeterministicParser oneSymbol(String s) {
		return new DeterministicParser() {
			
			@Override
			public Optional<List<String>> accepts(List<String> tokens) {
				if(tokens.contains(s)) {
					var l = new ArrayList<>(tokens);
					l.remove(s);
					return Optional.of(l);
				} 
				return Optional.empty();
			}
		};
	}

	@Override
	public DeterministicParser twoSymbols(String s1, String s2) {
		return new DeterministicParser() {
			
			@Override
			public Optional<List<String>> accepts(List<String> tokens) {
				if(tokens.contains(s1) && tokens.contains(s2)) {
					var l = new ArrayList<>(tokens);
					l.remove(s1);
					l.remove(s2);
					return Optional.of(l);
				} 
				return Optional.empty();
			}
		};
	}

	@Override
	public DeterministicParser possiblyEmptyIncreasingSequenceOfPositiveNumbers() {
		return new DeterministicParser() {
			
			@Override
			public Optional<List<String>> accepts(List<String> tokens) {
				List<String> newList = new ArrayList<>();
				int count = 0;
				String last = "";
				for(var n :tokens) {
					count++;
					if(Integer.parseInt(n)>0) {
						if(newList.isEmpty() && count==1) {
							newList.add(n);
							last=n;
						}else if(!newList.isEmpty() && Integer.parseInt(n)>Integer.parseInt(last)) {
							newList.add(n);
							last=n;
						}else if(newList.isEmpty() && count>1){
							return Optional.of(tokens);
						}
					}
				}
				List<String> newList2 = new ArrayList<>();
				tokens.forEach(s->{
					if(!newList.contains(s)) {
						newList2.add(s);
					}
				});
				return Optional.of(newList2);
			}
		};
	}

	@Override
	public DeterministicParser sequenceOfParsersWithDelimiter(String start, String stop, String delimiter,
			DeterministicParser element) {
		return new DeterministicParser() {
			
			@Override
			public Optional<List<String>> accepts(List<String> tokens) {
				return Optional.empty();
			}
		};
	}

	@Override
	public DeterministicParser sequence(DeterministicParser first, DeterministicParser second) {
		return new DeterministicParser() {
			
			@Override
			public Optional<List<String>> accepts(List<String> tokens) {
				
				return first.accepts(tokens).isEmpty() ? Optional.empty() :
					second.accepts(first.accepts(tokens).get()).isEmpty() ? 
					Optional.empty() : Optional.of(second.accepts(first.accepts(tokens).get()).get());
			}
		} ;
	}

}
