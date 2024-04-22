package com.kabe.genshincharacters.data.base

data class Resource<out T>(
    val status: Status,
    val data: T?,
    var message: String?,
    val exception: BaseRepository.ServiceException?
) {
    companion object {
        fun <T> success(data: T, message: String? = null): Resource<T> =
            Resource(status = Status.SUCCESS, data = data, message = message, exception = null)

        fun <T> error(
            data: T?,
            message: String,
            exception: BaseRepository.ServiceException
        ): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message, exception = exception)
    }
}