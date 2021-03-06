package sselab;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class CFGSim {
	int[][] costMatrix;
	static int INF_ = 1000;
	private CFG A, B;
	int n,m;

<<<<<<< HEAD
	int[] lx,ly,EGx_y,EGy_x,S,slackY,slack_x;
	boolean[] T;
=======
	int[] lx,ly,EGx_y,EGy_x,S,T,slackY,slack_x;
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
	int idx;
	int match_cnt;
	int s_front,s_rear;
	
	public CFGSim(CFG a,CFG b) {
		this.A = a;
		this.B = b;
		n = A.getsize();
		m = B.getsize();
		lx = new int[n+m+1];
		ly = new int[n+m+1];
		EGx_y = new int[n+m+1];
		EGy_x = new int[n+m+1];
		S = new int[n+m+1]; 
<<<<<<< HEAD
		T = new boolean[n+m+1];
=======
		T = new int[n+m+1];
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
		slackY = new int[n+m+1];
		slack_x = new int[n+m+1];
		idx = 0; match_cnt = 0; 
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
		for(int i=1;i<=n+m;i++) {
			for(int j=1;j<=n+m;j++) {
				System.out.printf("%d ", costMatrix[i][j]);
			}
			System.out.println();
		}
	}
	
	void init_S_T(){
		s_front = 1; s_rear=1;
<<<<<<< HEAD
		Arrays.fill(T,false);
=======
		Arrays.fill(T,0);
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
		for(int i=1;i<=n+m;i++){
			if(EGx_y[i] == -1){
				S[s_rear++] = i; break;
			}
		}
	}
	
	void slack_update(int x){
		int delta;
		for(int i=1;i<=n+m;i++){
			delta = lx[x] + ly[i] - costMatrix[x][i];
<<<<<<< HEAD
			if(!T[i] && slackY[i] > delta){
=======
			if(T[i] == 0 && slackY[i] > delta){
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
				slackY[i] = delta;
				slack_x[i] = x;
			}
		}
	}
	
	int find_in_slack(){
<<<<<<< HEAD
		int min_ = INF_+1;
		int idx = -1;
		for(int i=1;i<=n+m;i++){
			if(!T[i] && slackY[i] >= 0 && min_ > slackY[i]){
=======
		int min_ = INF_;
		int idx = -1;
		for(int i=1;i<=n+m;i++){
			if(T[i] == 0 && slackY[i] >= 0 && min_ > slackY[i]){
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
				min_ = slackY[i];
				idx = i;
			}
		}
		return idx;
	}
	
	void update_labels(int delta){
		for(int i=1;i<=n+m;i++){
			if(S[i] > 0){
				lx[i] -= delta;
			}
<<<<<<< HEAD
			if(T[i]){
				ly[i] += delta;
			}
		}
		for(int i=1;i<=n+m;i++) {
			if(!T[i]) {
=======
			if(T[i] > 0){
				ly[i] += delta;
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
				slackY[i] -= delta;
			}
		}
	}
	
	void reconstruct_matching(int x,int y){
		if(EGx_y[x] != -1){
			int t = EGx_y[x];
			EGx_y[x] = y;
			EGy_x[y] = x;
			reconstruct_matching(slack_x[t],t);
		}
		else{
<<<<<<< HEAD
			EGx_y[x] = y;
=======
			EGx_y[x] =y;
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
			EGy_x[y] = x;
		}
	}
	
	void augment(){
<<<<<<< HEAD
		int x=1,y=1;
=======
		int x=0,y=0;
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
		Arrays.fill(slackY,INF_);
		Arrays.fill(slack_x,-1);
		
		while(s_front < s_rear){
			slack_update(S[s_front++]);
			
			y = find_in_slack();
			x = slack_x[y];
			
			if(EGy_x[y] == -1) break;
			
			update_labels(slackY[y]);
			
			S[s_rear++] = EGy_x[y];
<<<<<<< HEAD
			T[y] = true;
=======
			T[y] = 1;
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
		}
		reconstruct_matching(x,y);
		match_cnt++;
	}
	
	void hungrian_alg() {
		//initial step
<<<<<<< HEAD
		Arrays.fill(EGx_y,-1);
		Arrays.fill(EGy_x,-1);
		for(int i=1;i<=n+m;i++) {
			lx[i] = INF_+1;
			for(int j=1;j<=n+m;j++){
				if(lx[i] > costMatrix[i][j]){
=======
		Arrays.fill(lx,INF_);
		Arrays.fill(ly,0);
		Arrays.fill(EGx_y,-1);
		Arrays.fill(EGy_x,-1);
		for(int i=1;i<=n+m;i++) {
			for(int j=1;j<=n+m;j++){
				if(lx[i] < costMatrix[i][j]){
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
					lx[i] = costMatrix[i][j];
					idx = j;
				}
			}
			if(EGy_x[idx] == -1){
				EGx_y[i] = idx;
				EGy_x[idx] = i;
				match_cnt++;
<<<<<<< HEAD
			}
		}

		for(int i=1;i<=n+m;i++) {
			System.out.printf("%d ", EGx_y[i]);
		}
		System.out.println();
		while(match_cnt < n+m){
			init_S_T();
			augment();
			for(int i=1;i<=n+m;i++) {
				System.out.printf("%d ", EGx_y[i]);
=======
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
			}
			System.out.println();
		}
<<<<<<< HEAD
		for(int i=1;i<=n+m;i++) {
			System.out.printf("%d ", costMatrix[i][EGx_y[i]]);
=======
		
		while(match_cnt <= n+m){
			init_S_T();
			augment();
>>>>>>> b94e0e3fd04713abc6a4acb2e50510e97d7e88a2
		}
		System.out.println();
	}
}