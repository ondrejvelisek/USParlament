package es.upc.fib.prop.shared13.ncliques;


import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import es.upc.fib.prop.shared13.Graph;
import es.upc.fib.prop.shared13.Node;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class represents a Clique Percolation Method algorithm.
 */
public class NCliquePercolation
{
    private ArrayList<HashSet<Node>> Cliques;
    private Table<Integer,Integer,Integer> matrix;;
    ArrayList<Set<Node>> cc;
    private Graph G;
    private class BKAlgorithm {



        private Graph g;
        private ArrayList<HashSet<Node>> Cliqs;
        private LinkedHashSet<Node> R, P, X;
        private static final String ERR_VOID_SET =
                "The set is empty, but this is not expected";
        public BKAlgorithm(Graph graph) {
            if (graph == null) throw new NullPointerException();
            g = graph;
            R  = new LinkedHashSet<>();
            P = new LinkedHashSet<>();
            X = new LinkedHashSet<>();
            Cliqs = new ArrayList<HashSet<Node>>();
            Collection<Node> c = g.getNodes();
            for(Node n:c){
                P.add(n);
            }
        }
        public void executeBK() {
            recursiveBK(R, P, X);
        }



        /**
         * Recursive method that developed Bron-Kerbosch method; a method
         * to find maximal cliques in a graph.
         *
         * @param r,p,x set of nodes.
         */
        private void recursiveBK(Set<Node> r, Set<Node> p, Set<Node> x) {
            if (p.isEmpty() && x.isEmpty()) {
                LinkedHashSet<Node> ret = new LinkedHashSet<>(r);
                Cliqs.add(ret);
                return;
            }
            Set<Node> unionPX = Sets.union(x, p); // Solo se consulta
            Node pivot = getPivot(unionPX);
            Set<Node> pnonpivot = Sets.difference(p, g.getValidNeighbours(pivot)); //Solo se consulta
            Set<Node> copia = new HashSet<>(pnonpivot);
            for (Node v : copia) {
                HashSet<Node> newr = new HashSet<Node>(r);
                newr.add(v); //rU{v}
                HashSet<Node> newp = new HashSet<Node>(p);
                newp.retainAll(g.getValidNeighbours(v)); // p ∩ N(v)
                HashSet<Node> newx = new HashSet<Node>(x);
                newx.retainAll(g.getValidNeighbours(v)); // x ∩ N(v)
                recursiveBK(newr, newp, newx);
                p.remove(v); // p \ {v}
                x.add(v); // x U {v}
            }
        }
        public ArrayList<HashSet<Node>> getallMaximalCliques() {
            return Cliqs;
        }
        /**
         * Returns the pivot node, the node with more neighbours in V.
         *
         * @param V set of nodes.
         * @return Returns the pivot node
         * @throws IllegalArgumentException if V is empty.
         */
        private Node getPivot(Set<Node> V) {
            if (V.isEmpty()) throw new IllegalArgumentException(ERR_VOID_SET);
            Iterator<Node> it = V.iterator();
            Node maxn;
            int maxint;
            maxn = it.next();
            Set<Node> intersection = Sets.intersection(g.getValidNeighbours(maxn), V);
            maxint = intersection.size();
            while (it.hasNext()) {
                Node n = it.next();
                intersection = Sets.intersection(g.getValidNeighbours(n), V);
                int intersize = intersection.size();
                if (intersize > maxint) {
                    maxint = intersize;
                    maxn = n;
                }
            }
            return maxn;
        }
    }

    /**
     * Creates a CPMAlgortihm
     * @param graph The input graph of the algorithm
     */
    public NCliquePercolation(Graph graph) {
        this.G=graph;
        cc = new ArrayList<Set<Node>>();
        Cliques = new ArrayList<HashSet<Node>>();
    }

    /**
     * Executes the algorithm to the graph.
     */
    public void execute() {
        BKAlgorithm BK = new BKAlgorithm(G);
        BK.executeBK();
        Cliques = BK.getallMaximalCliques();
        int n = Cliques.size();
        matrix = HashBasedTable.create();
        ListIterator<HashSet<Node>> it = Cliques.listIterator();
        int i = 0;
        long kl = 1;
        double kf = 1.0;

        while (it.hasNext()) {
            Set<Node> C1 = it.next();
            int sz = C1.size();
            kf *= Math.pow((double) sz, 1.0 / n); // Producto tamaño cliques
            matrix.put(i, i, sz);
            ++i;
        }
        int k = (int) Math.round(kf);
        it = Cliques.listIterator();
        i = 0;

        ArrayList<Set<Node>> cliq2 = new ArrayList<>();
        while (it.hasNext()) {
            Set<Node> C1 = it.next();
            int sz = C1.size();
            if(sz >= k) {
                matrix.put(i, i, sz);
                cliq2.add(i, C1);
                ++i;
            }
        }

        ListIterator<Set<Node>> itt= cliq2.listIterator();
        i = 0;
        while (itt.hasNext()) {
            int j = i;
            Set<Node> C1 = itt.next();
            ListIterator<Set<Node>> it2 = cliq2.listIterator(itt.previousIndex());
            while (it2.hasNext()) {
                Set<Node> C2 = it2.next();
                if (i == j) {
                    if (matrix.get(i, j) < k) {
                        matrix.put(i, j, 0);
                    } else {
                        matrix.put(i, j, 1);
                    }
                } else {
                    Set<Node> intersection = Sets.intersection(C2, C1);
                    int intersize = intersection.size();
                    if (intersize >= k - 1) matrix.put(i, j, 1);
                }
                ++j;
            }
            ++i;
        }

        Queue<Integer> que = new LinkedBlockingQueue<>();
        for (int ii = 0; ii < cliq2.size(); ++ii) {
            if (matrix.get(ii, ii) == 1) {
                que.add((Integer) ii);
                Set<Node> com;
                com = BT(matrix, cliq2, que, n);
                cc.add(com);
            }
            que.clear();
        }
    }

    /**
     * Returns a community of nodes find in the
     * overlapping matrix.
     * @return A community of nodes.
     */
    private Set<Node> BT(Table<Integer,Integer,Integer> m, ArrayList<Set<Node>> Clicks, Queue<Integer> q, int n) {
        Set<Node> c = new LinkedHashSet<>();
        while (!(q.isEmpty())) {
            Integer p = q.poll();
            if (m.contains(p,p) && m.get(p,p) == 1) {
                c.addAll(Clicks.get(p));
                m.put(p,p,0);
                for (Integer i : m.row(p).keySet()) {
                    if (m.get(p, i) == 1) {
                        q.add( i);
                        m.put(p,i,0);
                    }
                }
            }
        }
        return c;
    }

    public ArrayList<Set<Node>> getCc()
    {
        return cc;
    }

}
