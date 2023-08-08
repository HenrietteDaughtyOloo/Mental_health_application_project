package com.henriette.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.henriette.project.databinding.ActivityRegisterBinding

class LogInActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.etLogInEmail.onFocusChangeListener = this
        binding.etLogInPassword.onFocusChangeListener = this



    }


    private fun validateEmail(): Boolean {
        var errorMessage: String? = null
        val value = binding.etLogInEmail.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Email Is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = "Email address is invalid"
        }

        if (errorMessage != null) {
            binding.tilLogInEmail.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }


    private fun validatePassword(): Boolean {
        var errorMessage: String? = null
        val value = binding.etLogInPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password Is required"
        } else if (value.length < 6) {
            errorMessage = "Password must be 6 characters long"
        }

        if (errorMessage != null) {
            binding.tilLogInPassword.apply {
                isErrorEnabled = true
                error = errorMessage
            }
        }
        return errorMessage == null
    }




    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null) {
            when (view.id) {
                R.id.etLogInEmail -> {
                    if (hasFocus) {
                        if (binding.tilLogInEmail.isErrorEnabled) {
                            binding.tilLogInEmail.isErrorEnabled = false
                        }


                    } else {
                        validateEmail()

                    }

                }

                R.id.etLogInPassword -> {
                    if (hasFocus) {
                        if (binding.tilLogInPassword.isErrorEnabled) {
                            binding.tilLogInPassword.isErrorEnabled = false
                        }


                    } else {
                        validatePassword()
                    }

                }

            }
        }

    }

    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}