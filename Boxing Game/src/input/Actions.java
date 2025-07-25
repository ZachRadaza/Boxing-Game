package input;

import main.PlayerPackage;
import sprites.Player;

public class Actions{
	//single player
	public static void action(PlayerPackage playerPackage, KeyHandler keyHandler, CursorHandler cursorHandler, PlayerPackage opponent){
		
		if(playerPackage.getPlayer().getHealth() <= 0) return;
		
		movement(playerPackage, keyHandler, opponent);
		
		if(playerPackage.getPlayer().getPunchL()){
			stance(playerPackage, cursorHandler);
			block(playerPackage, keyHandler);
			punch(playerPackage, cursorHandler, keyHandler, opponent);
		}
		
	}
	//2 player
	public static void action(PlayerPackage playerPackage, KeyHandler2Player keyHandler, PlayerPackage opponent, int i){
		
		if(playerPackage.getPlayer().getHealth() <= 0) return;
		
		movement(playerPackage, keyHandler, opponent, i);
		
		if(playerPackage.getPlayer().getPunchL()){
			stance(playerPackage, keyHandler, i);
			block(playerPackage, keyHandler, i);
			punch(playerPackage, keyHandler, opponent, i);
		}
		
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
		if(keyHandler.getRight(i) && player.getBody().getRight()){
			player.updateCoords(x + 2, y);
		}
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
				if(player.getStance() > 7) player.setDodge(true);
			}
			else if(!blockK && blockP){ 
				if(player.getStance() != 0) {
					playerPackage.setStance(player.getStance() - 1);
					player.setBlock(false);
					player.setDodge(false);
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
				if(player.getStance() > 7) player.setDodge(true);
			}
			else if(!blockK && blockP){ 
				if(player.getStance() != 0) {
					playerPackage.setStance(player.getStance() - 1);
					player.setBlock(false);
					player.setDodge(false);
				}
			}
		}
	}
	
	private static void stance(PlayerPackage playerPackage, CursorHandler cursorHandler){
		Player player = playerPackage.getPlayer();
		int handlerState = cursorHandler.getStance() * 8;
		if(player.getPunchL()){
		
			if(!player.getBlock()) playerPackage.setStance(handlerState);
			else if (player.getBlock()) playerPackage.setStance(handlerState + 1);
		}
		
		player.adjustStamina(player.getStaminaRegen());
	}
	
	private static void stance(PlayerPackage playerPackage, KeyHandler2Player cursorHandler, int i){
		Player player = playerPackage.getPlayer();
		int handlerState = cursorHandler.getStance(i) * 8;

		if(player.getPunchL()){

			if(!player.getBlock()) playerPackage.setStance(handlerState);
			else if (player.getBlock()) playerPackage.setStance(handlerState + 1);
		}
		
		player.adjustStamina(player.getStaminaRegen());
	}
	
	private static void punch(PlayerPackage playerPackage, CursorHandler cursorHandler, KeyHandler keyHandler, PlayerPackage opponent){
		Player player = playerPackage.getPlayer();
		
		int i = 0;
		int damage = 0;
		boolean hook = false;
		
		if(!cursorHandler.getLeftClick() && !cursorHandler.getRightClick()){
			player.setPunch(0);
		} else {
			if(cursorHandler.getLeftClick() && keyHandler.getShift()){
				i = 4;
				damage = 7;
				hook = true;
			} else if(cursorHandler.getRightClick() && keyHandler.getShift()){
				i = 5;
				damage = 7;
				hook = true;
			} else if(cursorHandler.getLeftClick() && keyHandler.getBlock()){
				i = 5;
				damage = 15;
			} else if(cursorHandler.getRightClick() && keyHandler.getBlock()){
				i = 6;
				damage = 15;
			} else if(cursorHandler.getLeftClick()){ 
				i = 2;
				damage = 3;
			} else if(cursorHandler.getRightClick()){
				i = 3;
				damage = 11;
			}
			
			if(!player.canPunch(i)) return;
			
			if(opponent.getPlayer().getBlock()) damage /= 2;
			
			if(player.getStanceSimple() != opponent.getPlayer().getStanceSimple()) if(!hook || i != 2) damage = 0;
			
			if(opponent.getPlayer().getDodge()){
				damage = 0;
				opponent.getPlayer().setDodge(false);
			}
			
			if(player.getStanceSimple() != opponent.getPlayer().getStanceSimple()) damage = 0;
			
			player.setPunch(i);
			
			playerPackage.setStance(player.getStance() + i);
			
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
		boolean hook = false;
		
		if(!keyHandler.getPunchLeft(i) && !keyHandler.getPunchRight(i)){
			player.setPunch(0);
		} else {
			if(keyHandler.getPunchLeft(i) && keyHandler.getShift(i)){
				j = 4;
				damage = 7;
				hook = true;
			} else if(keyHandler.getPunchRight(i) && keyHandler.getShift(i)){
				j = 5;
				damage = 7;
				hook = true;
			} else if(keyHandler.getPunchLeft(i) && keyHandler.getBlock(i)){
				j = 5;
				damage = 15;
			} else if(keyHandler.getPunchRight(i) && keyHandler.getBlock(i)){
				j = 6;
				damage = 15;
			} else if(keyHandler.getPunchLeft(i)){ 
				j = 2;
				damage = 3;
			} else if(keyHandler.getPunchRight(i)){
				j = 3;
				damage = 11;
			}
			
			if(!player.canPunch(j)) return;
			
			if(opponent.getPlayer().getBlock()) damage /= 2;
			
			if(player.getStanceSimple() != opponent.getPlayer().getStanceSimple()) if(!hook || j != 2) damage = 0;
			
			if(opponent.getPlayer().getDodge()){
				damage = 0;
				opponent.getPlayer().setDodge(false);
			}
			
			player.setPunch(j);
			
			playerPackage.setStance(player.getStance() + j);
			
			if(player.getHands().collisionDetection(opponent.getPlayer().getColBox())){ 
				opponent.getPlayer().adjustHealth(-damage);
				opponent.adjustHealth();
			}
			
		}
		
		playerPackage.adjustStamina();
		
	}
}