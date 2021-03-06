package dji.thirdparty.io.reactivex.internal.operators.flowable;

import dji.thirdparty.io.reactivex.Flowable;
import dji.thirdparty.io.reactivex.internal.fuseable.ScalarCallable;
import dji.thirdparty.io.reactivex.internal.subscriptions.ScalarSubscription;
import org.reactivestreams.Subscriber;

public final class FlowableJust<T> extends Flowable<T> implements ScalarCallable<T> {
    private final T value;

    public FlowableJust(T value2) {
        this.value = value2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Subscriber<? super T> s) {
        s.onSubscribe(new ScalarSubscription(s, this.value));
    }

    public T call() {
        return this.value;
    }
}
