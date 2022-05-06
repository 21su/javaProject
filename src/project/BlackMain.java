package project;

import java.util.*;

public class BlackMain {
	static void delay(int num) {
		try {
			Thread.sleep(num);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	static void clearcon() {
		for(int i = 0; i < 10; i ++) {
			System.out.println("");
		}
	}
	public static void main(String[] args) {
		BlackSystem bs = new BlackSystem();
		Scanner scan = new Scanner(System.in);
		int plMoney = 50000;
		turnout:
		while(true) {
			List<BlackCard> cpuDeck = new ArrayList<>();
			bs.deckSave();
			int plPoint = 0;
			int cpuPoint = 0;
			int blackwin = 0;
			int win = 0;
			boolean bust = false;
			System.out.println("소지금: " + plMoney);
			if(plMoney == 0) {
				System.out.println("전재산을 탕진하셨습니다.");
				break;
			}
			System.out.println("얼마를 배팅하시겠습니까? (100단위)");
			System.out.println("55를 입력하면 게임이 종료됩니다.");
			int payplMoney = 0;
			while(true) {
				payplMoney = scan.nextInt();
				if(payplMoney == 55) {
					System.out.println("종료합니다.");
					break turnout;
				}if(payplMoney > plMoney) {
					System.out.println("소지금이 부족합니다.");
				}else if(payplMoney % 100 != 0) {
					System.out.println("100단위로 사용해주세요.");
				}else {
					break;
				}
			}
			bs.resetCard();
			plMoney -= payplMoney;
			plPoint = bs.plStartDraw();
			cpuPoint = bs.cpuStartDraw();
			cpuDeck = bs.cpusecret();
			System.out.println(cpuDeck);
			System.out.println("점수: " + cpuDeck.get(0).getPoint() + " + ?");
			delay(3000);
			clearcon();
			if(cpuDeck.get(0).getPoint() == 11) {
				System.out.println(cpuDeck);
				while(true) {
					System.out.println("딜러의 공개카드가 A입니다. 사이드배팅을 하시겠습니까?");
					System.out.println("잔액: " + plMoney);
					System.out.println("|1.예|2.아니요|");
					int side = scan.nextInt();
					if (side == 1) {
						if(plMoney < payplMoney/2) {
							System.out.println("금액이 부족합니다.");
						}else {
							List<BlackCard> cpucard = bs.cpudeckchek();
							if(bs.cpublackchek(cpucard)) {
								System.out.println("블랙잭 맞췄습니다.");
								plMoney += payplMoney/2;
								continue turnout;
							}else if(!bs.cpublackchek(cpucard)) {
								System.out.println("블랙잭이 아닙니다.");
								plMoney -= payplMoney/2;
								break;
							}
						}
					}else if(side == 2) {
						System.out.println("사이드배팅을 하지 않습니다.");
						break;
					}else {
						System.out.println("다시 입력해 주세요.");
					}
				}
			}
			game:
				while(true) {
					System.out.println("플레이어 점수: " + plPoint);
					System.out.println("딜러 카드: " + cpuDeck);
					System.out.println("|1.히트|2.스테이|3.서렌더|");
					while(true) {
						int select = scan.nextInt();
						if (select == 1) {
							plPoint = bs.plDraw();
							if (plPoint > 21) {
								win = 2;
								System.out.println("플레이어 버스트");
								bust = true;
								delay(2000);
								break game;
							}	
							break;
						}else if (select == 2) {
							break game;
						}else if (select == 3) {
							win = 4;
							bust = true;
							break game;
						}else {
							System.out.println("다시 입력해주세요");
						}
					}	
				}
				if(!bust) {
					System.out.println("플레이어 턴 종료");
					delay(1000);
					clearcon();
					System.out.println("딜러 패");
					cpuDeck = bs.cpudeckchek();
					System.out.println(cpuDeck);
					delay(2000);
					while(!bust) {
						if(cpuPoint <= 16) {
							cpuPoint = bs.cpuDraw();
							cpuDeck = bs.cpudeckchek();
							System.out.println(cpuDeck);
						}else if (cpuPoint >= 17) {
							bust = true;
						}
						delay(2000);
					}
					System.out.println("딜러 턴 종료");
					delay(1000);
					clearcon();
				plPoint = bs.ender();
				bs.last();
				delay(3000);
				blackwin = bs.wincheck();
				if (win !=2 && win != 4) {
					if (cpuPoint > 21) {
						System.out.println("딜러 버스트");
						win = 1;
					}else if((21-plPoint) < (21-cpuPoint)) {
						win = 1;
					}else if ((21-plPoint) > (21-cpuPoint)) {
						win = 2;
					}else if ((21-plPoint) == (21-cpuPoint)) {
						win = 3;
					}
				}
				delay(2500);
				}
			if(win == 1) {
				System.out.println("Win");
				if(blackwin == 1) {
					System.out.println("블랙잭으로 인한 배팅금 1.5배");
					plMoney += payplMoney*2 + (payplMoney/2);
				}
				plMoney += payplMoney*2;
			}else if (win == 2){
				System.out.println("Lose");
				System.out.println("배팅금액을 잃습니다.");
			}else if( win == 3) {
				System.out.println("Push");
				System.out.println("배팅금액을 돌려받습니다.");
				plMoney += payplMoney;
			}else if(win == 4) {
				System.out.println("플레이어 서렌더");
				System.out.println("배팅금액에 50%를 돌려받습니다.");
				plMoney += payplMoney/2;
			}
			delay(2000);
			clearcon();
		}
	}

}
