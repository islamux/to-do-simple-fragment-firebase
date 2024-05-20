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
import com.islamux.to_do_firebase.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    // onCreateView : called when the fragment is created or re-created from a previously saved state.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root // return the root view of the fragment layout
    }

    // onViewCreated: Called after the fragment's view has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState) // call the superclass method first

        //Initialize the view elements and set click listeners for them in this method
        init(view)

        // Register a click listener for the sign in button in the layout (events)
        registerEvents()
    }

    private fun registerEvents() {
        // 2- Set a click listener for the sign up text view to navigate to the sign up fragment when clicked on it (events)
        binding.signUpText.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        // 1- Set a click listener for the sign in button to handle the sign in process
        binding.signInButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    OnCompleteListener{
                        if(it.isSuccessful){

                            Toast.makeText(requireContext(), "Login Successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_signInFragment_to_homeFragment)

                        }else{
                            Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }


    }

    private fun init(view: View) {
        // Initialize the NavController with the provided view (fragment) as the context
        navController = Navigation.findNavController(view)

        // Initialize the FirebaseAuth instance
        auth = FirebaseAuth.getInstance()
    }
}