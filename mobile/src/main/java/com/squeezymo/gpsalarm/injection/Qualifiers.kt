package com.squeezymo.gpsalarm.injection

import javax.inject.Qualifier

@Qualifier @Retention(AnnotationRetention.RUNTIME)
annotation class ForApplication

@Qualifier @Retention(AnnotationRetention.RUNTIME)
annotation class ForActivity