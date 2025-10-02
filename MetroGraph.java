package com.metroapp.graph;

import java.util.*;

public class MetroGraph {
    class Station {
        String name;
        Station(String name) { this.name = name; }
    }

    class Edge {
        int dest, weight;
        Edge(int dest, int weight) {
            this.dest = dest; this.weight = weight;
        }
    }

    private List<Station> stations = new ArrayList<>();
    private List<List<Edge>> adjList = new ArrayList<>();

    public void addStation(String name) {
        stations.add(new Station(name));
        adjList.add(new ArrayList<>());
    }

    public void addConnection(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(src, weight));
    }

    public int getStationIndex(String name) {
        for (int i = 0; i < stations.size(); i++) {
            if (stations.get(i).name.equalsIgnoreCase(name)) return i;
        }
        return -1;
    }

    public String findShortestPath(String srcName, String destName) {
        int src = getStationIndex(srcName);
        int dest = getStationIndex(destName);
        if (src == -1 || dest == -1) return "Invalid station name.";

        int n = stations.size();
        int[] dist = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];

            for (Edge edge : adjList.get(u)) {
                int v = edge.dest, w = edge.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }

        if (dist[dest] == Integer.MAX_VALUE) return "No path found.";

        List<String> path = new ArrayList<>();
        for (int at = dest; at != -1; at = parent[at]) {
            path.add(stations.get(at).name);
        }
        Collections.reverse(path);

        return "Shortest Distance: " + dist[dest] + "\nPath: " + String.join(" → ", path);
    }
}