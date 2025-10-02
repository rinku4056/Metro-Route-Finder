package com.metroapp.service;

import com.metroapp.graph.MetroGraph;
import org.springframework.stereotype.Service;

@Service
public class MetroService {

    private MetroGraph metro;

    public MetroService() {
        metro = new MetroGraph();
        addSampleData();
    }

    public String findShortestPath(String source, String destination) {
        return metro.findShortestPath(source, destination);
    }

    private void addSampleData() {
        metro.addStation("Rajiv Chowk");
        metro.addStation("Kashmere Gate");
        metro.addStation("Karol Bagh");
        metro.addStation("Central Secretariat");
        metro.addStation("AIIMS");
        metro.addStation("INA");

        metro.addConnection(0, 1, 6);
        metro.addConnection(0, 2, 5);
        metro.addConnection(0, 3, 4);
        metro.addConnection(3, 4, 6);
        metro.addConnection(4, 5, 3);
        metro.addConnection(1, 5, 7);
    }
}