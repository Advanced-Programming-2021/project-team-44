package controller.processors;

import controller.Core;
import models.Account;
import models.Player;
import view.UserInterface;
import view.menus.Menus;

public class PlayerDuelMenuProcessor extends DuelMenuProcessor {

    public PlayerDuelMenuProcessor() {
        super(Menus.PLAYER_DUEL);
    }

    @Override
    public void gameInitialization(Account player1, Account player2, int rounds) {
        allRounds = remainingRounds = rounds;
        this.player1 = new Player(player2);
        this.player2 = new Player(player1);
        newRoundInitializer();
    }

    @Override
    public void execute() {
        executeRound();
        endGame();
        Core.currentMenu = Menus.MAIN;
    } //done

    @Override
    public void executeRound() {
        showBoard(); //Before command
        executeTurn();
        showBoard(); //After command
        if (hasAnyoneSurrendered) return;
        if (ifRoundHasEnded()) {
            if (remainingRounds > 0
                    && !(allRounds == 3
                    && (player1.getRoundsWon() == 2
                    || player2.getRoundsWon() == 2))) {
                newRoundInitializer();
                //TODO check for card swap between decks
                executeRound();
            } else return;
        }
        executeRound();
    } //done

    @Override
    public void executeTurn() {
        String command = getActingPlayer().getCommand();
        String[] dividedCommand = commandHandler(command);
        String response = process(Integer.parseInt(dividedCommand[0]), dividedCommand[1]);
        UserInterface.returnResponse(response);
    } //done

    @Override
    public void endGame() {
        //Giving money and score
        if (player1.getRoundsWon() > player2.getRoundsWon()) {
            coinPayer(player1, player2);
            System.out.println("\n" + player1.getAccount().getUsername()
                    + " won the whole match with score: "
                    + player1.getScore()
                    + " - "
                    + player2.getScore());
        } else {
            coinPayer(player2, player1);
            System.out.println("\n" + player2.getAccount().getUsername()
                    + " won the whole match with score: "
                    + player2.getScore()
                    + " - "
                    + player1.getScore());
        }
    } //done

    @Override
    public void endRound(Player winner, Player loser) {
        winner.winsRound();
        ifRoundHasEnded = true;
        System.out.println("\n" + winner.getAccount().getUsername()
                + " won the match with score: "
                + winner.getScore()
                + " - "
                + loser.getScore());
    } //done

    private void coinPayer(Player winner, Player loser) {
        if (allRounds == 1) {
            winner.getAccount().increaseScore(1000);

            winner.getAccount().increaseCoin(1000 + winner.getMaxLp());
            loser.getAccount().increaseCoin(100);
        } else {
            winner.getAccount().increaseScore(3000);

            winner.getAccount().increaseCoin(3000 + 3 * winner.getMaxLp());
            loser.getAccount().increaseCoin(300);
        }
    } //done
}
