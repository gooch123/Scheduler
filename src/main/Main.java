package main;
import process.Process;
import FCFS_Scheduler.*;
import RR_Scheduler.*;
import SRTF_Scheduler.*;
import Priority_Scheduler.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Exec {
   Scanner sc = new Scanner(System.in);
   ArrayList<Process> processeList = new ArrayList<>();
   ArrayList<Process> cl = new ArrayList<>();
   Random random = new Random();
   FCFS_Scheduler fcfs = new FCFS_Scheduler();
   Round_Robin_Scheduler rr = new Round_Robin_Scheduler();
   SRTF_Scheduler srtf = new SRTF_Scheduler();
   Priority_Scheduler prio = new Priority_Scheduler();
   int count =0;
   
   Exec(){
      System.out.println("스케줄러 실행\n-----------------------------------------------\n");
      while (true){
         System.out.println("1. 프로세스 추가 2. 랜덤 프로세스 10개 추가 " +
                 "\n3. FCFS 스케줄러 실행 4. SRTF 스케줄러 실행 5. 라운드 로빈 스케줄러 실행 6. 우선순위 스케줄러 실행"+
                 "\n7. 현재 추가된 프로세스 확인 8. 프로세스 비우기 0.종료");
         int selcet = checkInputError();
         if(selcet == 0)
            break;
         switch (selcet){
            case 1 :
               addProcess();
               break;
            case 2:
               randomProcess();
               break;
            case 3:
               fcfsSchedule();
               break;
            case 4:
               srtfSchedule();
               break;
            case 5:
               rrSchedule();
               break;
            case 6:
               prioritySchedule();
               break;
            case 7:
               checkProcess();
               break;
            case 8:
               clearProcess();
               break;
            default:
               System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
               break;
         }
      }
      sc.close();
   }
   
   private int checkInputError(){
      int n =0;
      while (true){
         try {
            n = sc.nextInt();
            sc.nextLine();
            break;
         }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("숫자를 입력해주세요");
         }
      }
      return n;
   }
   
   private void addProcess(){
      String name;
      int arriveTime;
      int burstTime;
      int priority;
      while (true){
         try {
            System.out.print("프로세스 이름 >> ");
            name = sc.next();
            System.out.print("도착 시간 >> ");
            arriveTime = sc.nextInt();
            sc.nextLine();
            System.out.print("실행 시간 >> ");
            burstTime = sc.nextInt();
            sc.nextLine();
            System.out.print("우선순위 >> ");
            priority = sc.nextInt();
            sc.nextLine();
            break;
         }catch (InputMismatchException e){
            sc.nextLine();
            System.out.println("잘못 입력하셨습니다.");
         }
      }
      processeList.add(new Process(name,arriveTime,burstTime,priority));
      count++;
   }
   
   private void randomProcess(){
//      processeList.add(new Process("p0",0,random.nextInt(20),random.nextInt(10)));
      for(int i =0;i<10;i++){
         processeList.add(new Process("p"+ count++,random.nextInt(10),random.nextInt(20)+1, random.nextInt(10)));
      }
      System.out.println("프로세스 10개 랜덤으로 추가 완료");
   }
   
   private void fcfsSchedule(){
      cl = copyProcess();
      for(Process p : cl){
         fcfs.addProcess(p);
      }
      fcfs.schedule();
   }
   
   private void srtfSchedule(){
      cl = copyProcess();
      for(Process p : cl){
         srtf.addProcess(p);
      }
      srtf.schedule();
   }
   
   private void prioritySchedule(){
      cl = copyProcess();
      System.out.print("에이징을 위해 버스트 몇 번당 우선순위를 올릴지 정해주세요 >> ");
      while (true) {
         int aging = checkInputError();
         if(aging > 0){
            prio.setAging(aging);
            break;
         }else
            System.out.println("0보다 커야 합니다. 다시 입력해주세요");
      }
      for(Process p : cl){
         prio.addProcess(p);
      }
      prio.schedule();
   }
   private void rrSchedule(){
      System.out.print("시분할 입력 >> ");
      while (true) {
         int time = checkInputError();
         if(time > 0) {
            rr.setTimeSlice(time);
            break;
         }
         else 
            System.out.println("시분할은 0보다 커야 합니다. 다시 입력해주세요");
      }
      cl = copyProcess();
      for(Process p : cl){
         rr.addProcess(p);
      }
      rr.schedule();
   }
   private void clearProcess(){
      processeList.clear();
      count = 1;
   }
   private void checkProcess(){
      for(Process p : processeList){
         System.out.println("이름 : " + p.name + " 도착 시간 : " + p.arriveTime + " 실행 시간 : " + p.burstTime +
                 " 우선순위 : " + p.priority);
      }
   }
   
   private ArrayList<Process> copyProcess(){
      ArrayList<Process> list = new ArrayList<>();
      for(Process p : processeList){
         list.add(new Process(p.name,p.arriveTime,p.burstTime,p.priority));
      }
      return list;
   }
   
}

public class Main {
   public static void main(String []args) {
      new Exec();
   }
}
