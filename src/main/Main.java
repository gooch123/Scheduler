package main;
import process.Process;
import FCFS_Scheduler.*;
import RR_Scheduler.*;
import SRTF_Scheduler.*;
import Priority_Scheduler.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Main {
   public static void main(String []args) throws InterruptedException {
      FCFS_Scheduler fcfs = new FCFS_Scheduler();
      Round_Robin_Scheduler rr = new Round_Robin_Scheduler(10);
      SRTF_Scheduler srtf = new SRTF_Scheduler();
      Priority_Scheduler prio = new Priority_Scheduler();
      Random random = new Random();
//    도착시간이 다 0일 때
//      for(int i =0;i<5;i++){
//         fcfs.addProcess(new Process("p"+i,0,random.nextInt(100)));
//         rr.addProcess(new Process("p"+i,0,random.nextInt(100)));
//         srtf.addProcess(new Process("p"+i,0,random.nextInt(100)));
//      }
//      fcfs.schedule();
//      rr.schedule();
//      srtf.schedule();
      //도착시간이 다 다를 때
//      int[] arriveTime = new int[10];
//      for(int i=0;i<10;i++){
//         arriveTime[i] = random.nextInt(10);
//      }
//      Arrays.sort(arriveTime);
//      //처음 프로세스 도착
//      fcfs.addProcess(new Process("p0",0,random.nextInt(100),random.nextInt(5)));
//      rr.addProcess(new Process("p0",0,random.nextInt(100),random.nextInt(5)));
//      srtf.addProcess(new Process("p0",0, random.nextInt(100),random.nextInt(5)));
//      prio.addProcess(new Process("p0",0, random.nextInt(100),random.nextInt(5)));
//      for(int i =0;i<9;i++){
//         fcfs.addProcess(new Process("p"+i,arriveTime[i],random.nextInt(100),random.nextInt(5)));
//         rr.addProcess(new Process("p"+i,arriveTime[i],random.nextInt(100),random.nextInt(5)));
//         srtf.addProcess(new Process("p"+i,arriveTime[i], random.nextInt(100),random.nextInt(5)));
//         prio.addProcess(new Process("p"+i,arriveTime[i], random.nextInt(15),random.nextInt(5)));
//      }
//      fcfs.schedule();
//      rr.schedule();
//      srtf.schedule();
//      prio.schedule();
      prio.addProcess(new Process("p1",0,10,3));
      prio.addProcess(new Process("p2",1,28,2));
      prio.addProcess(new Process("p3",2,6,4));
      prio.addProcess(new Process("p4",2,4,1));
      prio.addProcess(new Process("p5",4,14,2));
      prio.schedule();
   }
   static Comparator<Process> comp= (a,b) -> a.arriveTime-b.arriveTime;
}
