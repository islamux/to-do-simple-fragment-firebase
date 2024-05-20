package com.islamux.to_do_firebase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.islamux.to_do_firebase.databinding.FragmentSignUpBinding

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    // onCreateView : Called when the fragment's view needs to be created from previously saved state.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root // Return the root view of the fragment
    }

    // onViewCreated: Called after the fragment's view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Call the superclass's implementation of onViewCreated
        super.onViewCreated(view, savedInstanceState)

        //Initialize the view elements and set click listeners for them in this method
        init(view)

        // Register click listeners for the buttons (events)
        registerEvents()
    }

    private fun registerEvents() {

        // 2- Auth state listener
        binding.signInText.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        // 1- Set click listener for the sign up button to handle sign up logic
        binding.signUpButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()
            val rePassword = binding.reEnterPasswordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()) {
                if (password == rePassword) {
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        OnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    requireContext(),
                                    "Sign Up (Registered Successfully) Successful",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.navigate(R.id.action_signUpFragment_to_homeFragment)
                            } else {
                                Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }

    }

    private fun init(view: View) {
        // Initialize the NavController
        navController = Navigation.findNavController(view)

        // Initialize the FirebaseAuth instance
        auth = FirebaseAuth.getInstance()
    }
}