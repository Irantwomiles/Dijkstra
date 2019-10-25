package A5_Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import MinBinHeap_A3.*;

public class DiGraph implements DiGraph_Interface {

	Map<String, Map<String, Edge>> nodes;
	Map<Long, String> nodeIDs;
	Map<Long, Edge> edgeIDs;
	List<Node> nodeList;
	List<String> nodeRemoved;
	int numEdges;

	public DiGraph() { 
		nodes = new HashMap<String, Map<String, Edge>>();
		nodeIDs = new HashMap<Long, String>();
		edgeIDs = new HashMap<Long, Edge>();
		nodeList = new ArrayList<Node>();
		nodeRemoved = new ArrayList<String>();
		numEdges = 0;
	}

	@Override
	public boolean addNode(long idNum, String label) {
		// false if node < 0
		if (idNum < 0) {
			return false;
		}
		if (nodeIDs.containsKey(idNum)) {
			if (nodes.containsKey(nodeIDs.get(idNum))) {
				return false;
			}
		}
		// false if null
		if (label == null || nodes.containsKey(label)) {
			return false;
		}
		// true if node is added
		nodeIDs.put(idNum, label);
		nodes.put(label, new HashMap<String, Edge>());
		nodeList.add(new Node(idNum, label));
		return true;
	}

	@Override
	public boolean addEdge(long idNum, String sourceLabel, String destinationLabel, long weight, String eLabel) {
		// false if edge number < 0
		if (idNum < 0) {
			return false;
		}
		if (edgeIDs.containsKey(idNum)) {
			if (nodes.get(edgeIDs.get(idNum).sourceLabel).containsKey(edgeIDs.get(idNum).desitiationLabel)) {
				return false;
			}
		}
		// if source not in graph
		if (!nodes.containsKey(sourceLabel)) {
			return false;
		}
		// if destination not in graph
		if (!nodes.containsKey(destinationLabel)) {
			return false;
		}
		//  false is there is edge between these 2 nodes
		if (nodes.get(sourceLabel).get(destinationLabel) != null) {
			return false;
		}
		// true if edge is added
		Edge edge = new Edge(idNum, sourceLabel, destinationLabel, weight, eLabel);
		nodes.get(sourceLabel).put(destinationLabel, edge);
		edgeIDs.put(idNum, edge);
		numEdges += 1;
		return true;
	}

	@Override
	public boolean delNode(String label) {
		// return false if the node does not exist
		if (!nodes.containsKey(label)) {
			return false;
		}
		numEdges -= nodes.get(label).size();
		nodes.remove(label);
		nodeRemoved.add(label);
		return true;
	}

	@Override
	public boolean delEdge(String sourceLabel, String destinationLabel) {
		
		if (!nodes.containsKey(sourceLabel) || !nodes.containsKey(destinationLabel)) {
			return false;
		}
		if (nodes.get(sourceLabel).get(destinationLabel) == null) {
			return false;
		}

		nodes.get(sourceLabel).remove(destinationLabel);
		numEdges -= 1;
		return true;
	}

	@Override
	public long numNodes() {
		return nodes.size();
	}

	@Override
	public long numEdges() {
		return numEdges;
	}

	@Override
	public ShortestPathInfo[] shortestPath(String label) {
		
		Map<String, Boolean> exist = new HashMap<String, Boolean>();
		Map<String, Long> dis = new HashMap<String, Long>();
		List<ShortestPathInfo> table = new ArrayList<ShortestPathInfo>();
		Heap_Interface heap = new MinBinHeap();
		heap.insert(new EntryPair(label, 0));
		while(heap.size() != 0) {
			String n = heap.getMin().getValue();
			long d = heap.getMin().getPriority();
			heap.delMin();
			if (exist.get(n) != null && exist.get(n) == true) {
				continue;
			}
			exist.put(n, true);
			table.add(new ShortestPathInfo(n, d));
			nodes.get(n).forEach((des, edge) -> {
				if (exist.get(des) == null || exist.get(des) == false) {
					if (dis.get(des) == null || dis.get(des) > d + edge.weight) {
						dis.put(des, d + edge.weight);
						heap.insert(new EntryPair(des, d + edge.weight));
					}
				}
			});
		}
		nodes.forEach((node, edge) -> {
			if (exist.get(node) == null) {
				table.add(new ShortestPathInfo(node, -1));
			}
		});
		ShortestPathInfo[] return_table = new ShortestPathInfo[table.size()];
		return_table = table.toArray(return_table);
		return return_table;
	}

}

class Node {
	long idNum;
	String label;

	Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
	}
}

class Edge {
	long idNum;
	String sourceLabel;
	String desitiationLabel;
	long weight;
	String eLabel;

	Edge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		this.idNum = idNum;
		this.sourceLabel = sLabel;
		this.desitiationLabel = dLabel;
		this.weight = weight;
		this.eLabel = eLabel;
	}
}