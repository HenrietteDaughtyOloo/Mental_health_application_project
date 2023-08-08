package com.henriette.project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import com.henriette.project.databinding.ActivityLogInBinding
import com.henriette.project.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener,
    View.OnKeyListener {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.tilFullName.onFocusChangeListener = this
        binding.etLogInEmail.onFocusChangeListener = this
        binding.etLogInPassword.onFocusChangeListener=this
        binding.etConfirmPassword.onFocusChangeListener=this



    }

    private fun validateFullName(): Boolean {
        var errorMessage: String? = null
        val value: String = binding.etFullName.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Full Name is required"
        }
        if (errorMessage != null){
            binding.tilFullName.apply {
                isErrorEnabled = true
                error= errorMessage
            }
        }

        return errorMessage == null
    }

    private fun validateEmail(): Boolean {
        var errorMessage: String? = null
        val value = binding.etLogInEmail.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Email Is required"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            errorMessage = "Email address is invalid"
        }

        if (errorMessage != null){
            binding.tilLogInEmail.apply {
                isErrorEnabled = true
                error= errorMessage
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

        if (errorMessage != null){
            binding.tilLogInPassword.apply {
                isErrorEnabled = true
                error= errorMessage
            }
        }
        return errorMessage == null
    }

    private fun validateConfirmPassword(): Boolean {
        var errorMessage: String? = null
        val value = binding.etConfirmPassword.text.toString()
        if (value.isEmpty()) {
            errorMessage = "Password Confirmation Is required"
        } else if (value.length < 6) {
            errorMessage = "Password Confirmation must be 6 characters long"
        }

        if (errorMessage != null){
            binding.tilConfirmPassword.apply {
                isErrorEnabled = true
                error= errorMessage
            }
        }
        return errorMessage == null

    }

    private fun validatePasswordAndConfirmPassword(): Boolean {

        var errorMessage: String? = null
        val password = binding.etLogInPassword.text.toString()
        val confirmPassword = binding.etConfirmPassword.text.toString()
        if (password != confirmPassword) {
            errorMessage = "Confirm Password does not match with Password"
        }

        if (errorMessage != null){
            binding.tilLogInPassword.apply {
                isErrorEnabled = true
                error= errorMessage
            }
        }

        return errorMessage == null

    }


    override fun onClick(view: View?) {

    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view != null){
            when(view.id){
                R.id.etFullName ->{
                    if (hasFocus){
                        if (binding.tilFullName.isErrorEnabled){
                            binding.tilFullName.isErrorEnabled = false
                        }

                    }else{
                        validateFullName()

                    }

                }
                R.id.etLogInEmail ->{
                    if (hasFocus){
                        if (binding.tilLogInEmail.isErrorEnabled){
                            binding.tilLogInEmail.isErrorEnabled = false
                        }


                    }else{
                        validateEmail()

                    }
                    
                }
                R.id.etLogInPassword->{
                    if (hasFocus){
                        if (binding.tilLogInPassword.isErrorEnabled){
                            binding.tilLogInPassword.isErrorEnabled = false
                        }


                    }else{
                        if (validatePassword()&& binding.etLogInPassword.text!!.isNotEmpty()&&validateConfirmPassword()&&
                                validatePasswordAndConfirmPassword()){
                            if (binding.tilLogInPassword.isErrorEnabled){
                                binding.tilLogInPassword.isErrorEnabled=false
                            }
                            binding.tilLogInPassword.setStartIconDrawable(R.drawable.check_24)

                        }
                        validatePassword()
                    }

                }
                R.id.etConfirmPassword->{
                    if (hasFocus){
                        if (binding.tilConfirmPassword.isErrorEnabled){
                            binding.tilConfirmPassword.isErrorEnabled = false
                        }

                    }else{
                        validateConfirmPassword()

                    }

                }

            }
        }
        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, LogInActivity::class.java))
        }

    }


    override fun onKey(view: View?, event: Int, keyEvent: KeyEvent?): Boolean {
        return false
    }
}