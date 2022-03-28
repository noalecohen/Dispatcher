package com.noalecohen.dispatcher.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String, onCompleteListener: (Task<AuthResult>) -> Unit) {
        firebaseAuth
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(onCompleteListener)
    }

    fun login(email: String, password: String, onCompleteListener: (Task<AuthResult>) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(onCompleteListener)
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun signOut() {
        firebaseAuth.signOut()
    }
}