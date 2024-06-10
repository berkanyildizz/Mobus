package com.berkan.mobus_10

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.Navigation
import com.berkan.mobus_10.databinding.FragmentBasefragBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class basefrag : Fragment() {
    private var _binding: FragmentBasefragBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasefragBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signoutbut.setOnClickListener{ cikisyap(it)}
        binding.yolcueklepage.setOnClickListener{ yolcueklepage(it)}
        binding.yolculist01.setOnClickListener{ yolculistesiniac(it)}

    }

    fun cikisyap(view: View){
        auth.signOut()
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()}

    fun yolcueklepage(view: View){
        /*
        val action = FeedFragmentDirections.actionFeedFragmentToSignupFragment()
        Navigation.findNavController(requireView()).navigate(action)
*/
        val action = basefragDirections.actionBasefragToAddyolcu()
        Navigation.findNavController(requireView()).navigate(action)
    }

    /*
    fun yolculistesiniac(view: View){
        val action = basefragDirections.actionBasefragToRecyolculist()
        Navigation.findNavController(requireView()).navigate(action)
    }*/
    fun yolculistesiniac(view: View) {

            val action = basefragDirections.actionBasefragToYolculist()
            Navigation.findNavController(view).navigate(action)
        }







    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}