package a03a.e2;

import java.util.Set;

public interface Logic {

	void hit(Pair<Integer, Integer> pos);

	boolean isOver();

	Set<Pair<Integer, Integer>> getStars();

}
