package Starlight.cards;

import Starlight.actions.BeatUpAction;
import Starlight.cards.abstracts.AbstractEasyCard;
import Starlight.powers.CrumplePower;
import Starlight.util.CardArtRoller;
import Starlight.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.AllOutAttack;
import com.megacrit.cardcrawl.cards.purple.JustLucky;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Iterator;

import static Starlight.TheStarlightMod.makeID;

public class BeatUp extends AbstractEasyCard {
    public final static String ID = makeID(BeatUp.class.getSimpleName());

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;

    private static final int COST = 1;
    private static final int DMG = 7;
    private static final int UP_DMG = 3;

    public BeatUp() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = damage = DMG;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new BeatUpAction(m, damage, damageTypeForTurn));
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!m.isDeadOrEscaped() && m.hasPower(CrumplePower.POWER_ID)) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
                break;
            }
        }
    }

    public void upp() {
        upgradeDamage(UP_DMG);
    }

    @Override
    public String cardArtCopy() {
        return AllOutAttack.ID;
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.35f, 0.5f, 0.5f, 0.5f, false);
    }
}