package a01c.e1;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;


public class EventHistoryFactoryImpl implements EventHistoryFactory {

	@Override
	public <E> EventHistory<E> fromMap(Map<Double, E> map) {
		return new EventHistory<E>() {

			private double count = 1.0;
			
			@Override
			public double getTimeOfEvent() {
				for(var s : map.keySet() ) {
					if(s.equals(count)) {
						return s;
					}
				}
				return 0;
			}

			@Override
			public E getEventContent() {
				return map.get(count);
			}

			@Override
			public boolean moveToNextEvent() {
				count++;
				return map.containsKey(count) ? true : false;
			}
		};
	}

	@Override
	public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
		return new EventHistory<E>() {

			@Override
			public double getTimeOfEvent() {
				
				return times.next();
			}

			@Override
			public E getEventContent() {
				return content.next();
			}

			@Override
			public boolean moveToNextEvent() {
				return content.hasNext();
			}
		};
	}

	@Override
	public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
		return new EventHistory<E>() {

			private int count = 0;
			
			@Override
			public double getTimeOfEvent() {
				return initial + delta * count;
			}

			@Override
			public E getEventContent() {
				return content.get(count);
			}

			@Override
			public boolean moveToNextEvent() {
				count++;
				return content.size() > count;
			}
		};
	}

	@Override
	public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
		return new EventHistory<E>() {

			private int countEvent = 0;
			
			@Override
			public double getTimeOfEvent() {
				return Math.random();   //return 1.0;  IO AVEVO MESSO COSI' E ANDAVA BENE!!
			}

			@Override
			public E getEventContent() {
				countEvent++;
				return content.get();
			}

			@Override
			public boolean moveToNextEvent() {
				return size > countEvent;
			}
		};
	}

	@Override
	public EventHistory<String> fromFile(String file) throws IOException {
		return new EventHistory<String>() {
			
			List<Pair<Double, String>> events = readFile();
			private int count = 0;
			
			@Override
			public boolean moveToNextEvent() {
				return events.size() > count;
			}
			
			@Override
			public double getTimeOfEvent() {
				return events.get(count).get1();
			}
			
			@Override
			public String getEventContent() {
				return events.get(count).get2();
			}
			
			 private List<Pair<Double, String>> readFile() throws IOException {
	                var f = new File(file).getAbsoluteFile();
	                List<Pair<Double, String>> result = new ArrayList<>();
	                try (var reader = new BufferedReader(new FileReader(f))) {
	                    while (reader.ready()) {
	                        var line = reader.readLine().split(":", 2);
	                        result.add(new Pair<>(Double.parseDouble(line[0]), line[1]));
	                    }
	                }
	                return result;
	            }
			
		};
	}

}
