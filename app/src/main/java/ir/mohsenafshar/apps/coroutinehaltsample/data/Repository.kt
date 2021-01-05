package ir.mohsenafshar.apps.coroutinehaltsample.data

import ir.mohsenafshar.apps.coroutinehaltsample.ui.User
import javax.inject.Inject

interface Repository {
    suspend fun getList(): List<User>
}


class RemoteRepository @Inject constructor(private var network: Network) : Repository {

    override suspend fun getList(): List<User> {
        return network.getApi().getList()
    }

}