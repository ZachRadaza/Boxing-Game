package input;

import main.PlayerPackage;
import sprites.Player;

public class Actions{
	
	public static void action(PlayerPackage playerPackage, KeyHandler keyHandler, CursorHandler cursorHandler, PlayerPackage opponent){
		
		movement(playerPackage, keyHandler, opponent);
		
		if(playerPackage.getPlayer().getPunchL()){
			stance(playerPackage, cursorHandler);
			block(playerPackage, keyHandler);
		}
		
		punch(playerPackage, cursorHandler, keyHandler, opponent);
	}
	
	private static void movement(PlayerPackage playerPackage, KeyHandler keyHandler, PlayerPackage opponent){
		Player player = playerPackage.getPlayer();
		int x = player.getVector2D().getX();
		int y = player.getVector2D().getY();
		
		player.getBody().collisionDetection(opponent.getPlayer().getBody());
		
		if(!player.getDashL()){ 
			if(player.getDashDir() && player.getBody().getRight()) player.updateCoords(x + 20, y);
			else if(!player.getDashDir() && player.getBody().getLeft()) player.updateCoords(x - 20, y);
		
		}
		if(keyHandler.getUp()) { 
			if(player.getDashCD() && player.getBody().getRight()){
				player.updateCoords(x + 20, y);
				player.resetDashCD();
				player.resetDashL();
				player.setDashDir(true);
			}
		}
		if(keyHandler.getDown()) {
			if(player.getDashCD() && player.getBody().getLeft()){
				player.updateCoords(x - 20, y);
				player.resetDashCD();
				player.resetDashL();
				player.setDashDir(false);
			}
		}
		if(keyHandler.getRight() && player.getBody().getRight()) player.updateCoords(x + 2, y);
		if(keyHandler.getLeft() && player.getBody().getLeft()) player.updateCoords(x - 2, y);
	}
	
	private static void block(PlayerPackage playerPackage, KeyHandler keyHandler){
		Player player = playerPackage.getPlayer();
		boolean blockK = keyHandler.getBlock();
		boolean blockP = player.getBlock();
		
		if(player.getPunchL()){
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
	}
	
	private static void stance(PlayerPackage playerPackage, CursorHandler cursorHandler){
		Player player = playerPackage.getPlayer();
		int handlerState = cursorHandler.getStance() * 8;
		if(player.getPunchL()){
			if(player.getStanceL() && player.getStance() > 1) playerPackage.setStance(0); //so they can stay at a state for too long, except block at standard
		
			if(!player.getBlock() && (player.getStanceLast() != handlerState)) playerPackage.setStance(handlerState);
			else if (player.getBlock() && (player.getStanceLast() != handlerState + 1)) playerPackage.setStance(handlerState + 1);
		}
	}
	
	private static void punch(PlayerPackage playerPackage, CursorHandler cursorHandler, KeyHandler keyHandler, PlayerPackage opponent){
		Player player = playerPackage.getPlayer();
		
		int i = 0;
		int damage = 0;
		if(cursorHandler.getLeftClick() && cursorHandler.getRightClick()){
			player.setPunch(0);
		} else {
			if(cursorHandler.getLeftClick() && keyHandler.getShift()){
				i = 4;
				damage = 15;
			} else if(cursorHandler.getRightClick() && keyHandler.getShift()){
				i = 5;
				damage = 15;
			} else if(cursorHandler.getLeftClick() && keyHandler.getBlock()){
				i = 5;
				damage = 15;
			} else if(cursorHandler.getRightClick() && keyHandler.getBlock()){
				i = 6;
				damage = 15;
			} else if(cursorHandler.getLeftClick()){ 
				i = 2;
				damage = 5;
			} else if(cursorHandler.getRightClick()){
				i = 3;
				damage = 10;
			}
			
			if(player.getPunchL()) playerPackage.setStance(player.getStance() + i);
			player.setPunch(i);
			
			if(player.getHands().collisionDetection(opponent.getPlayer().getColBox())){ opponent.getPlayer().adjustHealth(-damage);
				System.out.println("hit");
			}
		}
	}
}