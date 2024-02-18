package br.com.luan.creditrequestsystem.exception

data class BusinessException(override val message: String?) : RuntimeException(message) {
}
