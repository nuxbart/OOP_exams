package a03b.e2;

import java.util.Set;


public interface Logic {

	void hit(Pair<Integer, Integer> position);

	Set<Pair<Integer, Integer>> getStars();

	Set<Pair<Integer, Integer>> getObst();

	boolean isOver();

}
