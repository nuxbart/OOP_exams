package a03a.e2;

public interface Logic {

	boolean hit(Pair<Integer, Integer> position);

	boolean pcWin();

	boolean playerWin();

	Pair<Integer, Integer> getPcT();

	Pair<Integer, Integer> getPlayerT();

	void getPcMove();

}
