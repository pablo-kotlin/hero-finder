package com.project.marvelsuperheroes.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.project.marvelsuperheroes.R
import com.project.marvelsuperheroes.data.repository.FirebaseRepositoryImpl
import com.project.marvelsuperheroes.databinding.ActivityLoginBinding
import com.project.marvelsuperheroes.ui.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private val repository by lazy { FirebaseRepositoryImpl(FirebaseAuth.getInstance()) }
    private val viewModel: LoginViewModel by lazy { LoginViewModel(repository) }

    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var mProgressBar: ProgressBar

    private var isLoginMode = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        if (savedInstanceState != null) {
            isLoginMode = savedInstanceState.getBoolean("loginMode")
        }

        mProgressBar = mBinding.loading

        mBinding.btnContinueAction.setOnClickListener {
            val email = mBinding.emailInputEditText.text.toString()
            val password = mBinding.passwordInputEditText.text.toString()

            if (isLoginMode) {
                signIn(email, password)
            } else {
                val passwordConfirm = mBinding.confirmPasswordInputEditText.text.toString()
                createUser(email, password, passwordConfirm)
            }
        }

        mBinding.btnSignInLogin.setOnClickListener {
            isLoginMode = !isLoginMode
            if (isLoginMode) {
                mBinding.confirmPasswordEditText.visibility = GONE
                mBinding.btnSignInLogin.setText(R.string.action_create_user)
                mBinding.tvMsgCreateAccount.setText(R.string.msg_create_new_user)
            } else {
                mBinding.confirmPasswordEditText.visibility = VISIBLE
                mBinding.btnSignInLogin.setText(R.string.action_login)
                mBinding.tvMsgCreateAccount.setText(R.string.msg_sign_in)
            }
        }
    }

    private fun signIn(email: String, password: String) {
        showProgressBar()
        if (viewModel.isUserNameValid(email) && viewModel.isPasswordValid(password)) {
            viewModel.signIn(email, password).addOnCompleteListener(this) { task ->
                hideProgressBar()
                if (task.isSuccessful) {
                    // Sign in success
                    val user = FirebaseAuth.getInstance().currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    task.exception?.message?.let {
                        Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()
                    }
                    updateUI(null)
                }
            }
        } else {
            hideProgressBar()
            Toast.makeText(baseContext, "Invalid email or password.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createUser(email: String, password: String, passwordConfirm: String) {
        if (viewModel.isUserNameValid(email) && viewModel.isPasswordValid(password) && viewModel.checkPasswordConfirmation(
                password,
                passwordConfirm
            )
        ) {
            showProgressBar()
            viewModel.createUser(email, password).addOnCompleteListener(this) { task ->
                hideProgressBar()
                if (task.isSuccessful) {
                    // User creation success, update UI with the signed-in user's information
                    val user = FirebaseAuth.getInstance().currentUser
                    updateUI(user)
                } else {
                    // If sign up fails, display a message to the user.
                    task.exception?.message?.let {
                        Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
                    } ?: run {
                        Toast.makeText(baseContext, "User creation failed.", Toast.LENGTH_SHORT)
                            .show()
                    }
                    updateUI(null)
                }
            }
        } else {
            hideProgressBar()
            Toast.makeText(
                baseContext,
                "Invalid email, password, or password confirmation.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("loginMode", isLoginMode)
    }

    private fun showProgressBar() {
        mProgressBar.visibility = VISIBLE
    }

    private fun hideProgressBar() {
        mProgressBar.visibility = GONE
    }

}