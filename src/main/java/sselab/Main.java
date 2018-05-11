package sselab;

/**
 * CFG similarity calculation using Hu Alg.
 *
 */
public class Main 
{
	public static void main(String args[]) {
		String_Parser Parser_cfg = new String_Parser(args[0]);
		System.out.println(args[0]);
		CFG cfg = Parser_cfg.Get_CFG();
		Parser_cfg = new String_Parser(args[1]);
		CFG cfg2 = Parser_cfg.Get_CFG();
		CFGSim sim = new CFGSim(cfg,cfg2);
		sim.make_costMatrix();
		sim.hungrian_alg();
	}
}
