package idx_csd.Algo;
import net.jadecy.graph.*;
import java.util.*;

import net.jadecy.utils.ComparableTreeSet;
/**
 * @author ：Yankai CHEN
 * @class ：SCSDQuery
 * @date ：Created in 7/1/2020
 */



public class SCSDQuery{

    /**
   SCSDQuery this class has a natural ordering that is inconsistent with equals.
     */
    public static class Vertex implements InterfaceVertex {
        private final int id;
        /**
         * Object field so that it can be reused by extending classes
         * for other types of collections.
         */
        private Object successors;
        /**
         * @param id Id for ordering. Must be >= 0.
         */
        public Vertex(int id) {
            this((Void) null, id);
            this.setSuccessors(new TreeSet<Vertex>());
        }
        @Override
        public String toString() {
            /*
             * Not showing successors in vertex toString(),
             * to avoid spam if just wanting to show its id or such.
             * Use printGraph(...) to show successors.
             */
            return "[" + this.id + "]";
        }
        @Override
        public int compareTo(InterfaceVertex other) {
            final Vertex ozer = (Vertex) other;
            // Our ids are >= 0.
            return this.id - ozer.id;
        }
        public int id() {
            return this.id;
        }
        @Override
        @SuppressWarnings("unchecked")
        public Set<Vertex> successors() {
            return (Set<Vertex>) this.successors;
        }
        /**
         * setSuccessors(...) must be called after that
         * during construction.
         */
        protected Vertex(
                Void dummy,
                int id) {
            if (id < 0) {
                throw new IllegalArgumentException("" + id);
            }
            this.id = id;
        }
        /**
         * To be called during construction, and never after.
         */
        protected final void setSuccessors(Object successors) {
            this.successors = successors;
        }
    }


    /**
     * Note: if vertices classes have a natural ordering that is inconsistent
     * with equals, this class also does.
     */
    public static class ComparableVertexTreeSet extends ComparableTreeSet<InterfaceVertex> {
        private static final long serialVersionUID = 1L;
        public ComparableVertexTreeSet() {
        }
        public ComparableVertexTreeSet(Collection<? extends InterfaceVertex> c) {
            super(c);
        }
    }



    public boolean run(Set<Integer> subgraph, int[][] outGraph, int queryID, int k, int l){
        if(subgraph.isEmpty()) return false;
        while(true){
            Set<Integer> newSubgraph = search(subgraph, outGraph, queryID, k, l);
            if(newSubgraph.isEmpty()) return false;
            if(newSubgraph.size() == subgraph.size()) return true;
            subgraph = newSubgraph;
        }
    }



    private Set<Integer> search(Set<Integer> subgraph, int[][] outGraph, int queryID, int k, int l){
        //step 1: load graph
        Map<Integer, Integer> inDegree = new HashMap<Integer, Integer>();
        List <InterfaceVertex> graph = new ArrayList<InterfaceVertex>();
        Map<Integer, Vertex> vertexMap = new HashMap<Integer, Vertex>(subgraph.size());
        for(int x : subgraph){
            final Vertex vertex = new Vertex(x);
            vertexMap.put(x, vertex);
        }

        for(int x: subgraph){
            final Vertex vertex = vertexMap.get(x);
            for(int ngh: outGraph[x]){
                if(subgraph.contains(ngh)){
                    final Vertex succ = vertexMap.get(ngh);
                    vertex.successors().add(succ);
                }
                if(!inDegree.containsKey(ngh)) inDegree.put(ngh, 0);
                inDegree.put(ngh, inDegree.get(ngh) + 1);
            }
            graph.add(vertex);
        }
        System.out.println("loading graph done.");
        //step 2: search
        tarjan(graph);

        //step 3: check degree
        Set<Integer> toRemove = new HashSet<Integer>();
        for(Vertex v : vertexMap.values()){
            if(v.successors().size() < l)
                toRemove.add(v.id);
        }
        for(int id: inDegree.keySet()){
            if(inDegree.get(id) < k)
                toRemove.add(id);
        }
        if(toRemove.contains(queryID)) return new HashSet<Integer>();
        if(toRemove.isEmpty()) return subgraph;
        for(int x: toRemove) subgraph.remove(x);
        return subgraph;
    }




    private static class MySccComputerVcp implements InterfaceVertexCollProcessor {
        final List<List<InterfaceVertex>> sccList = new ArrayList<List<InterfaceVertex>>();
        final TreeSet<ComparableVertexTreeSet> sccSet = new TreeSet<ComparableVertexTreeSet>();
        ComparableVertexTreeSet currentScc;
        /**
         * To check ordering and unicity.
         */
        InterfaceVertex collPrev = null;
        int nbrOfSccsUntilStop = -1;
        @Override
        public void processCollBegin() {
            this.sccList.add(new ArrayList<InterfaceVertex>());
            this.currentScc = new ComparableVertexTreeSet();
            this.collPrev = null;
        }
        @Override
        public void processCollVertex(InterfaceVertex vertex) {
            this.sccList.get(this.sccList.size()-1).add(vertex);
            this.currentScc.add(vertex);
            {
                if (this.collPrev != null) {
                    if (this.collPrev.compareTo(vertex) >= 0) {
                        throw new AssertionError(this.collPrev + " >= " + vertex + " (bad ordering)");
                    }
                }
                this.collPrev = vertex;
            }
        }
        @Override
        public boolean processCollEnd() {
            final boolean didAdd = this.sccSet.add(this.currentScc);
            if (!didAdd) {
                // Means duplicate.
                throw new AssertionError();
            }
            this.currentScc = null;
            if (this.nbrOfSccsUntilStop < 0) {
                return false;
            } else {
                return (--this.nbrOfSccsUntilStop <= 0);
            }
        }
    }





    private void tarjan(List <InterfaceVertex> graph) {
        final MySccComputerVcp processor = new MySccComputerVcp();
        SccsComputer.computeSccs(graph, processor);

        System.out.println(processor.sccSet.size());
    }

    public static void main(String[] args){
        SCSDQuery g = new SCSDQuery();
        Set<Integer> subgraph = new HashSet<Integer>();
        subgraph.add(0);
        subgraph.add(1);
        subgraph.add(2);
        subgraph.add(3);

        int[][] outGraph = {
                {1,2},
                {3},
                {3,4},
                {0,5},
                {5},
                {}
        };

        g.run(subgraph, outGraph, 1, 0, 0);
    }


}