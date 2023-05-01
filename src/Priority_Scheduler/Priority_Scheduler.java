package Priority_Scheduler;
import java.util.*;
import process.Process;

import javax.sound.sampled.Port;

public class Priority_Scheduler {
   PriorityQueue<Process> queue;
   ArrayList<Process> qList;
   
   int totalWaitingTime = 0;
   int totalTurnAroundTime = 0;
   int processQuantity;
   
   public Priority_Scheduler(){
      queue = new PriorityQueue<>(comp);
      qList = new ArrayList<>();
   }
   
   private static Comparator<Process> comp = (o1,o2)-> {
      if(o1.restArriveTime == o2.restArriveTime) {
         if(o1.priority >= o2.priority)
            return -1;
         else
            return 1;
      }
      else{
         if(o1.restArriveTime > o2.restArriveTime)
            return 1;
         else
            return -1;
      }
   };
   
   public void addProcess(Process p){
      qList.add(p);
   }
   
   private void showProcess(Process p){
      totalTurnAroundTime += p.waitingTime + p.burstTime;
      totalWaitingTime += p.waitingTime;
      System.out.println("name : "+p.name+
              " arrivalTime : " +p.arriveTime+
              " burstTime : "+p.burstTime+
              " waitingTime : "+(p.waitingTime>0?p.waitingTime:0) +
              " turnAroundTime : " + (p.waitingTime+p.burstTime) +
              " priority : " + p.priority);
   }
   
   public void schedule() throws InterruptedException {
      while (!qList.isEmpty()){
         Process scheduleP = qList.get(0);
         qList.remove(0);
         while (scheduleP.restBurstTime != 0){
            Collections.sort(qList,comp);
            if(!qList.isEmpty() && qList.get(0).restArriveTime == 0 && qList.get(0).priority > scheduleP.priority){ //우선순위 교체
               System.out.println(scheduleP.name + " 에서 " + qList.get(0).name + " 로 선점");
               qList.add(scheduleP);
               break;
            }
            scheduleP.restBurstTime--;
            System.out.println(scheduleP.name + " 버스트 : " + scheduleP.restBurstTime + " 우선순위 : " + scheduleP.priority);
            if(!qList.isEmpty()) {
               for (Process restP : qList) {
                  if (restP.restArriveTime > 0) restP.restArriveTime--;
                  restP.waitingTime++;
                  System.out.println(restP.name + " 프로세스 남은 도착시간 : " + restP.restArriveTime + " 남은 버스트 : " + restP.restBurstTime
                  + " 우선순위 : " + restP.priority);
               }
            }
            if(scheduleP.restBurstTime == 0){
               showProcess(scheduleP);
               if(!qList.isEmpty())
                  System.out.println(qList.get(0).name + "로 버스트 교체 !!");
               break;
            }
//          Thread.sleep(200);
         }
      }
      
      System.out.println("---------------------Priority---------------------");
      System.out.println("totalWaitingTime : " + totalWaitingTime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\naverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity
              + "\naverageWaitngTime : " +(double)totalWaitingTime/processQuantity);
      System.out.println("------------------------------------------------");
   }
}
