package Starlight.cards.bookOfFire;

import Starlight.cards.abstracts.AbstractMagickCard;
import Starlight.powers.BurnPower;
import Starlight.util.CardArtRoller;
import Starlight.util.CustomTags;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.purple.Worship;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static Starlight.TheStarlightMod.makeID;

public class WillOWisp extends AbstractMagickCard {
    public final static String ID = makeID(WillOWisp.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int EFFECT = 10;
    private static final int UP_EFFECT = 4;

    public WillOWisp() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = EFFECT;
        exhaust = true;
        tags.add(CustomTags.STARLIGHT_FIRE);
        tags.add(CustomTags.STARLIGHT_APPLIES_BURN);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new BurnPower(m, p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UP_EFFECT);
    }

    @Override
    public String cardArtCopy() {
        return Worship.ID;
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.45f, 0.55f, 0.35f, 0.45f, false);
    }

}