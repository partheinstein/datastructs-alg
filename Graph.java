import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Stack;

public class Graph {        

    enum Colour {
        WHITE, GRAY, BLACK
    };

    class Vertex {
        Character label;
        Colour colour;
        int distFromSource;
        Vertex parent;

        Vertex(Character label, Colour colour, int distFromSource, Vertex parent) {
            this.label = label;
            this.colour = colour;
            this.distFromSource = distFromSource;
            this.parent = parent;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Vertex)) return false;
            Vertex other = (Vertex) o;
            return other.label.equals(o);
        }

        @Override
        public int hashCode() {
            return label.hashCode();
        }

        @Override
        public String toString() {
            return "(" + label + "," + colour + "," + distFromSource + "," + parent + ")";
        }

    }

    public Map<Character, Vertex> bfs(Map<Character, Set<Character>> adjacentList,
                                      Character sourceLabel) {
        
        Map<Character, Vertex> vertices = new HashMap<>();
        adjacentList.keySet().forEach(vLabel -> {
                if (!vLabel.equals(sourceLabel)) {
                    vertices.put(vLabel, new Vertex(vLabel, Colour.WHITE, Integer.MAX_VALUE, null));
                }
            });

        Vertex source = new Vertex(sourceLabel, Colour.GRAY, 0, null);
        LinkedList<Vertex> q = new LinkedList<>();
        q.add(source);
        while (q.size() != 0) {
            Vertex u = q.remove();
            for (Character vLabel : adjacentList.get(u.label)) {       
                Vertex v = vertices.get(vLabel);
                if (v != null) {// if v is the source, it will not be in the vertices map
                    if (v.colour == Colour.WHITE) {
                        v.colour = Colour.GRAY;
                        v.distFromSource = u.distFromSource + 1;
                        v.parent = u;
                        q.add(v);
                    }
                }
            }
            u.colour = Colour.BLACK;
        }

        return vertices;
    }

    /**
     * Calculates distance from source vertex to reachable vertices using Breadth First Search
     * Note: the distances are the shortest path distances
     */
    public Map<Character, Integer> getDistBFS(Map<Character, Set<Character>> adjacentList,
                                              Character sourceLabel) {
        
        Map<Character, Boolean> visited = new HashMap<>();
        Map<Character, Integer> dist = new HashMap<>();
        for(Character u : adjacentList.keySet()) {
            visited.put(u, Boolean.FALSE);
            dist.put(u, Integer.MAX_VALUE);
        }

        visited.put(sourceLabel, true);
        dist.put(sourceLabel, 0);

        LinkedList<Character> q = new LinkedList<>();
        q.add(sourceLabel);
        while (q.size() != 0) {
            Character u = q.remove();
            for (Character v : adjacentList.get(u)) {
                if (v != null) {
                    if (visited.get(v).equals(Boolean.FALSE)) {
                        int distFromSrc = dist.get(u) + 1;
                        dist.put(v, distFromSrc);
                        visited.put(v, Boolean.TRUE);
                        q.add(v);
                    }
                }
            }
            
        }
        return dist;
    }

    /**
     * Calculates distance from source to reachable vertices using Depth first search (recursive)
     * Note: this is not the shortest distance; instead it is the distance as traversed
     * using DFS
     */
    public Map<Character, Integer> getDistDFS(Map<Character, Set<Character>> adjacentList,
                                              Character sourceLabel) {
        
        Map<Character, Boolean> visited = new HashMap<>();
        Map<Character, Integer> dist = new HashMap<>();

        for(Character u : adjacentList.keySet()) {
            visited.put(u, Boolean.FALSE);
            dist.put(u, Integer.MAX_VALUE);
        }

        visited.put(sourceLabel, true);
        dist.put(sourceLabel, 0);

        getDistDFS(adjacentList, sourceLabel, dist, visited);

        return dist;
        
    }
    
    private void getDistDFS(Map<Character, Set<Character>> adjacentList,
                            Character u,
                            Map<Character, Integer> dist,
                            Map<Character, Boolean> visited) {
        
        for (Character v : adjacentList.get(u)) {
            if (v != null) {
                if (visited.get(v).equals(Boolean.FALSE)) {
                    int distFromSrc = dist.get(u) + 1;
                    dist.put(v, distFromSrc);
                    visited.put(v, Boolean.TRUE);
                    getDistDFS(adjacentList, v, dist, visited);
                }
            }
        }        
    }

    public boolean isCyclic(Map<Character, Set<Character>> adjacentList) {
        Map<Character, Boolean> visited = new HashMap<>();

        for(Character u : adjacentList.keySet()) {
            visited.put(u, Boolean.FALSE);
        }

        Stack<Character> stack = new Stack<>();

        for (Character u : adjacentList.keySet()) {
            visited.put(u, true);
            stack.push(u);
            if (isCyclic(adjacentList, u, stack, visited)) {
                return true;
            }

            // re-init visited
            for(Character u : adjacentList.keySet()) {
                visited.put(u, Boolean.FALSE);
            }
        }

        System.out.println("end isCyclic stack size = " + stack.size());

        return false;
    }

    private boolean isCyclic(Map<Character, Set<Character>> adjacentList,
                             Character u,
                             Stack<Character> stack,
                             Map<Character, Boolean> visited) {
        
        System.out.println("visit " + u + " stack: " + stack);

        for(Character v : adjacentList.get(u)) {
            System.out.println("adj " + v  + " " + u);
            if (v != null) {
                if (stack.contains(v)) {
                    return true;
                }
                stack.push(v);
                
                if (visited.get(v).equals(Boolean.FALSE)) {
                    visited.put(v, Boolean.TRUE);
                    if (isCyclic(adjacentList, v, stack, visited)) {
                        stack.pop();
                        return true;
                    }
                }
            }
        }

        stack.pop();
        return false;
    }

    public static void main(String[] args) {
        Map<Character, Set<Character>> adjList = new HashMap<>();

        // r
        Set<Character> adjs = new HashSet<>();
        adjs.add('s'); //use Character.valueOf factory method
        adjs.add('v');
        adjList.put('r', adjs);

        // v
        adjs = new HashSet<>();
        adjs.add('r');
        adjList.put('v', adjs);

        // s
        adjs = new HashSet<>();
        adjs.add('r');
        adjs.add('w');
        adjList.put('s', adjs);

        // w
        adjs = new HashSet<>();
        adjs.add('s');
        adjs.add('t');
        adjs.add('x');
        adjList.put('w', adjs);

        // t
        adjs = new HashSet<>();
        adjs.add('w');
        adjs.add('x');
        adjList.put('t', adjs);

        // x
        adjs = new HashSet<>();
        adjs.add('w');
        adjs.add('t');
        adjs.add('u');
        adjs.add('y');
        adjList.put('x', adjs);

        // u
        adjs = new HashSet<>();
        adjs.add('t');
        adjs.add('x');
        adjs.add('y');
        adjList.put('u', adjs);

        // y
        adjs = new HashSet<>();
        adjs.add('u');
        adjs.add('x');
        adjList.put('y', adjs);

        Graph g = new Graph();

        System.out.println("Book");
        Map<Character, Vertex> bfirstTree = g.bfs(adjList, 's');
        bfirstTree.values().forEach(v -> System.out.println(v));

        System.out.println("BFS");
        Map<Character, Integer> distBFS = g.getDistBFS(adjList, 's');
        distBFS.keySet().forEach(v -> System.out.println(v + ":" + distBFS.get(v)));

        System.out.println("DFS");
        Map<Character, Integer> distDFS = g.getDistDFS(adjList, 's');
        distDFS.keySet().forEach(v -> System.out.println(v + ":" + distDFS.get(v)));

        Map<Character, Set<Character>> adjListCycles = new HashMap<>();

        // r
        adjs = new HashSet<>();
        adjs.add('v');
        adjListCycles.put('r', adjs);

        // v
        adjs = new HashSet<>();
        adjs.add('r');
        adjListCycles.put('v', adjs);

        System.out.println(g.isCyclic(adjListCycles));
    }
}
