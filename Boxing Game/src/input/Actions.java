package input;

import main.PlayerPackage;
import sprites.Player;

public class Actions{
	//single player
	public static void action(PlayerPackage playerPackage, KeyHandler keyHandler, CursorHandler cursorHandler, PlayerPackage opponent){
		
		movement(playerPackage, keyHandler, opponent);
		
		if(playerPackage.getPlayer().getPunchL()){
			stance(playerPackage, cursorHandler);
			block(playerPackage, keyHandler);
		}
		
		punch(playerPackage, cursorHandler, keyHandler, opponent);
	}
	//2 player
	public static void action(PlayerPackage playerPackage, KeyHandler2Player keyHandler, PlayerPackage opponent, int i){
		
		movement(playerPackage, keyHandler, opponent, i);
		
		if(playerPackage.getPlayer().getPunchL()){
			stance(playerPackage, keyHandler, i);
			block(playerPackage, keyHandler, i);
		}
		
		punch(playerPackage, keyHandler, opponent, i);
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
	
	private static void movement(PlayerPackage playerPackage, KeyHandler2Player keyHandler, PlayerPackage opponent, int i){
		Player player = playerPackage.getPlayer();
		int x = player.getVector2D().getX();
		int y = player.getVector2D().getY();
		
		player.getBody().collisionDetection(opponent.getPlayer().getBody());
		
		if(!player.getDashL()){ 
			if(player.getDashDir() && player.getBody().getRight()) player.updateCoords(x + 20, y);
			else if(!player.getDashDir() && player.getBody().getLeft()) player.updateCoords(x - 20, y);
		
		}
		if(keyHandler.getUp(i)) { 
			if(player.getDashCD() && player.getBody().getRight()){
				player.updateCoords(x + 20, y);
				player.resetDashCD();
				player.resetDashL();
				player.setDashDir(true);
			}
		}
		if(keyHandler.getDown(i)) {
			if(player.getDashCD() && player.getBody().getLeft()){
				player.updateCoords(x - 20, y);
				player.resetDashCD();
				player.resetDashL();
				player.setDashDir(false);
			}
		}
		if(keyHandler.getRight(i) && player.getBody().getRight()) player.updateCoords(x + 2, y);
		if(keyHandler.getLeft(i) && player.getBody().getLeft()) player.updateCoords(x - 2, y);
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
	
	private static void block(PlayerPackage playerPackage, KeyHandler2Player keyHandler, int i){
		Player player = playerPackage.getPlayer();
		boolean blockK = keyHandler.getBlock(i);
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
		
		player.adjustStamina(player.getStaminaRegen());
	}
	
	private static void stance(PlayerPackage playerPackage, KeyHandler2Player cursorHandler, int i){
		Player player = playerPackage.getPlayer();
		int handlerState = cursorHandler.getStance(i) * 8;

		if(player.getPunchL()){
			if(player.getStanceL() && player.getStance() > 1) playerPackage.setStance(0); //so they can stay at a state for too long, except block at standard
		
			if(!player.getBlock() && (player.getStanceLast() != handlerState)) playerPackage.setStance(handlerState);
			else if (player.getBlock() && (player.getStanceLast() != handlerState + 1)) playerPackage.setStance(handlerState + 1);
		}
		
		player.adjustStamina(player.getStaminaRegen());
	}
	
	private static void punch(PlayerPackage playerPackage, CursorHandler cursorHandler, KeyHandler keyHandler, PlayerPackage opponent){
		Player player = playerPackage.getPlayer();
		
		int i = 0;
		int damage = 0;
		if(!cursorHandler.getLeftClick() && !cursorHandler.getRightClick()){
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
			
			if(player.getHands().collisionDetection(opponent.getPlayer().getColBox())){ 
				opponent.getPlayer().adjustHealth(-damage);
				opponent.adjustHealth();
			}
		}
		
		playerPackage.adjustStamina();
	}
	
	private static void punch(PlayerPackage playerPackage, KeyHandler2Player keyHandler, PlayerPackage opponent, int i){
		Player player = playerPackage.getPlayer();
		
		int j = 0;
		int damage = 0;
		if(!keyHandler.getPunchLeft(i) && !keyHandler.getPunchRight(i)){
			player.setPunch(0);
		} else {
			if(keyHandler.getPunchLeft(i) && keyHandler.getShift(i)){
				j = 4;
				damage = 15;
			} else if(keyHandler.getPunchRight(i) && keyHandler.getShift(i)){
				j = 5;
				damage = 15;
			} else if(keyHandler.getPunchLeft(i) && keyHandler.getBlock(i)){
				j = 5;
				damage = 15;
			} else if(keyHandler.getPunchRight(i) && keyHandler.getBlock(i)){
				j = 6;
				damage = 15;
			} else if(keyHandler.getPunchLeft(i)){ 
				j = 2;
				damage = 5;
			} else if(keyHandler.getPunchRight(i)){
				j = 3;
				damage = 10;
			}
			
			if(player.getPunchL()) playerPackage.setStance(player.getStance() + j);
			
			player.setPunch(j);
			
			if(player.getHands().collisionDetection(opponent.getPlayer().getColBox())){ 
				opponent.getPlayer().adjustHealth(-damage);
				opponent.adjustHealth();
			}
			
			if(opponent.getPlayer().getHealth() <= 0){
				//game over
			}
		}
		
		playerPackage.adjustStamina();
		
	}
}