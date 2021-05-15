package controller.processors;

import models.Account;
import models.Board;
import models.Phases;
import models.Player;
import view.UserInterface;
import view.menus.Menus;

public class PlayerDuelMenuProcessor extends DuelMenuProcessor {

    public PlayerDuelMenuProcessor() {
        super(Menus.PLAYER_DUEL);
    }

    @Override
    public void gameInitialization(Account player1, Account player2, int rounds) {
        phase = Phases.DRAW;
        whoseTurn = 1;
        isSummonOrSetActionAvailable = true;
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
        this.player1Board = new Board(this.player1);
        this.player2Board = new Board(this.player2);
    }

    @Override
    public void execute() {
        String command = getActingPlayer().getCommand("duel");
        String[] dividedCommand = commandHandler(command);
        String response = commandDistributor(Integer.parseInt(dividedCommand[0]), dividedCommand[1]);
        UserInterface.returnResponse(response);

        if (!checkForDuelEnd()) execute();
        else endDuel();
    }
}
