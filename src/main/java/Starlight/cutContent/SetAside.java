package Starlight.cutContent;

import Starlight.cards.abstracts.AbstractEasyCard;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.purple.EmptyFist;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Starlight.TheStarlightMod.makeID;

public class SetAside extends AbstractEasyCard {
    public final static String ID = makeID(SetAside.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;
    private static final int DMG = 8;
    private static final int UP_DMG = 1;
    private static final int EFFECT = 1;
    private static final int UP_EFFECT = 1;

    public SetAside() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = damage = DMG;
        baseMagicNumber = magicNumber = EFFECT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.atb(new DiscardAction(p, p, magicNumber, false));
        /*Wiz.atb(new ScryAction(magicNumber));
        Wiz.atb(new ScryFollowUpAction(cards -> {
            if (cards.size() > 0) {
                Wiz.applyToSelf(new ProvidencePower(p, cards.size()));
            }
        }));*/
    }

    public void upp() {
        upgradeDamage(UP_DMG);
        upgradeMagicNumber(UP_EFFECT);
    }

    @Override
    public String cardArtCopy() {
        return EmptyFist.ID;
    }
}