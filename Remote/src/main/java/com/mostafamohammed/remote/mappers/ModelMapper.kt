package com.mostafamohammed.remote.mappers

import com.mostafamohammed.remote.RemoteModel

interface ModelMapper<in M, out E> {

    fun mapFromModel(model: M): E
}