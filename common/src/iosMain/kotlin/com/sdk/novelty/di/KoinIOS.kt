package com.sdk.novelty.di

import com.sdk.novelty.database.NoveltyDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        createDB()
    }
}

fun createDB(): NoveltyDatabase {
    val driver = NativeSqliteDriver(NoveltyDatabase.Schema, "ios_novelty.db")
    return NoveltyDatabase.invoke(driver)
}