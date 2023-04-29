package Test;
import java.util.*;

class Number{
   String name;
   int num;
   
   Number(String name, int num){
      this.name = name;
      this.num = num;
   }
}


public class Test {
   public static void main(String args[]){
      Random random = new Random();
      PriorityQueue<Number> queue = new PriorityQueue<Number>(new Comparator<Number>() {
         @Override
         public int compare(Number o1, Number o2) {
            if(o1.num == o2.num){
               return o1.num - o2.num;
            }else{
               if(o1.num > o2.num)
                  return 1;
               else
                  return -1;
            }
         }
      });
      for(int i=0;i<20;i++){
         queue.offer(new Number("case " + i,random.nextInt(100)));
      }
      while (!queue.isEmpty()){
         Number n = queue.poll();
         System.out.println(n.name + " " + n.num);
      }
   }
}
