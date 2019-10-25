package DiGraph_A5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiGraph implements DiGraph_Interface {

  
  Map<Long, Node> nodes;
  List<Node> nodeList;
	
	
  public DiGraph ( ) { 
	  nodes = new HashMap<Long, Node>();
	  nodeList = new ArrayList<Node>();
  }

@Override
public boolean addNode(long numID, String label) {
	
	if (numID < 0 || nodes.containsKey(numID)) {
		return false;
	}
	// false if null 
	if (label == null || containsLabel(label)) {
		return false;
	}
	//  true if node is added
	Node new_node = new Node(numID, label);
	nodes.put(numID, new_node);
	nodeList.add(new_node);
	return true;
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean delNode(String label) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	// TODO Auto-generated method stub
	return false;
}

@Override
public long numNodes() {
	return nodes.size();
}

@Override
public long numEdges() {
	// TODO Auto-generated method stub
	return 0;
}
  
private boolean containsLabel(String label) {
	boolean holds = false;
	for (Node node : nodeList) {
		if (node.label == label) holds = true;
		break;
	}
	return holds;
}

}

class Node {
	long idNum;
	String label;
	List<Edge> edges;
	
	public Node(long idNum, String label) {
		this.idNum = idNum;
		this.label = label;
		this.edges = new ArrayList<Edge>();
	}
}

class Edge {
	long idNum;
	Node src;
	Node des;
	long weight;
	String eLabel;
}