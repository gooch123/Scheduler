package SRTF_Scheduler;
import process.Process;
import java.util.*;


public class SRTF_Scheduler {
   PriorityQueue<Process> queue;
   int totalWatingtime =0;
   int totalTurnAroundTime=0;
   int processQuantity;
   
   public SRTF_Scheduler(){
      queue = new PriorityQueue<>(new Comparator<Process>() {
         @Override
         public int compare(Process o1, Process o2) {
            if(o1.burstTime == o2.burstTime){
               return o1.burstTime -o2.burstTime;
            }else{
               if(o1.burstTime > o2.burstTime)
                  return 1;
               else
                  return -1;
            }
         }
      });
   }
   
   public void addProcess(Process p){
      queue.offer(p);
   }
   
   public void schedule(){
      processQuantity = queue.size();
      while (!queue.isEmpty()) {
         Process p = queue.poll();
         while (p.restBurstTime > 0){
            p.restBurstTime--;
            for(Process pRest:queue)
               pRest.waitingTime++;
         }
         System.out.println("name : "+p.name
                 +" arrivalTime : " +p.arriveTime
                 +" burstTime : "+p.burstTime
                 +" waitingTime : "+(p.waitingTime>0?p.waitingTime:0)
                 + " turnAroundTime : " + (p.waitingTime+p.burstTime));
         totalTurnAroundTime += (p.waitingTime+p.burstTime);
         totalWatingtime += p.waitingTime;
      }
      System.out.println("-----------------------SRTF-----------------------");
      System.out.println("totalWaitingTime : " + totalWatingtime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\nAverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity
              + "\naverageWaitngTime : " +(double)totalWatingtime/processQuantity);
      System.out.println("--------------------------------------------------");
   }
   
}
