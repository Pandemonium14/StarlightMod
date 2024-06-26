package Starlight.orbs;

import Starlight.TheStarlightMod;
import Starlight.util.Wiz;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.defect.EvokeSpecificOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

public class ProjectedCardOrb extends AbstractOrb {
    // Standard ID/Description
    public static final String ORB_ID = TheStarlightMod.makeID("ProjectedCard");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESC = orbString.DESCRIPTION;

    AbstractCard card;
    private static final float IDLE_SCALE = 0.10f;
    private static final float HOVER_SCALE = 0.8f;

    public ProjectedCardOrb(AbstractCard card) {
        this.ID = ORB_ID;
        this.card = card.makeSameInstanceOf();
        this.card.current_x = AbstractDungeon.player.drawX + AbstractDungeon.player.hb_x;
        this.card.current_y = AbstractDungeon.player.drawY + AbstractDungeon.player.hb_y + AbstractDungeon.player.hb_h / 2.0F;
        this.angle = MathUtils.random(360.0F);
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESC[0];
    }

    @Override
    public void onStartOfTurn() {
        Wiz.atb(new EvokeSpecificOrbAction(this));
    }

    @Override
    public void onEvoke() {
        AbstractMonster m = AbstractDungeon.getRandomMonster();
        if (card != null && m != null) {
            AbstractCard tmp = card.makeSameInstanceOf();
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = card.current_x;
            tmp.target_y = card.current_y;
            tmp.calculateCardDamage(m);
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
        }
    }

    @Override
    public AbstractOrb makeCopy() {
        return new ProjectedCardOrb(card.makeStatEquivalentCopy());
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 10.0F;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(ImageMaster.ORB_SLOT_2, this.cX - 48.0F - this.bobEffect.y / 8.0F, this.cY - 48.0F + this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);
        sb.draw(ImageMaster.ORB_SLOT_1, this.cX - 48.0F + this.bobEffect.y / 8.0F, this.cY - 48.0F - this.bobEffect.y / 8.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        //this.renderText(sb);
        this.hb.render(sb);
        renderCardPreview(sb);
    }

    public void renderCardPreview(SpriteBatch sb) {
        if (card != null) {
            this.card.render(sb);
            if (this.hb.hovered) {
                TipHelper.renderTipForCard(card, sb, card.keywords);
            }
        }
    }

    @Override
    public void update() {
        this.hb.update();
        if (card != null) {
            card.target_x = this.cX;
            card.target_y = this.cY + this.bobEffect.y/2F;
            card.targetDrawScale = this.hb.hovered ? HOVER_SCALE : IDLE_SCALE;
            card.update();
        }
    }

    @Override
    public void playChannelSFX() {}
}
