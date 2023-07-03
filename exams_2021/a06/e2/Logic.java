package a06.e2;

import java.util.Set;

public interface Logic {

	void hitQuit();

	boolean isOver();

	void click(Pair<Integer, Integer> position);

	Set<Pair<Integer, Integer>> getElem();

	Set<Pair<Integer, Integer>> getStars();

}
