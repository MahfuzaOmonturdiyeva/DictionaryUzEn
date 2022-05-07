package uz.gita.dictionaryuzen.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.dictionaryuzen.data.source.local.AppDatabase
import uz.gita.dictionaryuzen.data.source.local.dao.TranslationDao
import uz.gita.dictionaryuzen.data.source.local.dao.WordDao

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.init(context)

    @Provides
    fun provideWordDao(appDatabase: AppDatabase): WordDao = appDatabase.getWordDao()

    @Provides
    fun provideTranslationDao(appDatabase: AppDatabase): TranslationDao =
        appDatabase.getTranslationDao()
}