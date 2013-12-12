
public class Item {
	
	String name;
	double x;
	double y;
	
	public Item(String name, double x, double y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public String getName() {
		return name;
	}

}
