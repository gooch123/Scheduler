package RR_Scheduler;

import java.util.*;

import process.Process;

public class Round_Robin_Scheduler{
   ArrayList<Process> queue;
   int timeSlice;
   int totalWaitingTime =0;
   int totalTurnAroundTime =0;
   int processQuantity;
   boolean check=true;
   
   public Round_Robin_Scheduler(){
      queue = new ArrayList<>();
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
   
   public void addProcess(Process p){
      queue.add(p);
   }
   
   public void setTimeSlice(int time){
      this.timeSlice = time;
   }
   
   private void showGUI(){
      if(check)
         System.out.print("■ ");
      else
         System.out.print("□ ");
   }
   
   public void schedule(){
      Collections.sort(queue,comp);
      System.out.println("\n-----------------------RR-----------------------");
      processQuantity = queue.size();
      int restTimeSlice=timeSlice;
      while (!queue.isEmpty()){
         while (queue.get(0).restArriveTime > 0){
            for(Process p : queue) {
               p.restArriveTime--;
               p.waitingTime++;
            }
            System.out.print("- ");
         }
         Process p = queue.get(0);
         queue.remove(0);
         System.out.print(p.name + " ");
         while(true){
            if(restTimeSlice > 0 && p.restBurstTime >0){
               restTimeSlice--;
               p.restBurstTime--;
               showGUI();
               for(Process pRest : queue) {
                  pRest.waitingTime++;
                  if(pRest.restArriveTime > 0)
                     pRest.restArriveTime--;
               }
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
      queue.clear();
   }
   
}
