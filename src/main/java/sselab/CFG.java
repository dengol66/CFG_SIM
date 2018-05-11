package sselab;

import java.util.ArrayList;

public class CFG {
	private ArrayList<CFG_Node> nodes;
	/*
	 *  |V|
	 *  |E|
	 * 계산 필요함  
	 * 
	 * 
	 */
	
	public CFG(int size_) {
		nodes = new ArrayList<CFG_Node>(size_);
	}
	
	public void add_node(CFG_Node node) {
		nodes.add(node);
	}
	
	public int getsize() {
		return nodes.size();
	}
	
	public void make_indege() {
		for(CFG_Node node : nodes) {
			for(int out:node.outedge) {
				nodes.get(out-1).inedge.add(node.getNodenum());
			}
		}
	}
	
	public CFG_Node getNode(int idx) {
		return nodes.get(idx-1);
	}
}

