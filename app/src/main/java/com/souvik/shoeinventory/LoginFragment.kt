package com.souvik.shoeinventory

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.souvik.shoeinventory.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        if(requireActivity().getPreferences(Context.MODE_PRIVATE).getBoolean(resources.getString(R.string.loginStatus),false))
            findNavController().navigate(R.id.action_loginFragment_to_onBoardingFragment)
        binding.btnCreateAccount.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext())
                .setTitle("Create Account")
                .setView(R.layout.creat_acc_layout)
                .create()
            dialog.show()
            dialog.findViewById<Button>(R.id.btnSubmit)!!.setOnClickListener {
                val email = dialog.findViewById<EditText>(R.id.etNewEmail)
                val password = dialog.findViewById<EditText>(R.id.etNewPassword)
                Log.d("TAG", "initView: ${email?.text} ${password?.text}")
                if (validate(email!!, password!!)) {
                    saveLoginCredential(email.text.toString(), password.text.toString())
                    dialog.dismiss()
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            if (validate(binding.etEmail,binding.etPassword) && validateLoginCredentials(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            ) {
                val edit = requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
                edit.putBoolean(resources.getString(R.string.loginStatus), true)
                edit.apply()
                findNavController().navigate(R.id.action_loginFragment_to_onBoardingFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Please make sure to enter correct credentials.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun saveLoginCredential(email: String, password: String) {
        val edit = requireActivity().getPreferences(Context.MODE_PRIVATE).edit()
        edit.putString(resources.getString(R.string.email), email)
        edit.putString(resources.getString(R.string.password), password)
        edit.putBoolean(resources.getString(R.string.loginStatus), true)
        edit.apply()
        Toast.makeText(requireContext(), "Account created successfully!", Toast.LENGTH_SHORT).show()
    }

    private fun validateLoginCredentials(email: String, password: String): Boolean {
        val pref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        return pref.getString(resources.getString(R.string.email), "").equals(email) &&
                pref.getString(resources.getString(R.string.password), "").equals(password)
    }

    private fun validate(email: EditText, password: EditText): Boolean {
        if (TextUtils.isEmpty(email.text)) {
            email.error = "Field cannot be empty."
            return false
        }
        if (TextUtils.isEmpty(password.text)) {
            password.error = "Field cannot be empty."
            return false
        }
        return true
    }
}