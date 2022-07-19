package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    MinPQ<serachNode> minPQ = new MinPQ<>();
    int smove;
    List<WorldState> sl;
    public Solver(WorldState initial) {
        serachNode init = new serachNode(initial,0,null);
        minPQ.insert(init);
        serachNode search = minPQ.delMin();
        while (!search.cur.isGoal()) {

            Iterable<WorldState> it = search.cur.neighbors();
            Iterator<WorldState> ne = it.iterator();
            while (ne.hasNext()) {
                WorldState s = ne.next();
                if ((search.pre != null && !s.equals(search.pre.cur)) || search.pre == null) {
                    minPQ.insert(new serachNode(s,search.move + 1,search));
                }
            }
            search = minPQ.delMin();
        }
        sl = new ArrayList<>();
        smove = search.move;
        while (search.pre != null) {
            sl.add(search.cur);
            search = search.pre;
        }
        sl.add(search.cur);
    }

    private class serachNode implements Comparable {
        private int esti;
        WorldState cur;
        serachNode pre;
        int move;
        public serachNode(WorldState cur,int move,serachNode pre) {
            this.cur = cur;
            this.move = move;
            this.pre = pre;
            esti = this.cur.estimatedDistanceToGoal();
        }

        public int getEsti(){
            return esti;
        }

        @Override
        public int compareTo(Object o) {
            serachNode o1 = (serachNode) o;
            if (this.getEsti() + this.move > o1.getEsti() + o1.move)
                return 1;
            else if (this.getEsti() + this.move < o1.getEsti() + o1.move)
                return -1;
            return 0;
        }
    }

    public int moves() {
        return smove;
    }

    public Iterable<WorldState> solution() {
        return sl;
    }
}
