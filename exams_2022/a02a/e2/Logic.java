package a02a.e2;

import java.util.Set;

public interface Logic {

	void hit(Pair<Integer, Integer> position);

	boolean isOver();

	Set<Pair<Integer, Integer>> getB();

	Set<Pair<Integer, Integer>> getEnabled();

}
