package com.ilakk.develop.connected.cities.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.ilakk.develop.connected.cities.graph.*;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

@Component
public class ConnectedCitiesServiceImpl implements IConnectedCitiesService{

	Set<CityPair> cityPairs = new HashSet<CityPair>();
	Map<City, Integer> cityIdMap = new HashMap<City, Integer>();
	Set<City> distinctEntries = new HashSet<City>();

	static int counter = 0;
	Graph graph;

	@Value("${connctedcities.input.file}")
	private String file;
	
	@Value("${connctedcities.graphsearch.algorithm}")
	private String algorithm;

	@PostConstruct
	public void init() throws URISyntaxException, IOException {
		cityPairs = loadCities();
		setCityIds();
		initGraph();
	}

	public Set<CityPair> loadCities() throws URISyntaxException, IOException {
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(file).toURI()))
				.filter(s -> !s.isEmpty())) { // ignore new line (empty lines) in the input file
			cityPairs = stream.map(line -> {
				String[] pairItems = line.split(",");
				City city1 = new City(pairItems[0].trim().toLowerCase());
				City city2 = new City(pairItems[1].trim().toLowerCase());
				CityPair newPair = new CityPair(city1, city2);
				distinctEntries.add(city1);
				distinctEntries.add(city2);
				return newPair;
			}).collect(Collectors.toSet());
		} catch (URISyntaxException | IOException e) {
			System.out.println(" Error reading Cities");
			e.printStackTrace();
		}
		return cityPairs;
	}

	private void setCityIds() {
		distinctEntries.forEach((City city) -> {
			cityIdMap.put(city, counter);
			counter++;

		});

		cityPairs.forEach((CityPair citypair) -> {
			City LCity = citypair.getLCity();
			City RCity = citypair.getRCity();
			LCity.setId(cityIdMap.get(LCity));
			RCity.setId(cityIdMap.get(RCity));

		});

	}

	private void initGraph() {
		graph = new Graph(cityIdMap.size());

		cityPairs.forEach((CityPair citypair) -> {
			City LCity = citypair.getLCity();
			City RCity = citypair.getRCity();
			graph.addEdge(graph, LCity.getId(), RCity.getId());

		});

	}

	@Override
	public boolean isConnected(String origin, String destination) {
		City city1 = new City(origin.trim());
		City city2 = new City(destination.trim());
		if((GraphSearchAlgorithm.valueOf(algorithm)).equals(GraphSearchAlgorithm.BFS)) {
			   return graph.isConnectedBFS(cityIdMap.get(city1), cityIdMap.get(city2));
		} else if((GraphSearchAlgorithm.valueOf(algorithm)).equals(GraphSearchAlgorithm.DFS)){
			   return graph.isConnectedDFS(cityIdMap.get(city1), cityIdMap.get(city2));
		}
		return graph.isConnectedDFS(cityIdMap.get(city1), cityIdMap.get(city2));
	}

}
