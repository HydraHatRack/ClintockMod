package hydrahatrack.clintock.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;

public class NucleotideOrb extends AbstractOrb {
    private float vfxTimer = 1.0F;
    String label = "";

    NucleotideOrb() {
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void updateDescription() {
        applyFocus();
        this.description = "";
    }

    @Override
    public void onEvoke() {}

    @Override
    public AbstractOrb makeCopy() {
        return null;
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 45.0F;

        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F) {
            AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
            float vfxIntervalMin = 0.1F;
            float vfxIntervalMax = 0.4F;
            this.vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
        spriteBatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y,
                48.0F, 48.0F, 96.0F, 96.0F,
                this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale,
                this.scale, this.angle, 0, 0, 96, 96, false, false);

        spriteBatch.setColor(new Color(1.0F, 1.0F, 1.0F, this.c.a / 2.0F));
        spriteBatch.setBlendFunction(770, 1);
        spriteBatch.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y,
                48.0F, 48.0F, 96.0F, 96.0F, this.scale,
                this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.04F * Settings.scale,
                -this.angle, 0, 0, 96, 96, false, false);

        spriteBatch.setBlendFunction(770, 771);
        FontHelper.renderFontCentered(spriteBatch, FontHelper.cardEnergyFont_L, this.label,
                this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET,
                this.c, this.fontScale);
        this.hb.render(spriteBatch);
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_PLASMA_CHANNEL", 0.1F);
    }
}
