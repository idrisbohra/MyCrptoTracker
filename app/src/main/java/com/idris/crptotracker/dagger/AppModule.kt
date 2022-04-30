package com.idris.crptotracker.dagger

import android.content.Context
import com.idris.crptotracker.utils.IdrisCrptoTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Idris Khozema on 30/04/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context as IdrisCrptoTracker

}