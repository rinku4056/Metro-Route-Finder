package com.metroapp.controller;

import com.metroapp.model.PathRequest;
import com.metroapp.service.MetroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metro")
@CrossOrigin(origins = "*")
public class MetroController {

    @Autowired
    private MetroService metroService;

    @PostMapping("/path")
    public String getShortestPath(@RequestBody PathRequest request) {
        return metroService.findShortestPath(request.getSource(), request.getDestination());
    }
}