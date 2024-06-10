package com.berkan.mobus_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.berkan.mobus_10.databinding.FragmentAddyolcuBinding
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class addyolcu : Fragment() {

    private var _binding: FragmentAddyolcuBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddyolcuBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.yolcufireekle.setOnClickListener { firebaseyolcuadd(it) }
        binding.yolcuekletomenu.setOnClickListener { gomainmenu(it) }



    }

    fun firebaseyolcuadd(view: View){
        if(auth.currentUser!=null){
            if(binding.adgirdi.text.isNotEmpty() && binding.koltukno.text.isNotEmpty() && binding.sefer.text.isNotEmpty() &&
                binding.nereden.text.isNotEmpty() && binding.nereye.text.isNotEmpty()) {
                val yolcuMap = hashMapOf<String, Any>()
                yolcuMap.put("adsoyad", binding.adgirdi.text.toString())
                yolcuMap.put("koltukno", binding.koltukno.text.toString())
                yolcuMap.put("seferno", binding.sefer.text.toString())
                yolcuMap.put("nereden", binding.nereden.text.toString())
                yolcuMap.put("nereye", binding.nereye.text.toString())
                yolcuMap.put("userEmail",auth.currentUser!!.email.toString())
                yolcuMap.put("date", Timestamp.now())
                db.collection( "yolculist").add(yolcuMap).addOnCompleteListener{task ->
                    if (task.isComplete && task.isSuccessful) {
                        Toast.makeText(requireContext(),"kayıt tamamlandı", Toast.LENGTH_LONG).show()
                    }
                }.addOnFailureListener{exception ->
                    Toast.makeText(requireContext(),exception.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }
            else{
                Toast.makeText(requireContext(),"eksik bilgileri doldurun", Toast.LENGTH_LONG).show()
            }
        }
    }


    /*
    val action = AddFragmentDirections.actionAddFragmentToFeedFragment()
                            Navigation.findNavController(view).navigate(action)
     */
    fun gomainmenu(view: View){
        val action = addyolcuDirections.actionAddyolcuToBasefrag()
        Navigation.findNavController(view).navigate(action)



    }
}