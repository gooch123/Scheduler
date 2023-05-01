package Priority_Scheduler;
import java.util.*;
import process.Process;

public class Priority_Scheduler {
   ArrayList<Process> qList;
   boolean check = true;
   int totalWaitingTime = 0;
   int totalTurnAroundTime = 0;
   int processQuantity;
   
   public Priority_Scheduler(){
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
   
   private void showGUI(){
      if(check)
         System.out.print("■ ");
      else
         System.out.print("□ ");
   }
   
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
   
   public void schedule() {
      System.out.println("\n---------------------Priority---------------------");
      processQuantity = qList.size();
      while (!qList.isEmpty()){
         Process p = qList.get(0);
         qList.remove(0);
         System.out.print(p.name + " ");
         while (p.restBurstTime != 0){
            Collections.sort(qList,comp);
            if(!qList.isEmpty() && qList.get(0).restArriveTime == 0 && qList.get(0).priority > p.priority){ //우선순위 교체
//               System.out.println(p.name + " 에서 " + qList.get(0).name + " 로 선점");
               qList.add(p);
               check = !check;
               break;
            }
            p.restBurstTime--;
            showGUI();
//            System.out.println(p.name + " 버스트 : " + p.restBurstTime + " 우선순위 : " + p.priority);
            if(!qList.isEmpty()) {
               for (Process restP : qList) {
                  if (restP.restArriveTime > 0) restP.restArriveTime--;
                  restP.waitingTime++;
//                  System.out.println(restP.name + " 프로세스 남은 도착시간 : " + restP.restArriveTime + " 남은 버스트 : " + restP.restBurstTime
//                  + " 우선순위 : " + restP.priority);
               }
            }
            if(p.restBurstTime == 0){
               totalTurnAroundTime += p.waitingTime + p.burstTime;
               totalWaitingTime += p.waitingTime;
//               showProcess(p);
//               if(!qList.isEmpty())
//                  System.out.println(qList.get(0).name + "로 버스트 교체 !!");
               check = !check;
               break;
            }
//          Thread.sleep(200);
         }
      }
      
      System.out.println("\n-----------------------------------------------");
      System.out.println("totalWaitingTime : " + totalWaitingTime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\naverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity
              + "\naverageWaitngTime : " +(double)totalWaitingTime/processQuantity);
      System.out.println("------------------------------------------------");
   }
}
