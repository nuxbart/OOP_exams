package a03a.e2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LogicImpl implements Logic {
	
	private final int size;
	private Pair<Integer, Integer> pcT;
	private Pair<Integer, Integer> playerT;
	private Random random = new Random();
	private boolean playerWinner = false;
	private boolean pcWinner = false;
	
	public LogicImpl(int size) {
		this.size = size;
		pcT = new Pair<Integer, Integer>(random.nextInt(size), random.nextInt(size));
		Pair<Integer, Integer> t = null;
		do {
			t=new Pair<>(random.nextInt(size), random.nextInt(size));
		}while(t==pcT);
		playerT=t;
		
	}
	
	private boolean isValid(Pair<Integer, Integer> p, Pair<Integer, Integer> curr) {
		return (!p.equals(curr)) && (p.getX()==curr.getX() || p.getY()==curr.getY());
	}

	@Override
	public boolean hit(Pair<Integer, Integer> position) {
		
		if(isValid(position, playerT)) {
			playerT=position;
			if(position.equals(pcT)) {
				playerWinner=true;
			}
		    return true;
		}
		return false;
	}

	@Override
	public boolean pcWin() {
		return pcWinner;
	}

	@Override
	public boolean playerWin() {
		return playerWinner;
	}

	@Override
	public Pair<Integer, Integer> getPcT() {
		return this.pcT;
	}

	@Override
	public Pair<Integer, Integer> getPlayerT() {
		return this.playerT;
	}

	@Override
	public void getPcMove() {
		Pair<Integer, Integer> randomPos= null;
		do {
			randomPos=getPos().get(random.nextInt(getPos().size()));
		}while(randomPos==pcT);
		this.pcT=randomPos;
		if(this.pcT.equals(this.playerT)) {
			pcWinner=true;
		}
		
	}

	private List<Pair<Integer, Integer>> getPos() {
		List<Pair<Integer, Integer>> pos = new ArrayList<>();
		for(int x=0;x<this.pcT.getX();x++) {
			pos.add(new Pair<Integer, Integer>(x, this.pcT.getY()));
		}
		for(int x=this.pcT.getX();x<size;x++) {
			pos.add(new Pair<Integer, Integer>(x, this.pcT.getY()));
		}
		for(int y=0;y<this.pcT.getY();y++) {
			pos.add(new Pair<Integer, Integer>(this.pcT.getX(), y));
		}
		for(int y=this.pcT.getY();y<size;y++) {
			pos.add(new Pair<Integer, Integer>(this.pcT.getX(),y));
		}
		
		return pos;
	}

}
