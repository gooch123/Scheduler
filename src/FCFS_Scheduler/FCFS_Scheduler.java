package FCFS_Scheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import process.Process;

public class FCFS_Scheduler{
   Queue<Process> queue;
   int totalWaitingTime = 0;
   int totalTurnAroundTime = 0;
   int processQuantity;
   ArrayList<Integer> burstList;
   boolean check = true;
   
   public FCFS_Scheduler(){
      queue = new LinkedList<Process>();
      burstList = new ArrayList<>();
   }
   
   public void addProcess(Process p) {
      queue.add(p);
   }
   
   private void showPorcess(Process p) {
      System.out.println("name : "+p.name+
              " arrivalTime : " +p.arriveTime+
              " burstTime : "+p.burstTime+
              " waitingTime : "+(p.waitingTime>0?p.waitingTime:0) +
              " turnAroundTime : " + (p.waitingTime+p.burstTime));
   }
   
   private void showGUI(Process p){
      if(check)
         System.out.print("■ ");
      else
         System.out.print("□ ");
   }
   
   public void schedule(){
      System.out.println("\n----------------FCFS-------------------");
      processQuantity = queue.size();
      while(!queue.isEmpty()) {
         Process p = queue.poll();
         System.out.print(p.name + " ");
         int burstCount=0;
         while(p.restBurstTime > 0) {
            p.restBurstTime--;
            burstCount++;
            showGUI(p);
            for (Process pRest : queue) {
               pRest.waitingTime++;
            }
         }
         check = !check;
         totalTurnAroundTime += p.waitingTime + p.burstTime;
         totalWaitingTime += p.waitingTime;
      }
      System.out.println("\n---------------------------------------");
      System.out.println("TotalWaitingTime : " + totalWaitingTime);
      System.out.println("TotalTurnAroundTime : " + totalTurnAroundTime);
      System.out.println("AverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity);
      System.out.println("AverageWaitingTime : " + (double)totalWaitingTime/processQuantity);
      System.out.println("---------------------------------------");
   }
   
}
