package com.staygrateful.feature_detail.data.repository

import com.staygrateful.feature_detail.data.source.local.IDetailLocalDataSource
import com.staygrateful.feature_detail.data.source.remote.IDetailRemoteDataSource

interface IDetailRepository: IDetailLocalDataSource, IDetailRemoteDataSource
