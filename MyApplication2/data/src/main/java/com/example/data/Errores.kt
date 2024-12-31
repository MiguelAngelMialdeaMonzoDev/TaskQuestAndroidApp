package com.example.data

sealed class Response {
    data class Success() : Response
}