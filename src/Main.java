
import java.util.*;
public class Main {
    public  static class Edge{

        int dest;
        int weight;
        public Edge(int dt, int wt){
            this.dest=dt;
            this.weight=wt;
        }
    }
    public static class Stationgraph{
        int numstations;
         ArrayList<ArrayList<Edge>> graph;
         public Stationgraph(int numstations){
             this.numstations=numstations;
             graph=new ArrayList<>();


         for(int i=0;i<numstations;i++){
             graph.add(new ArrayList<>());
         }
    }
    public void addEdge(int src, int dest, int wt){
         graph.get(src).add(new Edge(dest,wt));
         graph.get(dest).add(new Edge(src,wt));
         }


    }

    public static  class Pair{
        int node;
        int distance;
        public Pair(int node,int dis){
            this.node=node;
            this.distance=dis;
        }
    }

     public   static int[] dijkstra(ArrayList<ArrayList<Edge>> graph, int src){
         int n=graph.size();
         int[] dist=new int[n];
         Arrays.fill(dist, Integer.MAX_VALUE);
         dist[src]=0;
         PriorityQueue<Pair> pq=new PriorityQueue<>(Comparator.comparingInt(p -> p.distance));
         pq.add(new Pair(src,0));
         while(!pq.isEmpty()){
             Pair curr=pq.poll();
             for(Edge edge: graph.get(curr.node)){
                 int newdist= curr.distance + edge.weight;
                 if(newdist<dist[edge.dest]){
                     dist[edge.dest]=newdist;
                     pq.add(new Pair(edge.dest,newdist));
                 }
             }
         }
return dist;
     }

    public static void main(String[] args) {
  int n=5;
  Stationgraph metro = new Stationgraph(n);
        HashMap<String,Integer> nameToIndex=new HashMap<>();
        HashMap<Integer,String> indexToName=new HashMap<>();

        nameToIndex.put("Rajiv Chowk", 0);
        indexToName.put(0, "Rajiv Chowk");

        nameToIndex.put("Kashmere Gate", 1);
        indexToName.put(1, "Kashmere Gate");

        nameToIndex.put("Central Secretariat", 2);
        indexToName.put(2, "Central Secretariat");
        metro.addEdge(0, 1, 5); // Rajiv Chowk <-> Kashmere Gate
        metro.addEdge(0, 2, 2); // Rajiv Chowk <-> Central Secretariat

        // Find path from Rajiv Chowk to Kashmere Gate
        int[] distances = dijkstra(metro.graph, nameToIndex.get("Rajiv Chowk"));
        System.out.println("Shortest distance: " + distances[nameToIndex.get("Kashmere Gate")]);
    }
}