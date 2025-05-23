package mage.abilities.effects.common;

import mage.abilities.Ability;
import mage.abilities.Mode;
import mage.abilities.effects.OneShotEffect;
import mage.cards.Cards;
import mage.cards.CardsImpl;
import mage.constants.Outcome;
import mage.game.Game;
import mage.players.Player;
import mage.util.CardUtil;

/**
 * @author TheElk801
 */
public class TargetPlayerShufflesTargetCardsEffect extends OneShotEffect {

    private final int targetPlayerIndex;

    public TargetPlayerShufflesTargetCardsEffect() {
        this(0);
    }

    public TargetPlayerShufflesTargetCardsEffect(int targetPlayerIndex) {
        super(Outcome.Neutral);
        this.targetPlayerIndex = targetPlayerIndex;
    }

    private TargetPlayerShufflesTargetCardsEffect(final TargetPlayerShufflesTargetCardsEffect effect) {
        super(effect);
        this.targetPlayerIndex = effect.targetPlayerIndex;
    }

    @Override
    public TargetPlayerShufflesTargetCardsEffect copy() {
        return new TargetPlayerShufflesTargetCardsEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player targetPlayer = game.getPlayer(source.getTargets().get(targetPlayerIndex).getFirstTarget());
        Cards cards = new CardsImpl(source.getTargets().get(targetPlayerIndex + 1).getTargets());
        if (targetPlayer != null && !cards.isEmpty()) {
            return targetPlayer.shuffleCardsToLibrary(cards, game, source);
        }
        return false;
    }

    @Override
    public String getText(Mode mode) {
        if (staticText != null && !staticText.isEmpty()) {
            return staticText;
        }
        String rule = "target player shuffles ";
        int targetNumber = mode.getTargets().get(targetPlayerIndex + 1).getMaxNumberOfTargets();
        if (targetNumber == Integer.MAX_VALUE) {
            rule += "any number of target cards";
        } else {
            rule += "up to " + CardUtil.numberToText(targetNumber, "one") + " target card" + (targetNumber > 1 ? "s" : "");
        }
        return rule + " from their graveyard into their library";
    }
}
