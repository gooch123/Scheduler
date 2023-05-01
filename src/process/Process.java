package process;


public class Process{
   public int arriveTime;
   public int waitingTime=0;
   public int burstTime;
   public int restBurstTime;
   public int restArriveTime;
   public int priority;
   public int age = 0;
   public String name;
   
   public Process(String name, int arrivalTime, int burstTime, int priority) {
      this.name = name;
      this.arriveTime = arrivalTime;
      this.burstTime = burstTime;
      this.restBurstTime = burstTime;
      this.waitingTime -= arrivalTime;
      this.restArriveTime = arrivalTime;
      this.priority = priority;
   }
   
}
