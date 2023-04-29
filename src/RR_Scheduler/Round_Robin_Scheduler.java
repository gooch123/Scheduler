package RR_Scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import process.Process;

public class Round_Robin_Scheduler{
   Queue<Process> queue;
   ArrayList<Process> jobQ;
   int timeSlice;
   int totalWaitingTime =0;
   int totalTurnAroundTime =0;
   int processQuantity;
   
   public Round_Robin_Scheduler(int timeSlice){
      this.timeSlice = timeSlice;
      queue = new LinkedList<Process>();
      jobQ = new ArrayList<Process>();
   }
   private void processStateOut(Process p){
      System.out.println("name : "+p.name + " arrivalTIme : "+ p.arriveTime +" burstTime : "+p.burstTime+
              " waitingTime : "+p.waitingTime +" turnAroundTime : "+(p.waitingTime+p.burstTime));
      totalWaitingTime += p.waitingTime;
      totalTurnAroundTime += (p.waitingTime+p.burstTime);
   }
   
   public void addProcess(Process p){
      queue.add(p);
   }
   
   public void addJopQueue(Process p) {
   
   }
   
   public void schedule(){
      processQuantity = queue.size();
      int restTimeSlice=timeSlice;
      while (!queue.isEmpty()){
         Process p = queue.poll();
         while(true){
            if(restTimeSlice > 0 && p.restBurstTime >0){
               restTimeSlice--;
               p.restBurstTime--;
               for(Process pRest : queue)
                  pRest.waitingTime++;
            } else if (restTimeSlice > 0 && p.restBurstTime == 0) {
               processStateOut(p);
               break;
            } else if (restTimeSlice == 0 && p.restBurstTime > 0) {
               queue.add(p);
               restTimeSlice = timeSlice;
               break;
            } else if(restTimeSlice == 0 && p.restBurstTime ==0){
               processStateOut(p);
               restTimeSlice = timeSlice;
               break;
            }
         }
      }
      System.out.println("-----------------------RR-----------------------");
      System.out.println("totalWaitingTime : " + totalWaitingTime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\nAverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity
              + "\naverageWaitngTime : " +(double)totalWaitingTime/processQuantity);
      System.out.println("------------------------------------------------");
      
   }
   
}
