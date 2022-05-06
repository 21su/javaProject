package project;

import java.util.*;

public class BlackSystem {
	Scanner scan = new Scanner(System.in);
	int plPoint = 0;
	int cpuPoint = 0;
	BlackDeck bd = new BlackDeck();

	int pointcheck(List<BlackCard> deck) {
		int point = 0;
		for (int i = 0;i < deck.size();i++) {
			point += deck.get(i).point;
		}
		return point;
	}

	public void deckSave() {
		List<BlackCard> deck = bd.deckcheck();
		if(deck.size() < 60) {
			System.out.println("덱을 채웁니다.");
			bd.resetDeck();
			for(int i = 0 ; i < 6; i ++) {
				forDeck("스페이드");
				forDeck("클로버");
				forDeck("다이아");
				forDeck("하트");
			}
		}
	}

	void forDeck(String pattern){
		for(int i = 1; i <= 13 ; i++) {
			BlackCard deck2 = new BlackCard(pattern,i);
			bd.deckSave(deck2);
		}
	}

	public void deckcheck() {
		List<BlackCard> deck = bd.deckcheck();
		for(BlackCard d: deck) {
			System.out.println(d);
		}
	}

	public int plStartDraw() {
		System.out.println("플레이어");
		bd.plDraw();
		bd.plDraw();
		List<BlackCard> pldeck = bd.plDeckCheck();
		System.out.println(pldeck);
		plPoint = pointcheck(pldeck);
		System.out.println("점수: " + plPoint);
		return plPoint;
	}

	public int cpuStartDraw() {
		System.out.println("딜러");
		bd.cpuDraw();
		bd.cpuDraw();
		List<BlackCard> cpudeck = bd.cpuDeckCheck();
		cpuPoint = pointcheck(cpudeck);
		return cpuPoint;
	}
	public int plDraw() {
		bd.plDraw();
		List<BlackCard> pldeck = bd.plDeckCheck();
		System.out.println(pldeck);
		plPoint = pointcheck(pldeck);
		return plPoint;
	}

	public int cpuDraw() {
		bd.cpuDraw();
		List<BlackCard> cpudeck = bd.cpuDeckCheck();
		cpuPoint = pointcheck(cpudeck);
		return cpuPoint;
	}

	public int ender() {
		List<BlackCard> pldeck = bd.plDeckCheck();
		for(int i = 0; i < pldeck.size(); i++) {
			if(pldeck.get(i).getPoint() == 1) {
				plPoint = pointcheck(pldeck);
				System.out.println(pldeck);
				System.out.println("현제 점수는 " + plPoint + "입니다.");
				System.out.println(pldeck.get(i).getPattern() + " " + pldeck.get(i).getNum() + "의 카드의 점수를 11로 하시겠습니까?");
				System.out.println("|1.예|2.아니요|");
				System.out.println("|1." + (plPoint+10) + "|2." + plPoint + "|");
				while(true) {
					int select = scan.nextInt();
					if(select == 1) {
						bd.ender(i);
						break;
					}
					else if(select == 2) {
						break;
					}else {
						System.out.println("다시 입력해주세요.");
					}
				}
				pldeck = bd.plDeckCheck();
				plPoint = pointcheck(pldeck);
			}
		}
		return plPoint;
		
	}

	public List<BlackCard> cpusecret() {
		List<BlackCard> cpudeck = bd.cpuDeckCheck();
		List<BlackCard> cpudeck2 = new ArrayList<>();
		for(int i = 0; i < cpudeck.size();i++) {
			if(cpudeck.get(i).point == 11) {
				cpudeck2.add(cpudeck.get(i));
				break;
			}
		}
		if(cpudeck2.size() == 0) {
			cpudeck2.add(cpudeck.get(0));
		}
		BlackCard bc = new BlackCard("?",0);
		cpudeck2.add(bc);
		return cpudeck2;
	}

	public void last() {
		List<BlackCard> pldeck = bd.plDeckCheck();
		List<BlackCard> cpudeck = bd.cpuDeckCheck();
		plPoint = pointcheck(pldeck);
		cpuPoint = pointcheck(cpudeck);
		System.out.println("플레이어 점수: "+plPoint);
		System.out.println(pldeck);
		System.out.println("딜러 점수: "+cpuPoint);
		System.out.println(cpudeck);
		
	}
	
	public int wincheck() {
		boolean plwin = bd.plWinCheck();
		boolean cpuwin = bd.cpuWinCheck();
		int win = 0;
		if(plwin && cpuwin) {
			win = 3;
		}else if(plwin) {
			win = 1;
		}else if(cpuwin) {
			win = 2;
		}
		return win;
	}
	void resetCard() {
		bd.resetCard();
	}

	public List<BlackCard> cpudeckchek() {
		List<BlackCard> cpudeck = bd.cpuDeckCheck();
		return cpudeck;
	}

	public boolean cpublackchek(List<BlackCard> cpucard) {
		boolean chek = false;
		chek = bd.cpuWinCheck(); 
		return chek;
	}
}
