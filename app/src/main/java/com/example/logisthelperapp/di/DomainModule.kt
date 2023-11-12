package com.example.logisthelperapp.di

import com.example.logisthelperapp.data.repository.MessageRepositoryMockImpl
import com.example.logisthelperapp.data.repository.TaskRepositoryMockImpl
import com.example.logisthelperapp.data.repository.UserRepositoryMockImpl
import com.example.logisthelperapp.domain.repository.MessageRepository
import com.example.logisthelperapp.domain.repository.TaskRepository
import com.example.logisthelperapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface DomainModule {

    @Binds
    fun bindUserRepository(repository: UserRepositoryMockImpl): UserRepository

    @Binds
    fun bindTaskRepository(repository: TaskRepositoryMockImpl): TaskRepository

    @Binds
    fun bindMessageRepository(repository: MessageRepositoryMockImpl): MessageRepository
}