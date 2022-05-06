package project;

import java.util.*;

public class BlackDeck {
	static List<BlackCard> deck = new ArrayList<>();
	static List<BlackCard> pldk = new ArrayList<>();
	static List<BlackCard> cpudk = new ArrayList<>();
	
	public void deckSave(BlackCard deck2) {
		deck.add(deck2);
		
	}

	public List<BlackCard> deckcheck() {
		return deck;
	}

	public void plDraw() {
		int ran = (int)(Math.random()*deck.size());
		pldk.add(deck.get(ran));
		deck.remove(ran);
	}
	public void cpuDraw() {
		int ran = (int)(Math.random()*deck.size());
		cpudk.add(deck.get(ran));
		int point = 0;
		if(cpudk.get(cpudk.size()-1).getPoint() == 1) {
			for(int i = 0; i < cpudk.size();i++) {
				point += cpudk.get(i).getPoint();
			}
			if(point+10 <= 21) {
				cpudk.get(cpudk.size()-1).setPoint(11);
			}
		}
		deck.remove(ran);
		
	}
	public List<BlackCard> plDeckCheck() {
		return pldk;
	}

	public List<BlackCard> cpuDeckCheck() {
		return cpudk;
	}

	public void resetDeck() {
		deck.clear();;
		pldk.clear();;
		cpudk.clear();;
		
	}
	public void resetCard() {
		pldk.clear();;
		cpudk.clear();;
		
	}

	public void ender(int i) {
		pldk.get(i).setPoint(11);
		
	}

	public boolean plWinCheck() {
		boolean win = false;
		if(pldk.size()==2) {
			for(int i = 0; i < pldk.size();i++) {
				if(pldk.get(i).getPoint() == 10) {
					for(int j = 0; j < pldk.size();j++) {
						if(pldk.get(j).getPoint() == 11) {
							System.out.println("플레이어 블랙잭!");
							win = true;
						}
					}
				}
			}
		}
		return win;
	}

	public boolean cpuWinCheck() {
		boolean win = false;
		if(cpudk.size()==2) {
			for(int i = 0; i < cpudk.size();i++) {
				if(cpudk.get(i).getPoint() == 10) {
					for(int j = 0; j < cpudk.size();j++) {
						if(cpudk.get(j).getPoint() == 11) {
							System.out.println("딜러 블랙잭!");
							win = true;
						}
					}
				}
			}
		}
		return win;
	}


}
