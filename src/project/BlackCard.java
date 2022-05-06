package project;

public class BlackCard {
	String pattern;
	String num;
	int point;
	
	
	@Override
	public String toString() {
		return pattern + " - " + num + " ";
	}
	public BlackCard(String pattern, int num) {
		this.pattern = pattern;
		if (num <= 10 && num != 1) {
			String stnum = String.valueOf(num);
			this.num = stnum;
		}else if (num == 11) {
			this.num = "J";
		}else if (num == 12) {
			this.num = "Q";
		}else if (num == 13) {
			this.num = "K";
		}else if (num == 1) {
			this.num = "A";
		}
		if(num > 10) {
			this.point = 10;
		}else {
			this.point = num;
		}
		
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
}
