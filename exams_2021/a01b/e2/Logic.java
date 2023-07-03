package a01b.e2;

import java.util.Map;

public interface Logic {

	void click(Pair<Integer, Integer> position);

	boolean isOver();

	Map<Pair<Integer, Integer>, Integer> getElem();

}
