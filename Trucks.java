
public class Trucks extends Wehicle {

	private int vehicleNum;
	
	public Trucks(int num) { this.vehicleNum = num;}
	public Trucks(WehicleWasher ww, int num) {
		this.que = ww;
		this.vehicleNum = num;
	}
	
	// TODO: everything
	public void run() {
		while (true) {
			for (int i = 0; i < que.getNumOfStations(); i++) {
				if(que.getWash(i).isEmpty()) {
					que.addToWash(this, i);
					System.out.println("Truck: " + this.vehicleNum + " start washing");
					try {
						Thread.sleep((long) (que.AvgWash()*1000));
						que.removeFromStation(i);
						System.out.println("Truck: " + this.vehicleNum + " finish washing and added to finshed Trucks ");
						que.finishWash(this);
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
		return"Truck: " + this.vehicleNum;
	}

}

