package Starlight.relics;

import Starlight.characters.StarlightSisters;
import Starlight.powers.SpellPower;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Starlight.TheStarlightMod.makeID;

public class MagicWand extends AbstractEasyRelic {
    public static final String ID = makeID(MagicWand.class.getSimpleName());

    public MagicWand() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, StarlightSisters.Enums.METEORITE_PURPLE_COLOR);
    }

    public void atBattleStart() {
        flash();
        addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int draw = 0;
                if (!Wiz.adp().drawPile.getCardsOfType(AbstractCard.CardType.SKILL).isEmpty()) {
                    AbstractCard c = Wiz.adp().drawPile.getCardsOfType(AbstractCard.CardType.SKILL).getRandomCard(true);
                    Wiz.adp().drawPile.removeCard(c);
                    Wiz.adp().drawPile.addToTop(c);
                    draw++;
                }
                if (!Wiz.adp().drawPile.getCardsOfType(AbstractCard.CardType.ATTACK).isEmpty()) {
                    AbstractCard c = Wiz.adp().drawPile.getCardsOfType(AbstractCard.CardType.ATTACK).getRandomCard(true);
                    Wiz.adp().drawPile.removeCard(c);
                    Wiz.adp().drawPile.addToTop(c);
                    draw++;
                }
                Wiz.att(new DrawCardAction(draw));
                this.isDone = true;
            }
        });
    }
}
