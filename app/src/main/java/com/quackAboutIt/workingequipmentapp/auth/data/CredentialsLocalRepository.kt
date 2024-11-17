package com.quackAboutIt.workingequipmentapp.auth.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.quackAboutIt.workingequipmentapp.auth.domain.CredentialsRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CredentialsLocalRepository(
    private val dataStore: DataStore<Preferences>
): CredentialsRepository {

    private var token: String? = null

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[TOKEN] = token
        }
        this.token = token
    }

    override suspend fun getToken(): String? {
        if (token == null) {
            token = dataStore.data.map {
                it[TOKEN]
            }.first()
        }
        return token
    }

    private companion object {
        val TOKEN = stringPreferencesKey("token")
    }
}