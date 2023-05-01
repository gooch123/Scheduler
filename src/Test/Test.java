package Test;
import java.util.*;

class Number{
   String name;
   int num;
   int time;
   
   Number(String name, int num, int time){
      this.name = name;
      this.num = num;
      this.time = time;
   }
}

public class Test {
   public static Comparator<Number> comp = (o1,o2) -> {
      if(o1.num == o2.num){
         if(o1.time >= o2.time)
            return 1;
         else
            return -1;
      }else{
         if(o1.num > o2.num)
            return 1;
         else
            return -1;
      }
   };
   
   public static void main(String args[]){
      Random random = new Random();
      ArrayList<Number> list = new ArrayList<>();
//      PriorityQueue<Number> queue = new PriorityQueue<Number>(new Comparator<Number>() {
//         @Override
//         public int compare(Number o1, Number o2) {
//            if(o1.num == o2.num){
//               if(o1.time >= o2.time)
//                  return 1;
//               else
//                  return -1;
//            }else{
//               if(o1.num > o2.num)
//                  return 1;
//               else
//                  return -1;
//            }
//         }
//      });
//      for(int i=0;i<20;i++){
//         queue.offer(new Number("case " + i,random.nextInt(10),random.nextInt(5)));
//      }
//      while (!queue.isEmpty()){
//         Number n = queue.poll();
//         System.out.println(n.name + " " + n.num + " "  +n.time);
//      }
      for(int i=0;i<20;i++){
         list.add(new Number("case " + i,random.nextInt(10),random.nextInt(5)));
      }
      Collections.sort(list,comp);
      for(Number n : list)
         System.out.println(n.name + " " + n.num + " "  +n.time);
   }
}
