package Starlight.cards.bookOfNature;

import Starlight.cards.abstracts.AbstractMagickCard;
import Starlight.powers.BarbPower;
import Starlight.util.CardArtRoller;
import Starlight.util.CustomTags;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.blue.DoomAndGloom;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Starlight.TheStarlightMod.makeID;

public class Absorb extends AbstractMagickCard {
    public final static String ID = makeID(Absorb.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 2;
    private static final int DMG = 18;
    private static final int UP_DMG = 6;
    private static final int EFFECT = 2;
    private static final int UP_EFFECT = 1;

    public Absorb() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = damage = DMG;
        baseBlock = block = 0;
        isMultiDamage = true;
        //baseMagicNumber = magicNumber = EFFECT;
        tags.add(CustomTags.STARLIGHT_NATURE);
        //tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        allDmg(AbstractGameAction.AttackEffect.POISON);
        /*Wiz.atb(new DamageFollowupAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.POISON, i -> {
            if (i > 0 && m.hasPower(TanglePower.POWER_ID)) {
                Wiz.applyToEnemy(m, new StrengthPower(m, -magicNumber));
            }
        }));
        Wiz.atb(new HealAction(p, p, magicNumber));*/
    }

    @Override
    public void applyPowers() {
        baseBlock = 0;
        AbstractPower barbs = Wiz.adp().getPower(BarbPower.POWER_ID);
        if (barbs != null) {
            baseBlock = barbs.amount;
        }
        super.applyPowers();
    }

    public void upp() {
        upgradeDamage(UP_DMG);
        //upgradeMagicNumber(UP_EFFECT);
    }

    @Override
    public String cardArtCopy() {
        return DoomAndGloom.ID;
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.05f, 0.55f, 0.55f, 0.45f, false);
    }
}