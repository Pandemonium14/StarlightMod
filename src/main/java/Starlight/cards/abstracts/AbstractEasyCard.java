package Starlight.cards.abstracts;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import Starlight.characters.StarlightSisters;
import Starlight.util.CardArtRoller;

import java.util.ArrayList;

import static Starlight.TheStarlightMod.*;
import static Starlight.util.Wiz.atb;
import static Starlight.util.Wiz.att;

public abstract class AbstractEasyCard extends CustomCard {

    protected final CardStrings cardStrings;

    public int secondMagic = -1;
    public int baseSecondMagic = -1;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    public int secondDamage = -1;
    public int baseSecondDamage = -1;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    public int info = -1;
    public int baseInfo = -1;
    public boolean upgradedInfo;
    public boolean isInfoModified;

    private float rotationTimer = 0;
    private int previewIndex;
    protected ArrayList<AbstractCard> cyclePreviewCards = new ArrayList<>();

    private boolean needsArtRefresh = false;

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, StarlightSisters.Enums.METEORITE_PURPLE_COLOR);
    }

    public AbstractEasyCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), type),
                cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        initializeTitle();
        initializeDescription();

        //CommonKeywordIconsField.useIcons.set(this, true);

        //ArtlessField.isArtless.set(this, true);

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.getAllCards() != null && !CardLibrary.getAllCards().isEmpty()) {
                CardArtRoller.computeCard(this);
                if (cardsToPreview instanceof AbstractEasyCard) {
                    CardArtRoller.computeCard((AbstractEasyCard) cardsToPreview);
                }
            } else
                needsArtRefresh = true;
        }
    }


    @Override
    protected Texture getPortraitImage() {
        if (super.getPortraitImage() == null) {
            return CardArtRoller.getPortraitTexture(this);
        }
        return super.getPortraitImage();
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
            case POWER:
            case SKILL:
                textureString = makeImagePath("cards/" + cardName + ".png");
                break;
            default:
                textureString = makeImagePath("ui/missing.png");
                break;
        }

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    @Override
    public void applyPowers() {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.applyPowers();

            secondDamage = damage;
            baseDamage = tmp;

            super.applyPowers();

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        if (baseSecondDamage > -1) {
            secondDamage = baseSecondDamage;

            int tmp = baseDamage;
            baseDamage = baseSecondDamage;

            super.calculateCardDamage(mo);

            secondDamage = damage;
            baseDamage = tmp;

            super.calculateCardDamage(mo);

            isSecondDamageModified = (secondDamage != baseSecondDamage);
        } else super.calculateCardDamage(mo);
    }

    public void resetAttributes() {
        super.resetAttributes();
        secondMagic = baseSecondMagic;
        isSecondMagicModified = false;
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
        info = baseInfo;
        isInfoModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
        if (upgradedInfo) {
            info = baseInfo;
            isInfoModified = true;
        }
    }

    protected void upgradeSecondMagic(int amount) {
        baseSecondMagic += amount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }

    protected void upgradeInfo(int amount) {
        baseInfo += amount;
        info = baseInfo;
        upgradedInfo = true;
    }

    protected void uDesc() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    protected void upgradeCardToPreview() {
        for (AbstractCard q : cyclePreviewCards) {
            q.upgrade();
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
        }
    }

    public abstract void upp();

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
            if (cardsToPreview instanceof AbstractEasyCard) {
                CardArtRoller.computeCard((AbstractEasyCard) cardsToPreview);
            }
        }
        if (!cyclePreviewCards.isEmpty()) {
            if (hb.hovered) {
                if (rotationTimer <= 0F) {
                    rotationTimer = getRotationTimeNeeded();
                    cardsToPreview = cyclePreviewCards.get(previewIndex);
                    if (previewIndex == cyclePreviewCards.size() - 1) {
                        previewIndex = 0;
                    } else {
                        previewIndex++;
                    }
                } else {
                    rotationTimer -= Gdx.graphics.getDeltaTime();
                }
            }
        }
    }

    protected float getRotationTimeNeeded() {
        return 2.5f;
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    protected void dmg(AbstractCreature t, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(t, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void dmgTop(AbstractCreature t, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(t, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public String cardArtCopy() {
        return null;
    }

    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return null;
    }
}
