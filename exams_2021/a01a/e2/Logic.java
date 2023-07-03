package a01a.e2;

import java.util.Map;

public interface Logic {

	void click(Pair<Integer, Integer> cell);

	boolean isOver();

	Map<Pair<Integer, Integer>, Integer> getElem();

}
