package Starlight.cards.bookOfIce;

import Starlight.cards.abstracts.AbstractMagickCard;
import Starlight.powers.ChillPower;
import Starlight.util.CardArtRoller;
import Starlight.util.CustomTags;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.green.Skewer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Starlight.TheStarlightMod.makeID;

public class FrostArrow extends AbstractMagickCard {
    public final static String ID = makeID(FrostArrow.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 0;
    private static final int DMG = 3;
    private static final int UP_DMG = 1;
    private static final int EFFECT = 1;
    private static final int UP_EFFECT = 1;

    public FrostArrow() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = damage = DMG;
        baseMagicNumber = magicNumber = EFFECT;
        tags.add(CustomTags.STARLIGHT_ICE);
        tags.add(CustomTags.STARLIGHT_APPLIES_CHILL);
        tags.add(CustomTags.STARLIGHT_ARROW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        Wiz.applyToEnemy(m, new ChillPower(m, magicNumber));
    }

    public void upp() {
        upgradeDamage(UP_DMG);
        upgradeMagicNumber(UP_EFFECT);
    }

    @Override
    public String cardArtCopy() {
        return Skewer.ID;
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.03f, 0.6f, 0.75f, 0.5f, false);
    }

}