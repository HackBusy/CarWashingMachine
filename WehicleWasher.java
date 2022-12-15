/**********************************
 * 
 * @author Avidan Mor 318231164
 * @author Amir Melikson 322526898
 * 
 **********************************/

import java.lang.Math;
import java.util.Random;
public class WehicleWasher {

	private Car[] carWashed;
	private SUV[] suvWashed;
	private Trucks[] truckWashed;
	private MiniBus[] minibusWashed;
	private Wehicle[] waiting, wash;
	private int carNum;
	private double avgWaitIn, avgWashIn;
	
	public WehicleWasher(int s, int vehAmunt, double aW, double aS) {
		this.wash = new Wehicle[s];
		this.waiting = new Wehicle[vehAmunt];	
		this.carNum = 0;
		this.avgWaitIn = aW;
		this.avgWashIn = aS;
		System.out.println(wash.length);
		addCarsToQue();
	}
	
//	public double getAvgWait() {return avgWaitIn;}
//
//	public double getAvgWash() {return avgWash;}

	public double AvgWait() {
		Random r = new Random();
		double uni = r.nextFloat();
		return (-Math.log(uni)) / avgWaitIn;
		}

	public double AvgWash() {
		Random r = new Random();
		double uni = r.nextFloat();
		return (-Math.log(uni)) /avgWashIn;
	}

	public synchronized void addCar () {
		this.waiting[carNum] = new Car(this, carNum + 1);
		System.out.println("A new Car has join the car Wash: " + (carNum + 1));
		this.waiting[carNum].start();
		this.carNum++;
	}
	public synchronized void addTrucks() {
		this.waiting[carNum] = new Trucks(this, carNum + 1);
		System.out.println("A new Truck has join the car Wash: " + (carNum + 1));
		this.waiting[carNum].start();
		this.carNum++;
	}
	public synchronized void addMiniBus() {
		this.waiting[carNum] = new MiniBus(this, carNum + 1);
		System.out.println("A new MiniBus has join the car Wash: " + (carNum + 1));
		this.waiting[carNum].start();
		this.carNum++;
	}
	public synchronized void addSUV() {
		this.waiting[carNum] = new SUV(this, carNum);
		System.out.println("A new SUV has join the car Wash: " + (carNum + 1));
		this.waiting[carNum].start();
		this.carNum++;
	}
	
	public synchronized Wehicle getWash(int index) { return this.waiting[index];}
	public int getNumOfStations() {return this.wash.length;}
	
	public synchronized void addToWash(Wehicle w, int index) {
		if (w instanceof Car)
			this.wash[index] = new Car(w.getWehicleId());
		if (w instanceof SUV)
			this.wash[index] = new SUV(w.getWehicleId());
		if (w instanceof Trucks)
			this.wash[index] = new Trucks(w.getWehicleId());
		if (w instanceof MiniBus)
			this.wash[index] = new MiniBus(w.getWehicleId());
	}
	
	public synchronized void finishWash(Wehicle w) {
		if (w instanceof Car) {
			Car[] temp = new Car[this.carWashed.length];
			for (int i = 0; i < temp.length; i++)
				temp[i] = this.carWashed[i];
			this.carWashed = new Car[temp.length + 1];
			for (int i = 0; i < temp.length; i++)
				this.carWashed[i] = temp[i];
			this.carWashed[temp.length] = new Car(w.getWehicleId());
		}
		if (w instanceof SUV) {
			SUV[] temp = new SUV[this.suvWashed.length];
			for (int i = 0; i < temp.length; i++)
				temp[i] = this.suvWashed[i];
			this.suvWashed = new SUV[temp.length + 1];
			for (int i = 0; i < temp.length; i++)
				this.suvWashed[i] = temp[i];
			this.suvWashed[temp.length] = new SUV(w.getWehicleId());
		}
		if (w instanceof Trucks) {
			Trucks[] temp = new Trucks[this.truckWashed.length];
			for (int i = 0; i < temp.length; i++)
				temp[i] = this.truckWashed[i];
			this.truckWashed = new Trucks[temp.length + 1];
			for (int i = 0; i < temp.length; i++)
				this.truckWashed[i] = temp[i];
			this.truckWashed[temp.length] = new Trucks(w.getWehicleId());
		}
		if (w instanceof MiniBus) {
			MiniBus[] temp = new MiniBus[this.minibusWashed.length];
			for (int i = 0; i < temp.length; i++)
				temp[i] = this.minibusWashed[i];
			this.minibusWashed = new MiniBus[temp.length + 1];
			for (int i = 0; i < temp.length; i++)
				this.minibusWashed[i] = temp[i];
			this.minibusWashed[temp.length] = new MiniBus(w.getWehicleId());
		}		
	}
	
	public synchronized void removeFromStation(int i) {
		if (this.wash[i].isEmpty())
			return;
		this.wash[i].setWheilcleId(-1);
	}
	
	public synchronized void addCarsToQue() {
		Random gen = new Random();
		for (int i = 0; i < waiting.length; i++) {
			//System.out.println(carNum);
			while (true){
				try {
					Thread.sleep((long) (AvgWait() * 1000));
					int choise = gen.nextInt(4);
					switch(choise) {
					case 0 :
						addCar();
						break;
					case 1:
						addSUV();
						break;
					case 2:
						addTrucks();
						break;
					case 3:
						addMiniBus();
						break;
					default:
						throw new Exception("");
					}
					break;
				}catch (Exception e) {
					System.out.println("error accured");
				}
			}
		}
	}

}
