package SRTF_Scheduler;
import process.Process;
import java.util.*;


public class SRTF_Scheduler {
   ArrayList<Process> qList;
   int totalWatingtime =0;
   int totalTurnAroundTime=0;
   int processQuantity;
   boolean check = true;
   
   public SRTF_Scheduler(){
      qList = new ArrayList<>();
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
   
   public void schedule() {
      Collections.sort(qList, comp);
      System.out.println("-----------------------SRTF-----------------------");
      processQuantity = qList.size();
      while (!qList.isEmpty()) {
         while (qList.get(0).restArriveTime > 0){
            for(Process p : qList) {
               p.restArriveTime--;
               p.waitingTime++;
            }
            System.out.print("- ");
         }
         Process p = qList.get(0);
         System.out.print(p.name + " ");
         qList.remove(0);
         while (p.restBurstTime != 0) {
            Collections.sort(qList, comp);
            if (!qList.isEmpty() && qList.get(0).restArriveTime == 0 && qList.get(0).restBurstTime < p.restBurstTime) { //우선순위 교체
               check = !check;
               qList.add(p);
               break;
            }
            p.restBurstTime--;
            showGUI();
            if (!qList.isEmpty()) {
               for (Process restP : qList) {
                  if (restP.restArriveTime > 0) restP.restArriveTime--;
                  restP.waitingTime++;
               }
            }
            if (p.restBurstTime == 0) {
               totalTurnAroundTime += (p.waitingTime+p.burstTime);
               totalWatingtime += p.waitingTime;
               check = !check;
               break;
            }
         }
         
      }
      System.out.println("\n--------------------------------------------------");
      System.out.println("totalWaitingTime : " + totalWatingtime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\nAverageTurnAroundTime : " + (double) totalTurnAroundTime / processQuantity
              + "\naverageWaitngTime : " + (double) totalWatingtime / processQuantity);
      System.out.println("--------------------------------------------------");
      qList.clear();
   }
   
}
