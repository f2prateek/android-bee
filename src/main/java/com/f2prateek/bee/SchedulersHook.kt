package com.f2prateek.bee

import rx.Scheduler
import rx.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicReference

/**
 * Too lazy to setup proper DI for this project.
 * Waiting on https://github.com/ReactiveX/RxJava/issues/2297.
 */
class SchedulersHook private constructor() {
    init {
        throw AssertionError("no instances.")
    }

    companion object {
        private val COMPUTATION = AtomicReference<Scheduler>()

        fun computation(): Scheduler {
            if (COMPUTATION.get() == null) {
                COMPUTATION.compareAndSet(null, Schedulers.computation())
                // Call get() again in case of thread-race so the winner will always get returned.
            }
            return COMPUTATION.get()
        }

        fun setComputationScheduler(scheduler: Scheduler) {
            if (!COMPUTATION.compareAndSet(null, scheduler)) {
                throw IllegalStateException("Scheduler was already registered: " + COMPUTATION.get())
            }
        }
    }
}
