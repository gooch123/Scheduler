package FCFS_Scheduler;

import java.util.LinkedList;
import java.util.Queue;
import process.Process;

public class FCFS_Scheduler{
   Queue<Process> queue;
   int totalWaitingTime = 0;
   int totalTurnAroundTime = 0;
   int processQuantity;
   
   public FCFS_Scheduler(){
      queue = new LinkedList<Process>();
   }
   
   public void addProcess(Process p) {
      queue.add(p);
   }
   
   public void schedule(){
      processQuantity = queue.size();
      while(!queue.isEmpty()) {
         Process p = queue.poll();
         while(p.restBurstTime > 0) {
            p.restBurstTime--;
            for(Process pRest : queue) {
               pRest.waitingTime++;
            }
         }
         totalTurnAroundTime += p.waitingTime + p.burstTime;
         totalWaitingTime += p.waitingTime;
         System.out.println("name : "+p.name+
                 " arrivalTime : " +p.arriveTime+
                 " burstTime : "+p.burstTime+
                 " waitingTime : "+(p.waitingTime>0?p.waitingTime:0) +
                 " turnAroundTime : " + (p.waitingTime+p.burstTime));
      }
      System.out.println("----------------FCFS-------------------");
      System.out.println("TotalWaitingTime : " + totalWaitingTime);
      System.out.println("TotalTurnAroundTime : " + totalTurnAroundTime);
      System.out.println("AverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity);
      System.out.println("AverageWaitingTime : " + (double)totalWaitingTime/processQuantity);
      System.out.println("---------------------------------------");
   }
   
}
