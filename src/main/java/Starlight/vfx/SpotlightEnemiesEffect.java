package Starlight.vfx;

import Starlight.util.Wiz;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SpotlightEnemiesEffect extends AbstractGameEffect {

    public SpotlightEnemiesEffect() {
        this.duration = 3.0F;
        this.color = new Color(1.0F, 1.0F, 0.8F, 0.5F);
    }

    public void update() {
        if (this.duration == 3.0F) {
            CardCrawlGame.sound.playA("GHOST_ORB_IGNITE_1", -0.6F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 1.5F) {
            this.color.a = Interpolation.pow5In.apply(0.5F, 0.0F, (this.duration - 1.5F) / 1.5F);
        } else {
            this.color.a = Interpolation.exp10In.apply(0.0F, 0.5F, this.duration / 1.5F);
        }

        if (this.duration < 0.0F) {
            this.color.a = 0.0F;
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        Wiz.forAllMonstersLiving(m -> {
            sb.draw(ImageMaster.SPOTLIGHT_VFX, 0.0F, 0.0F, m.drawX + m.hb_w * 2.0F, (float) Settings.HEIGHT);
        });
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {}
}
