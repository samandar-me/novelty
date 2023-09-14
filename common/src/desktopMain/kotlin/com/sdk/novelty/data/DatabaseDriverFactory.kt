package com.sdk.novelty.data

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.sdk.novelty.database.NoveltyDatabase
import com.squareup.sqldelight.db.SqlDriver
import java.sql.Driver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        JdbcSqliteDriver
    }
}