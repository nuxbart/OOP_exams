package a03c.e2;

import java.util.Set;

public interface Logic {

	void hit();

	boolean isOver();

	Set<Pair<Integer, Integer>> getObs();

	Pair<Integer, Integer> getStarPos();

}
