package a01b.e2;

import java.util.Set;

public interface Logic {

	void hit(Pair<Integer, Integer> position);

	boolean isOver();

	Set<Pair<Integer, Integer>> getStars();

}
