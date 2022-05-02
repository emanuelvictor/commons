package io.github.emanuelvictor.infrastructure.async;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class ThreadComponent {

    /**
     * {@code DEFAULT_POOL} is the default value to the pool size
     */
    private final static int DEFAULT_POOL = 10;

    /**
     * {@code block} define if the thread componente will be blocked.
     */
    private boolean block = false;

    /**
     * {@link Collection} of {@link Future} to storage the async tasks
     */
    private final Collection<Future<?>> futures = new ArrayList<>();

    /**
     * {@link ExecutorService} from java.util.concurrent concurrent API's
     * Instancy {@link java.util.concurrent.Executors#newFixedThreadPool(int)} with {@link #DEFAULT_POOL} value
     *
     * @see java.util.concurrent.ExecutorService
     */
    public static ExecutorService executorService = Executors.newFixedThreadPool(DEFAULT_POOL);

    /**
     * @param threadPool Count the thread pool
     */
    private ThreadComponent(final int threadPool) {
        executorService = Executors.newFixedThreadPool(threadPool);
    }

    /**
     * <p>
     * Define the count of the tread pool of the {@code ExecutorService}.
     * If this value is not defined, the default value will be {@link #DEFAULT_POOL}.
     * </p>
     * <p>
     * If the count of threads is 1, the {@link Runnable} will be executed in sequential.
     * If the count of threads is different of 1, the {@link Runnable} will be executed with aleatory order.
     * </p>
     *
     * @param threadPool int
     * @return {@link ThreadComponent}
     */
    public static ThreadComponent pool(final int threadPool) {
        return new ThreadComponent(threadPool);
    }

    /**
     * Enter with {@link Runnable} implemetantions.
     *
     * @param runnables {@link Runnable}
     * @return {@link ThreadComponent}
     */
    public ThreadComponent execute(final Runnable... runnables) {
        for (final Runnable runnable : runnables) {
            futures.add(executorService.submit(runnable));
        }
        return this;
    }

    /**
     * Set the private variable {@link ThreadComponent#block} to true, to block localThread. Then return {@link ThreadComponent}
     *
     * @return {@link ThreadComponent}
     */
    public ThreadComponent block() {
        this.block = true;
        return this;
    }

    /*
     * @param then Consumer<?>
     */
    public void then(final Consumer<?> then) {
        if (block)
            accept(then);
        else {
            final Runnable runnable = () -> accept(then);
            final ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(runnable);
            executorService.shutdown();
        }
    }

    /*
     * @param then Consumer<?>
     */
    private void accept(final Consumer<?> then) {
        int j = 1;
        for (int i = 0; i < j++; i++) {
            if (futures.stream().allMatch(Future::isDone)) {
                then.accept(null);
                break;
            }
            j++;
        }
    }

}