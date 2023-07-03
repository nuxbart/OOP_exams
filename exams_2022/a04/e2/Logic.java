package a04.e2;

public interface Logic {

	boolean hit(Pair<Integer, Integer> position);

	boolean isOver();

	void reset();

	void computerMove();

	Pair<Integer, Integer> getKing();

	Pair<Integer, Integer> getTorre();

}
