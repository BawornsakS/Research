
public class Item {
	
	String name;
	double x;
	double y;
	double r;	// polar
	double a;	// polar
	
	public Item(String name, double x, double y){
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public void updatePolar(Robot robot){
		r = Math.sqrt((Math.pow(this.x - robot.getX() , 2) + Math.pow(this.y - robot.getY() , 2))); 
		
		a = Math.acos((this.x - robot.getX())/r);
		a = Math.toDegrees(a);
		
		a = a - robot.getOrientation();
	}
	
	public void randomMove(){
		int random = (int)Math.random()*4;
		
		if(random == 0)
			this.x++;
		else if(random == 1)
			this.x--;
		else if(random == 2)
			this.y++;
		else if(random == 3)
			this.y--;
		
		if(this.x < 0)
			this.x = 100;
		if(this.y < 0)
			this.y = 100;
		if(this.x > 100)
			this.x = 0;
		if(this.y > 100)
			this.y = 0;
			
	}
	
	public void setPosition(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public double getA(){
		return a;
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

	public Object getR() {
		return r;
	}

}
