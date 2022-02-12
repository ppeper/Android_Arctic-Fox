package com.kyonggi.viewmodelscopedemo.model

import kotlinx.coroutines.delay

class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(5000)
        val users: List<User> = listOf(
            User(1, "Pepper1"),
            User(2, "Pepper2"),
            User(3, "Pepper3"),
            User(4, "Pepper4")
        )
        return users
    }
}