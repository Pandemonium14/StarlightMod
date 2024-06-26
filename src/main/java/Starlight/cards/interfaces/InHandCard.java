package Starlight.cards.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public interface InHandCard {
    default void onCardUsed(AbstractCard card){}
    default void onCardDrawn(AbstractCard card){}
    default void onCardExhausted(AbstractCard card){}
    default void onPowerApplied(AbstractPower power, AbstractCreature target, AbstractCreature source){}
    default float atDamageGive(float dmg, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        return dmg;
    }
    default float atDamageFinalGive(float dmg, DamageInfo.DamageType type, AbstractCreature target, AbstractCard card) {
        return dmg;
    }
    default float onModifyBlock(float blk, AbstractCard card) {
        return blk;
    }
    default float onModifyBlockFinal(float blk, AbstractCard card) {
        return blk;
    }
    default float modifyMagicMagic(float magic, AbstractCard card) {
        return magic;
    }
}
