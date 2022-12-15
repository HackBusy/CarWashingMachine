
public class Car extends Wehicle {
	
	private int vehicleNum;
	
	public Car(int num) { this.vehicleNum = num;}
	
	public Car(WehicleWasher ww, int num) {
		this.que = ww;
		this.vehicleNum = num;
	}
	
	// TODO: add the formula for sleep
	public void run() {
		while (true) {
			for (int i = 0; i < que.getNumOfStations(); i++) {
				if(que.getWash(i).isEmpty()) {
					que.addToWash(this, i);
					System.out.println("Car: " + this.vehicleNum + " start washing");
					try {
						Thread.sleep((long) (que.AvgWash()*1000));
						que.removeFromStation(i);
						que.finishWash(this);
						System.out.println("Car: " + this.vehicleNum + " finish washing and added to finshed Cars ");
						try {
							notifyAll();
						} catch (Exception e) {}
						return;
					} catch(Exception e) {}
				}			
			}
			try {
				wait();
			} catch (Exception e) {}
		}
	}
	
	public String toString() {
		return"Car: " + this.vehicleNum;
	}

}
