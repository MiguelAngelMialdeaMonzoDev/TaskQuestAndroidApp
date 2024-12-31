package com.example.domain.exception

sealed class DomainException(message: String) : Exception(message) {
    class NetworkException : DomainException("Network error occurred")
    class DatabaseException : DomainException("Database error occurred")
    data class UnexpectedException(val originalException: Throwable) : DomainException("Unexpected error occurred: ${originalException.message}")
}