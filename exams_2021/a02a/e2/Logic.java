package a02a.e2;

import java.util.Map;

public interface Logic {

	boolean hit();

	boolean isOver();

	Map<Pair<Integer, Integer>, Integer> getElem();

}
