package com.ilakk.develop.connected.cities.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ilakk.develop.connected.cities.graph.*;

/* 
 * 
 * Copyright (C) 2019 Ilakkuvaselvi Manoharan - All Rights Reserved
 * 
 */

public class TestGraph {
	
	static Set<CityPair> cityPairs = new HashSet<CityPair>();
	static Map<City, Integer> cityIdMap = new HashMap<City, Integer>();
	static Set<City> distinctEntries = new HashSet<City>();
	
	static int counter = 0;
	
	static Graph graph;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		cityPairs.add(new CityPair(new City("Boston"),new City("New York")));
		cityPairs.add(new CityPair(new City("Philadelphia"),new City("Newark")));
		cityPairs.add(new CityPair(new City("Newark"),new City("Boston")));
		cityPairs.add(new CityPair(new City("Trenton"),new City("Albany")));
		
		cityPairs.stream().forEach(pair -> { distinctEntries.add(pair.getLCity()); distinctEntries.add(pair.getRCity()); });
		
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
		
		graph = new Graph(cityIdMap.size());
		
		
		cityPairs.forEach((CityPair citypair) -> {
			City LCity = citypair.getLCity();
			City RCity = citypair.getRCity();
			graph.addEdge(graph, LCity.getId(), RCity.getId());
			
	    });
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
		cityPairs = null;
		cityIdMap = null;
		counter = 0;
		distinctEntries = null;
		graph = null;
		
	}
	
	@Test
	void test_isConnectedDFS() {
		
	    assertTrue(graph.isConnectedDFS(cityIdMap.get(new City("Boston")), cityIdMap.get(new City("Newark"))));
	    assertTrue(graph.isConnectedDFS(cityIdMap.get(new City("Boston")), cityIdMap.get(new City("Philadelphia"))));
	    assertFalse(graph.isConnectedDFS(cityIdMap.get(new City("Philadelphia")), cityIdMap.get(new City("Albany"))));
		
	}
	
	@Test
	void test_isConnectedBFS() {
		
		assertTrue(graph.isConnectedBFS(cityIdMap.get(new City("Boston")), cityIdMap.get(new City("Newark"))));
		assertTrue(graph.isConnectedBFS(cityIdMap.get(new City("Boston")), cityIdMap.get(new City("Philadelphia"))));
	    assertFalse(graph.isConnectedBFS(cityIdMap.get(new City("Philadelphia")), cityIdMap.get(new City("Albany"))));
		
	}
    
}
