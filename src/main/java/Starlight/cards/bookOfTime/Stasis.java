package Starlight.cards.bookOfTime;

import Starlight.cards.abstracts.AbstractMagickCard;
import Starlight.powers.SpellPower;
import Starlight.util.CustomTags;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Expertise;
import com.megacrit.cardcrawl.cards.red.BattleTrance;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Starlight.TheStarlightMod.makeID;

public class Stasis extends AbstractMagickCard {
    public final static String ID = makeID(Stasis.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;

    private static final int COST = 1;
    private static final int BLK = 5;
    private static final int UP_BLK = 2;
    private static final int SP = 4;
    private static final int UP_SP = 2;

    public Stasis() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = block = BLK;
        baseMagicNumber = magicNumber = SP;
        tags.add(CustomTags.STARLIGHT_TIME);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.applyToSelf(new SpellPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(UP_BLK);
        upgradeMagicNumber(UP_SP);
    }

    @Override
    public String cardArtCopy() {
        return BattleTrance.ID;
    }
}