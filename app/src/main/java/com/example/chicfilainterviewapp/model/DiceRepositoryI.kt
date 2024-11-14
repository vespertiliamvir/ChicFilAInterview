package com.example.chicfilainterviewapp.model

interface DiceRepositoryI {
    suspend fun rollDice():Result<Int>
}