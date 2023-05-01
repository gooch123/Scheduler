package SRTF_Scheduler;
import process.Process;
import java.util.*;


public class SRTF_Scheduler {
   PriorityQueue<Process> queue;
   ArrayList<Process> qList;
   int totalWatingtime =0;
   int totalTurnAroundTime=0;
   int processQuantity;
   boolean check = true;
   
   public SRTF_Scheduler(){
      qList = new ArrayList<>();
      queue = new PriorityQueue<>(comp);
   }
   
   private void showGUI(){
      if(check)
         System.out.print("■ ");
      else
         System.out.print("□ ");
   }
   
   private static Comparator<Process> comp = (o1,o2)-> {
      if(o1.restArriveTime == o2.restArriveTime) {
         if(o1.restBurstTime <= o2.restBurstTime)
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
      System.out.println("name : "+p.name
              +" arrivalTime : " +p.arriveTime
              +" burstTime : "+p.burstTime
              +" waitingTime : "+(p.waitingTime>0?p.waitingTime:0)
              + " turnAroundTime : " + (p.waitingTime+p.burstTime));
      totalTurnAroundTime += (p.waitingTime+p.burstTime);
      totalWatingtime += p.waitingTime;
   }
   
   public void schedule() {
      System.out.println("-----------------------SRTF-----------------------");
      processQuantity = qList.size();
      while (!qList.isEmpty()) {
         Process p = qList.get(0);
         System.out.print(p.name + " ");
         qList.remove(0);
         while (p.restBurstTime != 0) {
            Collections.sort(qList, comp);
            if (!qList.isEmpty() && qList.get(0).restArriveTime == 0 && qList.get(0).restBurstTime < p.restBurstTime) { //우선순위 교체
//               System.out.println(p.name + " 에서 " + qList.get(0).name + " 로 선점");
               check = !check;
               qList.add(p);
               break;
            }
            p.restBurstTime--;
            showGUI();
//            System.out.println(p.name + " 버스트 : " + p.restBurstTime);
            if (!qList.isEmpty()) {
               for (Process restP : qList) {
                  if (restP.restArriveTime > 0) restP.restArriveTime--;
                  restP.waitingTime++;
//                  System.out.println(restP.name + " 프로세스 남은 도착시간 : " + restP.restArriveTime + " 남은 버스트 : " + restP.restBurstTime
//                          + "대기시간 : " + restP.waitingTime);
               }
            }
            if (p.restBurstTime == 0) {
//               showProcess(p);
//               if (!qList.isEmpty())
//                  System.out.println(qList.get(0).name + "로 버스트 교체 !!");
               check = !check;
               break;
            }
//          Thread.sleep(200);
         }
         totalTurnAroundTime += (p.waitingTime+p.burstTime);
         totalWatingtime += p.waitingTime;
      }
      System.out.println("\n--------------------------------------------------");
      System.out.println("totalWaitingTime : " + totalWatingtime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\nAverageTurnAroundTime : " + (double) totalTurnAroundTime / processQuantity
              + "\naverageWaitngTime : " + (double) totalWatingtime / processQuantity);
      System.out.println("--------------------------------------------------");
   }
   
}
