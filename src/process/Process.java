package process;

import java.util.Comparator;

public class Process{
   public int arriveTime;
   public int waitingTime=0;
   public int burstTime;
   public int restBurstTime;
   public int restArriveTime;
   public String name;
   
   public Process(String name, int arrivalTime, int burstTime) {
      this.name = name;
      this.arriveTime = arrivalTime;
      this.burstTime = burstTime;
      this.restBurstTime = burstTime;
      this.waitingTime -= arrivalTime;
      this.restArriveTime = arriveTime;
   }
   
}
