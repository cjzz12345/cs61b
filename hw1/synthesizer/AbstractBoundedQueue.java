package synthesizer;

import org.junit.Test;

public abstract class AbstractBoundedQueue < T >implements BoundedQueue < T >{
    protected int fillCount;
    protected int capacity;

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
