package edward.com.animation.effects;

public abstract class NoDirection implements Effect4View {

    protected long duration = AnimatorBuilder.DEFAULT_DURATION;
    protected int repeatCount;

    protected NoDirection() {
    }

    protected NoDirection(long duration) {
        this.duration = duration;
    }

    public NoDirection setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public long getDuration() {
        return duration;
    }
}
