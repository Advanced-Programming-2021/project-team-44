package controller.processors;

import models.Account;
import models.Phases;
import models.Player;
import view.UserInterface;
import view.menus.Menus;

import java.util.ArrayList;

public class PlayerDuelMenuProcessor extends DuelMenuProcessor {

    public PlayerDuelMenuProcessor() {
        super(Menus.PLAYER_DUEL);
    }

    @Override
    public void gameInitialization(Account player1, Account player2, int rounds) {
        phase = Phases.DRAW;
        whoseTurn = 1;
        remainingRounds = rounds;
        isSummonOrSetActionAvailable = true;
        monsterActiveContinuousEffects = new ArrayList<>();
        this.player1 = new Player(player1);
        this.player2 = new Player(player2);
    }

    @Override
    public void execute() {
        String command = getActingPlayer().getCommand("duel");
        String[] dividedCommand = commandHandler(command);
        String response = process(Integer.parseInt(dividedCommand[0]), dividedCommand[1]);
        UserInterface.returnResponse(response);

        if (!ifDuelHasEnded()) execute();
        else endDuel(getWinner(), getLoser());
    }
}
