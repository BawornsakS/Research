import java.io.DataInputStream;
import java.io.IOException;


public class Simulation {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		World world1 = new World();
		
		Item i1 = new Item("i1", 0, 90);
		Item i2 = new Item("i2", 45, 45);
		
		Robot r = new Robot("TestBot", 0, 0, 0);
		
		world1.addItem(i1);
		world1.addItem(i2);
		world1.addRobot(r);
		
		
		double angle = Math.atan((i1.getY()-r.getY())/(i1.getX()-r.getX()));
		angle = 90 - Math.toDegrees(angle);
		
		r.setOrientation(angle);
		
		
		boolean tracking = true;
		DataInputStream d = new DataInputStream(System.in);

		System.out.println("which item to track?");
		String in = "";
		try {
			in = d.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Item tracked = null;
		
		for(Item i : world1.getItems())
		{
			if(in.compareTo(i.getName())== 0){
				r.setTrack(i);
				tracked = i;
				break;
			}
		}
		
		
		while(true){
			
			String newX = "";
			String newY = "";
			
			System.out.println(tracked.getName() + "'s new x: ");
			try {
				newX = d.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.out.println(tracked.getName() + "'s new y: ");
			try {
				newY = d.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			tracked.setPosition(Double.parseDouble(newX), Double.parseDouble(newY));
			
			angle = Math.atan((tracked.getY()-r.getY())/(tracked.getX()-r.getX()));
			angle = 90 - Math.toDegrees(angle);
			
			r.setOrientation(angle);
			
			
			System.out.println("Orientation : " + r.getOrientation());
			
			
			
			if(in.compareTo("y") == 0)
				tracking = true;
			else
				tracking = false;
			
		}
		
	}

}