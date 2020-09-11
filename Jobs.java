/**
 * Name         : Yap Kai Herng
 * Matric. No   : A0199729A
*/

import java.util.*;

public class Jobs {
    private void run() {
        Scanner sc = new Scanner(System.in);
        int numJobs = sc.nextInt();
        List<Integer> jobs = new LinkedList<>();
        for (int n = 0; n < numJobs; n++) {
            jobs.add(sc.nextInt());
        }
        System.out.println(this.assign(jobs, 3));
        sc.close();
    }

    private long assign(List<Integer> jobs, int numWorkers) {
        long[] workers = new long[numWorkers];
        Arrays.fill(workers, 0);
        return this.assign(jobs,workers);
    }

    private long assign(List<Integer> jobs, long[] workers) {
        if (jobs.isEmpty()) return this.maxTime(workers);
        List<Integer> newJobs = new LinkedList<>(jobs);
        int currJob = newJobs.remove(0);
        long[] assignOne = this.giveTo(workers,1, currJob);
        long[] assignTwo = this.giveTo(workers,2, currJob);
        long[] assignThree = this.giveTo(workers,3, currJob);
        return
        Math.min(Math.min(this.assign(newJobs,assignOne),this.assign(newJobs,
        assignTwo)), this.assign(newJobs, assignThree));
    }

    private long[] giveTo(long[] workers, int selected, int job) {
        long[] newWorkers = new long[workers.length];
        System.arraycopy(workers,0, newWorkers,0, workers.length);
        long currSelectedTime = workers[selected -1];
        newWorkers[selected -1] = currSelectedTime + job;
        return newWorkers;
    }

    private long maxTime(long[] workers) {
        long max = -1;
        for (long n : workers) {
            max = Math.max(n, max);
        }
        return max;
    }

    public static void main(String args[]) {
        Jobs runner = new Jobs();
        runner.run();
    }
}
