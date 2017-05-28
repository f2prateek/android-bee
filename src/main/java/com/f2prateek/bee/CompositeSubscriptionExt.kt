package com.f2prateek.bee

import rx.Subscription
import rx.subscriptions.CompositeSubscription

operator fun CompositeSubscription.plusAssign(subscription: Subscription) = add(subscription)
