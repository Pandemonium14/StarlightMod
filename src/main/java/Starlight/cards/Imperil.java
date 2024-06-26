package Starlight.cards;

import Starlight.cards.abstracts.AbstractEasyCard;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.blue.GoForTheEyes;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.ScrapeEffect;

import static Starlight.TheStarlightMod.makeID;

public class Imperil extends AbstractEasyCard {
    public final static String ID = makeID(Imperil.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;
    private static final int DMG = 5;
    private static final int UP_DMG = 2;
    private static final int EFFECT = 1;
    private static final int UP_EFFECT = 1;

    public Imperil() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = damage = DMG;
        baseMagicNumber = magicNumber = EFFECT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {// 40
            this.addToBot(new VFXAction(new ScrapeEffect(m.hb.cX, m.hb.cY), 0.1F));
        }
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        Wiz.applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        //upgradeDamage(UP_DMG);
        upgradeMagicNumber(UP_EFFECT);
    }

    @Override
    public String cardArtCopy() {
        return GoForTheEyes.ID;
    }
}