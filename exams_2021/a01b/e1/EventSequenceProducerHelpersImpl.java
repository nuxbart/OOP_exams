package a01b.e1;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.function.Supplier;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers {

	@Override
	public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
		
		return new EventSequenceProducer<E>() {

			@Override
			public Pair<Double, E> getNext() throws NoSuchElementException {
				return iterator.next();
			}
		};
	}

	@Override
	public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
		return toStream(sequence)
				.dropWhile(e -> e.get1() < fromTime)
				.takeWhile(e -> e.get1() < toTime)
				.map(Pair::get2)
				.toList();
	}
	
	 // Creates a stream from the event producer.
    private <E> Stream<Pair<Double, E>> toStream(EventSequenceProducer<E> producer) {
        Supplier<Optional<Pair<Double, E>>> supplier = new Supplier<Optional<Pair<Double,E>>>() {

            @Override
            public Optional<Pair<Double, E>> get() {
                try {
                    return Optional.of(producer.getNext());
                } catch (Exception e) {
                    return Optional.empty();
                }
            }
            
        };

        return Stream.generate(supplier)
                .takeWhile(Optional::isPresent)
                .map(Optional::get);
    }
	@Override
	public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
		
		return ()->toStream(sequence).map(Pair::get2).iterator();
	}

	@Override
	public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
		
		return toStream(sequence).dropWhile(e->e.get1() < time).findFirst();
	}

	@Override
	public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
		
		return fromIterator(
				toStream(sequence).filter(e -> predicate.test(e.get2())).iterator()
				);
	}

}
