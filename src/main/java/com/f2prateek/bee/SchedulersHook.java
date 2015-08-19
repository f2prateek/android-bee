package com.f2prateek.bee;

import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * Too lazy to setup proper DI for this project.
 * Waiting on https://github.com/ReactiveX/RxJava/issues/2297.
 */
public final class SchedulersHook {
  private SchedulersHook() {
    throw new AssertionError("no instances.");
  }

  private static final AtomicReference<Scheduler> COMPUTATION = new AtomicReference<>();

  public static Scheduler computation() {
    if (COMPUTATION.get() == null) {
      COMPUTATION.compareAndSet(null, Schedulers.computation());
      // Call get() again in case of thread-race so the winner will always get returned.
    }
    return COMPUTATION.get();
  }

  public static void setComputationScheduler(Scheduler scheduler) {
    if (!COMPUTATION.compareAndSet(null, scheduler)) {
      throw new IllegalStateException("Scheduler was already registered: " + COMPUTATION.get());
    }
  }
}
