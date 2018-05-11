package sselab;
import java.util.ArrayList;

public class CFG_Node {
	public ArrayList<Integer> outedge;
	public ArrayList<Integer> inedge;
	private int nodenum;
	private String kind;
	
	private String startLine;
	private String startCol;

	private String finishLine;
	private String finishCol;
	
	private String endstructurenode;
	
	public CFG_Node(int num,String kind) {
		outedge = new ArrayList<Integer>();
		inedge = new ArrayList<Integer>();
		nodenum = num;
		this.kind = kind;
	}
	
	public void add_Edge(int next) {
		outedge.add(next);
	}
	
	public String getStartLine() {
		return startLine;
	}
	public void setStartLine(String startLine) {
		this.startLine = startLine;
	}
	public String getFinishLine() {
		return finishLine;
	}
	public void setFinishLine(String finishLine) {
		this.finishLine = finishLine;
	}
	public String getStartCol() {
		return startCol;
	}
	public void setStartCol(String startCol) {
		this.startCol = startCol;
	}
	public String getFinishCol() {
		return finishCol;
	}
	public void setFinishCol(String finishCol) {
		this.finishCol = finishCol;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getNodenum() {
		return nodenum;
	}

	public void setNodenum(int nodenum) {
		this.nodenum = nodenum;
	}

	public String getEndstructurenode() {
		return endstructurenode;
	}

	public void setEndstructurenode(String endstructurenode) {
		this.endstructurenode = endstructurenode;
	}
	
}
