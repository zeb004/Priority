import java.io.*;
import java.util.*;

class Priority {

    public static void main(String args[]) throws IOException {
		int numproc;
		int arrival[];
		int burst[];
        int priority[];
        arrival = new int[100];
		priority = new int[100];
        burst = new int[100];
        // link list for store the
        // process with details (new state)
        LinkedList queue = new LinkedList();
	    File file = new File("Inputs.txt");
	    Scanner sc = new Scanner(file);
	    numproc= sc.nextInt();
		System.out.print("\n\t Number of processes="+ numproc+ "\n");
		 while(sc.hasNextInt()){
		for(int u=0;u<numproc;u++) {
         arrival[u]=sc.nextInt();
		 priority[u] = sc.nextInt();
		 burst[u] = sc.nextInt();
}
}
        for (int i = 0; i < numproc; i++)

            // insert in new state(queue)
            queue.addLast(new Process(i + 1, arrival[i],
            burst[i], priority[i]));

        FindGantChart obj = new FindGantChart();
        obj.findGc(queue);

    }

}


/// Data Structure
class Process {
	int priorityt;
    int arrivet;
    int burstt;
    int procnum;
    Process(int procnum, int arrivet, int burstt, int priorityt)
    {
        this.procnum = procnum;
        this.priorityt = priorityt;
        this.arrivet = arrivet;
        this.burstt = burstt;
    }
}

/// Gantt chart structure
class GChart {
    // process number, start time, complete time,
    // turn around time, waiting time
    int procnum, stime, ctime, wtime, ttime;
}

// user define comparative method (first arrival first serve,
// if arrival time same then heigh priority first)
class MyComparator implements Comparator {

    public int compare(Object o1, Object o2)
    {

        Process p1 = (Process)o1;
        Process p2 = (Process)o2;
        if (p1.arrivet < p2.arrivet)
            return (-1);

        else if (p1.arrivet == p2.arrivet && p1.priorityt > p2.priorityt)
            return (-1);

        else
            return (1);
    }
}


// class to find Gantt chart
class FindGantChart {
    void findGc(LinkedList queue)
    {

        // initial time = 0
        int time = 0;

        // priority Queue sort data according
        // to arrival time or priority (ready queue)
        TreeSet prique = new TreeSet(new MyComparator());

        // link list for store processes data
        LinkedList result = new LinkedList();

        // process in ready queue from new state queue
        while (queue.size() > 0)
            prique.add((Process)queue.removeFirst());

        Iterator it = prique.iterator();

        // time set to according to first process
        time = ((Process)prique.first()).arrivet;

        // scheduling process
        while (it.hasNext()) {

            // dispatcher dispatch the
            // process ready to running state
            Process obj = (Process)it.next();

            GChart gc1 = new GChart();
            gc1.procnum = obj.procnum;
            gc1.stime = time;
            time += obj.burstt;
            gc1.ctime = time;
            gc1.ttime = gc1.ctime - obj.arrivet;
            gc1.wtime = gc1.ttime - obj.burstt;

            /// store the exxtreted process
            result.add(gc1);
        }

        // create object of output class and call method
        new ResultOutput(result);
    }
}


class ResultOutput {

    ResultOutput(LinkedList result)
    {

        double wavg = 0, tavg = 0;
        int totalprocess = result.size();
        System.out.println("Process_no\tStart_time\t"+
        "Complete_time\tTrun_Around_Time\tWating_Time");

        // dispalay the process details
        while (result.size() > 0) {

            GChart obj = (GChart)result.poll();
            wavg += obj.wtime;
            tavg += obj.ttime;
            System.out.println(obj.procnum + "\t\t" +
            obj.stime + "\t\t" + obj.ctime + "\t\t" +
            obj.ttime + "\t\t\t" + obj.wtime);
        }

        // display the average waiting time
        //and average turn around time
        System.out.println("Average Wating Time is : "
        + (wavg / totalprocess));

        System.out.println("Average Trun Around time is : "
         + (tavg / totalprocess));
    }
}



