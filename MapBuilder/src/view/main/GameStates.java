package view.main;

public enum GameStates {

	EDIT;

	public static GameStates gameState = EDIT;

	public static void SetGameState(GameStates state) {
		gameState = state;
	}

}
