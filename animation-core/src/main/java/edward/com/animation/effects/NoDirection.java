package edward.com.animation.effects;

/**
 * Created by Administrator on 15-2-5.
 */
public abstract class NoDirection implements Effect4View {

    protected long duration = AnimatorBuilder.DEFAULT_DURATION;
    protected int repeatCount;

    protected NoDirection() {
    }

    protected NoDirection(long duration) {
        this.duration = duration;
    }

    @Override
    public Effect4View setDuration(long duration) {
        this.duration = duration;
        return this;
    }
}
