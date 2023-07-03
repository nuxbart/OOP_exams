package a04.e2;

import java.util.Map;

public interface Logic {

	int hitQuit();

	void clikCell(Pair<Integer, Integer> pos);

	Map<Pair<Integer, Integer>, Integer> getNum();

	Map<Pair<Integer, Integer>, String> getOp();

	Map<Pair<Integer, Integer>, String> getHitten();

}
