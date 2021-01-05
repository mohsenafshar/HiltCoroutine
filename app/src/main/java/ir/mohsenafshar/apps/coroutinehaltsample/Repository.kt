package ir.mohsenafshar.apps.coroutinehaltsample

import javax.inject.Inject

interface Repository {
    suspend fun getList(): List<User>
}


class RemoteRepository @Inject constructor(private var network: Network) : Repository{

    override suspend fun getList(): List<User> {
        return network.getApi().getList()
    }

}