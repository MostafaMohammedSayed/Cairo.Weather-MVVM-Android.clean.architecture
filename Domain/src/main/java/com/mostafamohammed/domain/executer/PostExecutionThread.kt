package com.mostafamohammed.domain.executer

import io.reactivex.rxjava3.core.Scheduler

interface PostExecutionThread {
    val scheduler: Scheduler
}