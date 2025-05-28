package input;

import sprites.Player;

public class Actions{
	
	
	public static void action(Player player, KeyHandler keyHandler, CursorHandler cursorHandler, MouseHandler mouseHandler){
		movement(player, keyHandler);
		state(player, cursorHandler);
		block(player, keyHandler);
		punch(player, mouseHandler);
		
	}
	
	private static void movement(Player player, KeyHandler keyHandler){
		int x = player.getVector2D().getX();
		int y = player.getVector2D().getY();
		
		if(!player.getDashL()){ 
			if(player.getDashDir()) player.updateCoords(x + 20, y);
			else player.updateCoords(x - 20, y);
		
		}
		if(keyHandler.getUp()) { 
			if(player.getDashCD()){
				player.updateCoords(x + 20, y);
				player.resetDashCD();
				player.resetDashL();
				player.setDashDir(true);
			}
		}
		if(keyHandler.getDown()) {
			if(player.getDashCD()){
				player.updateCoords(x - 20, y);
				player.resetDashCD();
				player.resetDashL();
				player.setDashDir(false);
			}
		}
		if(keyHandler.getRight()) player.updateCoords(x + 2, y);
		if(keyHandler.getLeft()) player.updateCoords(x - 2, y);
	}
	
	private static void block(Player player, KeyHandler keyHandler){
		boolean blockK = keyHandler.getBlock();
		boolean blockP = player.getBlock();
		if(blockK && !blockP){ 
			player.setState(player.getState() + 1);
			player.setBlock(true);
		}
		else if(!blockK && blockP){ 
			player.setState(player.getState() - 1);
			player.setBlock(false);
		}
	}
	
	private static void state(Player player, CursorHandler cursorHandler){
		if(!player.getBlock()) player.setState(cursorHandler.getState() * 2);
		else player.setState((cursorHandler.getState() * 2) + 1);
	}
	
	private static void punch(Player player, MouseHandler mouseHandler){
		if(mouseHandler.getLeftClick()) player.setPunch(1);
		else if(mouseHandler.getRightClick()) player.setPunch(2);
		else player.setPunch(0);
	}
}