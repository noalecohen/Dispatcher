package com.noalecohen.dispatcher.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {

    open class Response {
        data class Error(val error: Exception?) : Response()
        data class Success(val authResult: AuthResult) : Response()
    }

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    suspend fun register(email: String, password: String): Response {

        return try {
            Response.Success(firebaseAuth.createUserWithEmailAndPassword(email, password).await())
        } catch (e: Exception) {
            Response.Error(e)
        }

    }

    suspend fun login(email: String, password: String): Response {
        return try {
            Response.Success(firebaseAuth.signInWithEmailAndPassword(email, password).await())
        } catch (e: Exception) {
            Response.Error(e)
        }
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}