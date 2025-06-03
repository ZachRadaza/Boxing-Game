package input;

import main.PlayerPackage;
import sprites.Player;

public class Actions{
	
	public static void action(PlayerPackage playerPackage, KeyHandler keyHandler, CursorHandler cursorHandler, MouseHandler mouseHandler){
		
		movement(playerPackage, keyHandler);
		state(playerPackage, cursorHandler);
		block(playerPackage, keyHandler);
		punch(playerPackage, mouseHandler);
		
	}
	
	private static void movement(PlayerPackage playerPackage, KeyHandler keyHandler){
		Player player = playerPackage.getPlayer();
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
	
	private static void block(PlayerPackage playerPackage, KeyHandler keyHandler){
		Player player = playerPackage.getPlayer();
		boolean blockK = keyHandler.getBlock();
		boolean blockP = player.getBlock();
		
		if(blockK && !blockP){ 
			playerPackage.setStance(player.getStance() + 1);
			player.setBlock(true);
		}
		else if(!blockK && blockP){ 
			if(player.getStance() != 0) {
				playerPackage.setStance(player.getStance() - 1);
				player.setBlock(false);
			}
		}
	}
	
	private static void state(PlayerPackage playerPackage, CursorHandler cursorHandler){
		Player player = playerPackage.getPlayer();
		int handlerState = cursorHandler.getState() * 2;
		
		if(player.getStanceL() && player.getStance() > 1) playerPackage.setStance(0); //so they can stay at a state for too long, except block at standard
		
		if(!player.getBlock() && (player.getStanceLast() != handlerState)) playerPackage.setStance(handlerState);
		else if (player.getBlock() && (player.getStanceLast() != handlerState + 1)) playerPackage.setStance(handlerState + 1);
	}
	
	private static void punch(PlayerPackage playerPackage, MouseHandler mouseHandler){
		Player player = playerPackage.getPlayer();
		
		if(mouseHandler.getLeftClick()) player.setPunch(1);
		else if(mouseHandler.getRightClick()) player.setPunch(2);
		else player.setPunch(0);
	}
}