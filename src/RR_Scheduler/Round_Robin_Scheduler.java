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
   boolean check=true;
   
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
   
   private void showGUI(Process p){
      
      if(check)
         System.out.print("■ ");
      else
         System.out.print("□ ");
   }
   
   public void schedule(){
      System.out.println("\n-----------------------RR-----------------------");
      processQuantity = queue.size();
      int restTimeSlice=timeSlice;
      while (!queue.isEmpty()){
         Process p = queue.poll();
         System.out.print(p.name + " ");
         while(true){
            if(restTimeSlice > 0 && p.restBurstTime >0){
               restTimeSlice--;
               p.restBurstTime--;
               showGUI(p);
               for(Process pRest : queue)
                  pRest.waitingTime++;
            } else if (restTimeSlice > 0 && p.restBurstTime == 0) {
               //processStateOut(p);
               check = !check;
               break;
            } else if (restTimeSlice == 0 && p.restBurstTime > 0) {
               queue.add(p);
               if(queue.size() != 1)
                  check = !check;
               restTimeSlice = timeSlice;
               break;
            } else if(restTimeSlice == 0 && p.restBurstTime ==0){
               //processStateOut(p);
               check = !check;
               restTimeSlice = timeSlice;
               break;
            }
         }
         totalWaitingTime += p.waitingTime;
         totalTurnAroundTime += (p.waitingTime+p.burstTime);
      }
      System.out.println("\n------------------------------------------------");
      System.out.println("totalWaitingTime : " + totalWaitingTime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\nAverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity
              + "\naverageWaitngTime : " +(double)totalWaitingTime/processQuantity);
      System.out.println("------------------------------------------------");
      
   }
   
}
