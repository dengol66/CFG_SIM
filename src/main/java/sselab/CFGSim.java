package sselab;
import java.util.ArrayList;

public class CFGSim {
	int[][] costMatrix;
	static int INF_ = 987654321;
	private CFG A, B;
	int n,m;
	
	public CFGSim(CFG a,CFG b) {
		this.A = a;
		this.B = b;
		n = A.getsize();
		m = B.getsize();
	}
	
	int get_intersection(ArrayList<Integer> a,ArrayList<Integer> b) {
		int ret = Math.min(a.size(), b.size());
		return ret;
	}
	
	void make_costMatrix() {

		costMatrix = new int[n+m+1][n+m+1];
		
		//first section
		for(int i=1;i<=n;i++) {
			CFG_Node nodeA = A.getNode(i);
			for(int j=1;j<=m;j++) {
				CFG_Node nodeB = B.getNode(j);
				//calculate intersection of outedge, inedge;
				costMatrix[i][j] = nodeA.outedge.size() + nodeB.outedge.size() + nodeA.inedge.size() + nodeB.inedge.size()
						- 2 * get_intersection(nodeA.outedge,nodeB.outedge) - 2 * get_intersection(nodeA.inedge,nodeB.inedge);
			}
		}
		
		//second section
		for(int i=1;i<=n;i++) {
			for(int j=m+1;j<=n+m;j++) {
				CFG_Node temp = A.getNode(i);
				if(i == j-m){
					costMatrix[i][j] = temp.inedge.size() + temp.outedge.size() +1;
				}
				else costMatrix[i][j] = INF_;
			}
		}
		
		//thrid section
		for(int i=n+1;i<=n+m;i++) {
			for(int j=1;j<=m;j++) {
				CFG_Node temp = B.getNode(j);
				if(i-n == j){
					costMatrix[i][j] = temp.inedge.size() + temp.outedge.size() + 1;
				}
				else costMatrix[i][j] = INF_;
			}
		}
		
		//fourth section - dummy node
	}
	
	ArrayList<Integer> getNeighbor(ArrayList<Integer> st,int[] eq_graph){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int i=0;i<st.size();i++) {
			ret.add(eq_graph[st.get(i)]);
		}
		return ret;
	}

	void hungrian_alg() {
		ArrayList<Integer> St = new ArrayList<Integer>(); //X
		ArrayList<Integer> T = new ArrayList<Integer>(); //Y
		
		ArrayList<Integer> free_Vertex = new ArrayList<Integer>(); // free vertexes
		
		int[] lx = new int[n+m+1];  
		int[] ly = new int[n+m+1];
		int[] Matching = new int[n+m+1]; //Y -> X
		int[] EqualityGraph = new int[n+m+1]; //X -> Y
		//initial step
		for(int i=1;i<=n+m;i++) {
			ly[i] = 0;
			int min_ = 1;
			for(int j=1;j<=n+m;j++) {
				if(costMatrix[i][j] < costMatrix[i][min_]) {
					min_ = j;
				}
			}
			Matching[min_] = i;
			EqualityGraph[i] = min_;
			lx[i] = costMatrix[i][min_];
			free_Vertex.add(i);
		}
		for(int i=1;i<=n+m;i++) {
			if(Matching[i] >=1) {
				free_Vertex.remove(Matching[i]);
			}
		}
		ArrayList<Integer> Neighbor;
		while(!free_Vertex.isEmpty()) {
			int s = free_Vertex.get(0);
			free_Vertex.remove(0);
			St.add(s);
			Neighbor = getNeighbor(St,EqualityGraph);
			// check Nl(s) == T 
			// if Nl(s) != T choose y = Nl(s) - T
			// else calculate al 
		}
	}
}
