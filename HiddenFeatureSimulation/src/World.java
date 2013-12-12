import java.util.ArrayList;
import java.util.List;


public class World {
	
	List<Item> items;
	Robot r;
	double width;
	double length;
	double light;
	
	public World(){
		this.items = new ArrayList<Item>();
		this.width = 100;
		this.length = 100;
		light = 0;
	}
	
	public void setLight(double n){
		this.light = n;
	}
	
	public void addItem(Item item){
		this.items.add(item);
	}
	
	public void addRobot(Robot r){
		this.r = r;
	}

	public List<Item> getItems() {
		return items;
	}
	
	public double getLight(){
		return light;
	}

}
