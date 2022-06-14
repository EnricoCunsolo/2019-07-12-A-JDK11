package it.polito.tdp.food.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {
	
	FoodDao dao;
	Map<Integer,Food> foodMap;
	Graph<Food,DefaultWeightedEdge> grafo;
	
	public Model() {
		dao = new FoodDao();
		foodMap = new HashMap<>();
		dao.listAllFoods(foodMap);
	}
	
	public void creaGrafao(int n) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		List<Food> vertici = new LinkedList<>(dao.getVertici(n,foodMap));
		Graphs.addAllVertices(grafo, vertici);
		System.out.println("VERTICI = "+ grafo.vertexSet().size());
		for(Food f1 : vertici) {
			for(Food f2 : vertici) {
				if(!f1.equals(f2) && (!grafo.containsEdge(f1, f2) || !grafo.containsEdge(f2, f1))) {
					double peso = dao.getPeso(f1,f2,foodMap);
					if(peso!=0) {
						Graphs.addEdgeWithVertices(grafo, f1, f2, peso);
					}
				}
			}
		}
		System.out.println("ARCHI = "+ grafo.edgeSet().size());
		
		
		
	}

}
