package GUI;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
   JButton[] b = new JButton[4];
   JLabel[] setProcess = new JLabel[4];
   JTextField[] tf = new JTextField[4];
   JButton addProcessBtn = new JButton("Add");
   JTextField processArea = new JTextField();
   String result="";
   
   GUI(){
      String[] buttonTitle = {"FCFS","SRTF","Priority","Round Robin"};
      String[] lableTitle = {"name","실행 시간","도착 시간","우선순위"};
      processArea.setBounds(20,180,600,300);
      processArea.setEditable(false);
      add(processArea);
      
      for(int i=0;i<4;i++){
         setProcess[i] = new JLabel(lableTitle[i]);
         setProcess[i].setBounds(20+140*i,20,130,20);
         add(setProcess[i]);
         tf[i] = new JTextField();
         tf[i].setBounds(20+140*i,50,130,30);
         add(tf[i]);
         b[i] = new JButton(buttonTitle[i]);
         b[i].setBounds(20+140*i,120,130,40);
         add(b[i]);
      }
      addProcessBtn.setBounds(580,50,130,30);
      add(addProcessBtn);
      addProcessBtn.addActionListener(this);
      setSize(800,600);
      setLayout(null);
      setVisible(true);
   }
   
   public static void main(String[] args) {
      new GUI();
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      String name = tf[0].getText();
      int burstTime = Integer.parseInt(tf[1].getText());
      int arriveTime = Integer.parseInt(tf[2].getText());
      int priority = Integer.parseInt(tf[3].getText());
      if(e.getSource() == addProcessBtn){
         result += (name + "   |   " +burstTime + "   |   " + arriveTime + "   |   " + priority+ "\n");
         processArea.setText(result);
      }
   }
}
