package com.francis.popularmovies.utility

import android.app.Application

class NetworkStatus(val app: Application) : INetworkStatus {
    override fun isConnected(): Boolean {
        return isConnectionAvailable(app)
    }
}