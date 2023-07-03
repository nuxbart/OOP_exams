package a04.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic {
	
	private final int size;
	private Pair<Integer, Integer> king;
	private Pair<Integer, Integer> torre;
	private Random random = new Random();

	public LogicImpl(int size) {
		this.size = size;
		this.reset();
	}
	
	private boolean isValid(Pair<Integer, Integer> p) {
		return !(p.equals(torre))&& (p.getX()==torre.getX() || p.getY()==torre.getY());
	}

	@Override
	public boolean hit(Pair<Integer, Integer> position) {
		if(isValid(position)) {
			this.torre=position;
			return true;
		}
		return false;
	}

	@Override
	public boolean isOver() {
		return this.king.equals(this.torre);
	}

	@Override
	public void reset() {
		this.king = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
		Pair<Integer, Integer> temp = null;
		do {
			temp = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
		}while(temp==this.king);
		this.torre = temp;
		
	}
	
	private List<Pair<Integer, Integer>> pcMoves(){
		List<Pair<Integer, Integer>> listMoves = new ArrayList<>();
		//metto tutti gli adiacenti che sono le possibili mosse
		listMoves.add(new Pair<Integer, Integer>(this.king.getX()+1, this.king.getY()+1));
		listMoves.add(new Pair<Integer, Integer>(this.king.getX()-1, this.king.getY()-1));
		listMoves.add(new Pair<Integer, Integer>(this.king.getX()-1, this.king.getY()));
		listMoves.add(new Pair<Integer, Integer>(this.king.getX()+1, this.king.getY()));
		listMoves.add(new Pair<Integer, Integer>(this.king.getX(), this.king.getY()-1));
		listMoves.add(new Pair<Integer, Integer>(this.king.getX(), this.king.getY()+1));
		return listMoves;
	}
	

	@Override
	public void computerMove() {
		if(pcMoves().contains(this.torre)) {
			this.king=this.torre;
		}else {
			Pair<Integer, Integer> t = null;
			do {
				t = pcMoves().get(random.nextInt(pcMoves().size()));
			}while(!(t.getX()>=0 && t.getY()<size && t.getX()<size && t.getY()>=0));
			this.king = t;
			
		}
	}

	@Override
	public Pair<Integer, Integer> getKing() {
		return this.king;
	}

	@Override
	public Pair<Integer, Integer> getTorre() {
		return this.torre;
	}
	

}
