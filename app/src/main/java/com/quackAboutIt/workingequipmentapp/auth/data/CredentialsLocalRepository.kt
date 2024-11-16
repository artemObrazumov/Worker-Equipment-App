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

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[TOKEN] = token
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map {
            it[TOKEN]
        }.first()
    }

    private companion object {
        val TOKEN = stringPreferencesKey("token")
    }
}