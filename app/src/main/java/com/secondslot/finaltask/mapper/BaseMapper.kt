package com.secondslot.finaltask.mapper

interface BaseMapper<in A, out B> {

    fun map(type: A?): B
}
