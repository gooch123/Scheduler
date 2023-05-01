package Priority_Scheduler;
import java.util.*;
import process.Process;

public class Priority_Scheduler {
   ArrayList<Process> qList;
   boolean check = true;
   int totalWaitingTime = 0;
   int totalTurnAroundTime = 0;
   int processQuantity;
   int aging;
   
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
   public void setAging(int n){this.aging =n;}
   
   public void addProcess(Process p){
      qList.add(p);
   }
   
   public void schedule() {
      System.out.println("\n---------------------Priority---------------------");
      Collections.sort(qList,comp);
      processQuantity = qList.size();
      while (!qList.isEmpty()){
         while (qList.get(0).restArriveTime > 0){
            for(Process p : qList) {
               p.restArriveTime--;
               p.waitingTime++;
            }
            System.out.print("- ");
         }
         Process p = qList.get(0);
         qList.remove(0);
         System.out.print(p.name + " ");
         while (p.restBurstTime != 0){
            Collections.sort(qList,comp);
            if(!qList.isEmpty() && qList.get(0).restArriveTime == 0 && qList.get(0).priority > p.priority){ //우선순위 교체
               qList.add(p);
               check = !check;
               break;
            }
            p.restBurstTime--;
            for(Process pR : qList) {
               pR.age++;
               if(pR.age >= aging)
                  pR.priority++;
            }
            showGUI();
            if(!qList.isEmpty()) {
               for (Process restP : qList) {
                  if (restP.restArriveTime > 0) restP.restArriveTime--;
                  restP.waitingTime++;
               }
            }
            if(p.restBurstTime == 0){
               totalTurnAroundTime += p.waitingTime + p.burstTime;
               totalWaitingTime += p.waitingTime;
               check = !check;
               break;
            }
         }
      }
      
      System.out.println("\n-----------------------------------------------");
      System.out.println("totalWaitingTime : " + totalWaitingTime
              + "\ntotalTurnAroundTime : " + totalTurnAroundTime
              + "\naverageTurnAroundTime : " + (double)totalTurnAroundTime/processQuantity
              + "\naverageWaitngTime : " +(double)totalWaitingTime/processQuantity);
      System.out.println("------------------------------------------------");
      qList.clear();
   }
}
