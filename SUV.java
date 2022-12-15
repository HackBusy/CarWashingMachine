
public class SUV extends Wehicle {	
	
	private int vehicleNum;
	
	public SUV(int num) { this.vehicleNum = num;}
	
	public SUV(WehicleWasher ww, int num) {
		this.que = ww;
		this.vehicleNum = num;
	}
	// TODO: everything
	public void run() {
		while (true) {
			for (int i = 0; i < que.getNumOfStations(); i++) {
				if(que.getWash(i).isEmpty()) {
					que.addToWash(this, i);
					System.out.println("SUV: " + this.vehicleNum + " start washing");
					try {
						Thread.sleep((long) (que.AvgWash()*1000));
						que.removeFromStation(i);
						System.out.println("SUV: " + this.vehicleNum + " finish washing and added to finshed SUV's ");
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
		return"SUV: " + this.vehicleNum;
	}

}

