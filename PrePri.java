import java.util.*;
import java.io.*;


public class PrePri {
    public static void main(String args[])throws IOException {
            Scanner s = new Scanner(System.in);

            int x;
            int numproc;
            int arrival[];
            int priority[];
            int burst[];
            int w[];
            int t[];
            int awt;
            int atat;
            int i;
            int burstmod[];
            int runtime=0;
            int pnum=0;
            int temo=0;
	        String stop=null;
            arrival = new int[100];
            priority = new int[100];
            burst = new int[100];
            w = new int[100];
            t = new int[100];
            burstmod=new int[100];
			int ncurr=0;
			int time2=0;


	//get dat info
	File file = new File("Inputs.txt");
	Scanner sc = new Scanner(file);
   numproc= sc.nextInt();
   System.out.print("\n\t Number of processes="+ numproc+ "\n");
    while(sc.hasNextInt()){
		for(i=0;i<numproc;i++)
		    {

		       arrival[i]=sc.nextInt();
		       priority[i] = sc.nextInt();
		      burst[i] = sc.nextInt();
		      temo=burst[i];
		      burstmod[i]=temo;

		      System.out.print("\nProcess["+(i+1)+"]:" +" "+"Arrive:"+" "+arrival[i]+" "+"Priority:"+" "+priority[i]+" "+"Burst:"+" "+burst[i]+"\n");

	  }

    }
//calculate run time:
for(int k=0;k<numproc;k++) {
	runtime+=burst[k];
}
System.out.println("RUNTIME OF PGM:"+runtime+"\n");
int start=0;
int time=1;
if(start!=arrival[0]) {
		System.out.println("STALL, TIME:"+" "+time);
		start++;
		time++;
	}
//calculate arrival times with respect to CPU Time
for(int u=runtime-start;u>0;u--) {
	//check current CPU time, if not same as arrival, stall
	int currtime=runtime-(u-1);
	int action=burst[pnum];
	System.out.println("Process"+" "+(pnum+1)+" "+"arrive at"+" "+arrival[pnum]);
	System.out.println("Work for Process arrival at:"+arrival[pnum]+" "+"TIME:"+currtime);
    action--;
    burst[pnum]--;
	//If equal to arrival, increment pnum for next process
	if(burst[pnum]==0||(priority[pnum+1]<priority[pnum]&&currtime==arrival[pnum+1])){
		if(burst[pnum]==0) {
			System.out.println("PROCESS"+" "+(pnum+1)+"FINISHED @:"+currtime);
		System.out.println("TURNAROUND TIME:"+(currtime-arrival[pnum]));
	}
		pnum++;
		burst[pnum]--;
       action=burst[pnum];
}
if(pnum>numproc-1) {
	time2=currtime;
	//System.out.println("TIME PASSED"+time2);
	break;
}

}
//sort remaining processes that were preempted by priority, if they still have burst values left
//sorting on the basis of priority
  for(i=0;i<numproc-1;i++) {
     for(int j=i+1;j<numproc;j++) {
       if(priority[i]<priority[j]) {
     x=priority[i];
     priority[i]=priority[j];
     priority[j]=x;
     x=burst[i];
     burst[i]=burst[j];
     burst[j]=x;
     x=arrival[i];
     arrival[i]=arrival[j];
     arrival[j]=x;
      }
   }
}

pnum=0;
for(int u=runtime-time2;u>0;u--) {
	//check current CPU time, if not same as arrival, stall
	int currtime=runtime-(u-1);
	int action=burst[pnum];
	//if no burst value left, discard
	if(burst[pnum]==0) {
		pnum++;
	}else
	System.out.println("Process"+" "+(pnum+1)+" "+"arrive at"+" "+arrival[pnum]);
	System.out.println("Work for Process arrival at:"+arrival[pnum]+" "+"TIME:"+currtime);
    action--;
    burst[pnum]--;
	//print turnaround time for 2nd round
	if(burst[pnum]==-1){
		System.out.println("PROCESS"+" "+(pnum+1)+"FINISHED @:"+currtime);
		System.out.println("TURNAROUND TIME:"+(currtime-arrival[pnum]));
		pnum++;

}
//end condition
if(pnum>numproc-1) {
	break;
}

}
//calculations incorrect
w[0]=0;
awt=0;
t[0]=burstmod[0];
atat=t[0];
for(i=1;i<numproc;i++)
 {
   w[i]=t[i-1];
   awt+=w[i];
   t[i]=w[i]+burstmod[i];
   atat+=t[i];
 }

//Displaying the process
//Calculation not correct
/*
  System.out.print("\n\nProcess \t Arrival \t Burst Time \t Wait Time \t Turn Around Time   Priority \n");
for(i=0;i<numproc;i++)
  System.out.print("\n"+(i+1)+"\t\t      "+arrival[i]+"\t\t   "+burstmod[i]+"\t\t     "+w[i]+"\t\t     "+t[i]+"\t\t     "+priority[i]+"\n");
awt/=numproc;
atat/=numproc;
  System.out.print("\n Average Wait Time : "+awt);
  System.out.print("\n Average Turn Around Time : "+atat+"\n");
*/
        }


}