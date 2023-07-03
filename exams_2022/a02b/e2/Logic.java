package a02b.e2;

import java.util.Set;

public interface Logic {

	void hit(Pair<Integer, Integer> position);

	void checkButton();

	Set<Pair<Integer, Integer>> getStars();

	Set<Pair<Integer, Integer>> getEnabled();

}
