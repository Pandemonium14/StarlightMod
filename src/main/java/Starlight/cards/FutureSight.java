package Starlight.cards;

import Starlight.actions.ProjectTopCardAction;
import Starlight.cards.abstracts.AbstractEasyCard;
import Starlight.cards.interfaces.TagTeamCard;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Starlight.TheStarlightMod.makeID;

public class FutureSight extends AbstractEasyCard {
    public final static String ID = makeID(FutureSight.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int EFFECT = 2;
    private static final int UP_EFFECT = 1;

    public FutureSight() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = EFFECT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ScryAction(magicNumber));
        Wiz.atb(new ProjectTopCardAction(1));
    }

    public void upp() {
        upgradeMagicNumber(UP_EFFECT);
    }
}