package a01c.e2;

import java.util.Set;

public interface Logic {

	void click(Pair<Integer, Integer> position);

	boolean isOver();

	Set<Pair<Integer, Integer>> getStars();

}
