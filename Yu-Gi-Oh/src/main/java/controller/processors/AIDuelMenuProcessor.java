package controller.processors;

import controller.Core;
import models.Account;
import models.Phases;
import models.Player;
import view.menus.Menus;

public class AIDuelMenuProcessor extends DuelMenuProcessor {
    private int whichPlayerIsAI;

    public AIDuelMenuProcessor() {
        super(Menus.AI_DUEL);
    }

    @Override
    public void gameInitialization(Account player1, Account player2, int rounds) {
        phase = Phases.DRAW;
        whoseTurn = 1;
        if (player1 == null) {
            this.player1 = null;
            this.player2 = new Player(player2);
            whichPlayerIsAI = 1;
        }
        if (player2 == null) {
            this.player1 = new Player(player1);
            this.player2 = null;
            whichPlayerIsAI = 2;
        }
    }

    @Override
    public void execute() {

    }

    private Player getPlayerByNumber(int number) {
        if (number == 1) return this.player1;
        else return this.player2;
    }
}
