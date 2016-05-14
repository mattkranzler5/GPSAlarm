package com.squeezymo.gpsalarm.injection

import javax.inject.Scope

@Scope @Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope @Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope