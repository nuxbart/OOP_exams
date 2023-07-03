package a01a.e2;

import java.util.List;

public interface Logic {

	void hit(Pair<Integer, Integer> position);

	List<Pair<Integer, Integer>> getStars();

	boolean isOver();

}
