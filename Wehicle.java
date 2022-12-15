
public abstract class Wehicle extends Thread {
	
	protected int wehicleId;
	protected WehicleWasher que;
	
	public Wehicle() {
		this.wehicleId = -1;
	}
	public abstract void run() ;
	
	public boolean isEmpty() {
		if(this.wehicleId == -1 || this == null)
			return true;
		return false;
	}
	

	public int getWehicleId() {return wehicleId;}
	public void setWheilcleId(int i) {this.wehicleId = i;} 
	public abstract String toString();
}
