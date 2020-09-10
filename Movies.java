/**
 * Name         : Yap Kai Herng
 * Matric. No   : A0199729A
*/

import java.util.*;

public class Movies {
    
    private void run() {
        Scanner sc = new Scanner(System.in);
        int numMov = sc.nextInt();
        int maxTake = sc.nextInt();
        LinkedList<Movie> movies = new LinkedList<>();
        for (int n = 0; n < numMov; n++) {
            movies.add(new Movie(sc.nextInt(), sc.nextInt(),
            sc.nextInt()));
        }
        System.out.println(this.select(movies, maxTake));
        sc.close();
    }
    

    //precond: see select() below. This method assumes no movies are currently being selected.
    //postcond: see select() below
    private int select(LinkedList<Movie> avail, int maxTake) {
        return this.select(avail, new LinkedList<Movie>(), maxTake);
    }

    //precond: avail represents the movies available, taken represents the movies currently selected,
    //maxTake represents how many more movies can be selected. 
    //postcond: select() is called again, with the first movie from avail removed 
    private int select(LinkedList<Movie> avail, LinkedList<Movie> taken, int maxTake ) {
        if (maxTake == 0 || avail.isEmpty()) {
            return this.totalScore(taken);
        }
        Movie curr = avail.get(0);
        LinkedList<Movie> newAvail = new LinkedList<Movie>(avail);
        newAvail.remove(0);
        
        //choice is to take/not take the first movie from available movies
        //but first we need to know if we can take in the first place
        //if we cannot take that movie, then we will just remove it from availability
        if (!this.canTake(taken, curr)) {     
            return this.select(newAvail, taken, maxTake);
        } else {
            //once we checked that it is possible to take the movie,    
            //we can make a decision to take or not take.
            //this will result in two scores, the score if we took the movie
            //and the score if we did not take the movie
            //we want the maximum score of the two options
           LinkedList<Movie> newTaken = new LinkedList<Movie>(taken);
           newTaken.add(curr);
           return Math.max(this.select(newAvail,newTaken,maxTake-1),
           this.select(newAvail, taken, maxTake));
        }
    }

    //precond: m is the Movie to be checked to see if it can fit into a watch 
    //schedule represented by list
    //postcond: returns a boolean that indicates if m can be watched in conjunction with 
    //all the other movies in list. 
    private boolean canTake(List<Movie> list, Movie m) {
        int mStart = m.start();
        int mEnd = m.end();
        for (Movie n : list) {
           int currStart = n.start();
           int currEnd = n.end();
           //m must start after current movie or end before current movie
           if(mStart >= currEnd || mEnd <= currStart) continue;
           return false; //the moment m cannot be watched with any Movie in list, return false
        }
        //will only reach here if all movies clear
        return true;
    }

    //predcond: list represents the group of movies that we want to have 
    //their scores summed. 
    //postcond: returns an integer representing the sum of the scores of 
    //all the movies in list
    private int totalScore(List<Movie> list) {
        int score = 0;
        for (Movie m : list) {
            score += m.score();
        }
        return score;
    }

    public static void main(String args[]) {
        Movies runner = new Movies();
        runner.run();
    }
}

class Movie {
    private int start;
    private int end;
    private int score;

    //precond: a movie is represented by a start time, end time and a score
    // 0 <= start <= end <= 10^9, 0 <= score <= 10^8
    //postcond: a movie object is created
    public Movie(int start,int end,int score) {
        this.start = start;
        this.end = end;
        this.score = score;
    }

    public int start() {return this.start;}
    public int end() {return this.end;}
    public int score() {return this.score;}
    
    @Override
    public String toString() {
        return this.start + " " + this.end + " " + this.score;
    }
}

