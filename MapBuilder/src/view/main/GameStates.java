package view.main;
/**
 * enum per gestire gli stati dell applicazione 
 */
public enum GameStates {

	EDIT;

	public static GameStates gameState = EDIT;
	/**
	 * questo metodo imposta gameState 
	 * @param state
	 */
	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}
