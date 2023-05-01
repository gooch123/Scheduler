package FCFS_Scheduler;

import java.util.*;

import process.Process;

public class FCFS_Scheduler{
   PriorityQueue<Process> queue;
   int totalWaitingTime = 0;
   int totalTurnAroundTime = 0;
   int processQuantity;
   ArrayList<Integer> burstList;
   boolean check = true;
   
   public FCFS_Scheduler(){
      queue = new PriorityQueue<>(comp);
      burstList = new ArrayList<>();
   }
   
   private static Comparator<Process> comp = (o1, o2)-> {
      if(o1.arriveTime == o2.arriveTime)
         return 0;
      else {
         if(o1.arriveTime <= o2.arriveTime)
            return -1;
         else
            return 1;
      }
   };
   
   public void addProcess(Process p) {
      queue.add(p);
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
         while (!queue.isEmpty() && queue.peek().restArriveTime > 0){
            for(Process p : queue) {
               p.restArriveTime--;
               p.waitingTime++;
            }
            System.out.print("- ");
         }
         Process p = queue.poll();
         System.out.print(p.name + " ");
         while(p.restBurstTime > 0) {
            p.restBurstTime--;
            showGUI(p);
            for (Process pRest : queue) {
               pRest.waitingTime++;
               if(pRest.restArriveTime > 0)
                  pRest.restArriveTime--;
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
      queue.clear();
   }
   
}
