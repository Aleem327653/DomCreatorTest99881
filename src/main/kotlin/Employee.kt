package org.example

data class Employee(
    val id: Int,
    val name: String,
    val age: Int,
    val department: String,
    val address: Address
)

data class Address(
    val street: String,
    val city: String,
    val zip: String
)
