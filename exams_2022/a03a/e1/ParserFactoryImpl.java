package a03a.e1;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory {

	@Override
	public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
		return new Parser<X>() {

			@Override
			public boolean accept(Iterator<X> iterator) {
				List<X> list = new ArrayList<>();
				while(iterator.hasNext()) {
					list.add(iterator.next());
				}
				for(var seq : acceptedSequences) {
					if(seq.equals(list)) {
						return true;
					}
				}
				return false;
			}
		};
	}

	@Override
	public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
		return null;
	}

	@Override
	public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
		return recursive(x->x.equals(x0) ?
				Optional.of(next.apply(x0).map(y->fromIteration(y, next))).orElse(Optional.empty()) 
				:Optional.empty(), false);
		}
				/* new Parser<X>() {

			@Override
			public boolean accept(Iterator<X> iterator) {
				X current = x0;
				while (iterator.hasNext()) {
					X nextElem = iterator.next();
					Optional<X> result = next.apply(current);
					if(result.get().equals(nextElem) && result.isPresent()) {
						current=nextElem;
					}else {
						return false;
					}
					
				}
				
				Optional<X> finalResult = next.apply(current);
            	return !finalResult.isPresent();
			}
		};
		
	}*/


	@Override
	public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
		return new Parser<X>() {
			
			@Override
			public boolean accept(Iterator<X> iterator) {
				
				if(!iterator.hasNext()) {
					return isFinal;
				}
				return nextParser.apply(iterator.next()).map(p->p.accept(iterator)).orElse(false);
			}
		};
	}

	@Override
	public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
		return recursive(i->i.equals(x) ? Optional.of(parser) : Optional.empty(), false);
	}

}
