package sselab;

public class String_Parser {
	private String input_;
	
	public String_Parser(String input) {
		input_ = input;
	}
	
	public CFG Get_CFG() {
		String[] all_node = input_.split(";");
		CFG cfg = new CFG(all_node.length);
		for(int idx =0;idx<all_node.length;idx++) {
			String[] token = all_node[idx].split(",");
			CFG_Node node = new CFG_Node(idx+1,token[0]);
			if(token.length > 1) {
				if(!token[1].isEmpty()) node.setStartLine(token[1]);
				if(!token[2].isEmpty()) node.setStartCol(token[2]);
				if(!token[3].isEmpty()) node.setFinishLine(token[3]);
				if(!token[4].isEmpty()) node.setFinishCol(token[4]);
				if(!token[5].isEmpty()) node.setEndstructurenode(token[5]);
				for(int i=6;i<token.length;i++) {
					if(!token[i].isEmpty())
						node.add_Edge(Integer.parseInt(token[i]));
				}
			}
			cfg.add_node(node);
		}
		cfg.make_indege();
		return cfg;
	}
}
