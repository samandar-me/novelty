package com.sdk.novelty.di

import android.content.Context
import com.sdk.novelty.database.NoveltyDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        createDB(get())
    }
}

fun createDB(context: Context): NoveltyDatabase {
    val driver = AndroidSqliteDriver(
        NoveltyDatabase.Schema,
        context.applicationContext,
        "android_novelty.db"
    )
    return NoveltyDatabase(driver)
}