package com.berkan.mobus_10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.berkan.mobus_10.databinding.FragmentYolculistBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.sql.Timestamp

class yolculist : Fragment() {
    private var _binding: FragmentYolculistBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    var adapter : yolculistrcadapter? = null
    val yolcudatalist : ArrayList<yolcudataclass> = ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        db = Firebase.firestore




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentYolculistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        adapter = yolculistrcadapter(yolcudatalist)
        binding.recyclerView.adapter = adapter
         */
        getDataFromFirestore()

        adapter = yolculistrcadapter(yolcudatalist)
        binding.recycle01.layoutManager = LinearLayoutManager(requireContext())
        binding.recycle01.adapter = adapter




    }

    fun getDataFromFirestore() {
        db.collection("yolculist").orderBy("koltukno", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    Toast.makeText(requireContext(), exception.localizedMessage, Toast.LENGTH_LONG).show()
                } else {
                    if (snapshot != null && !snapshot.isEmpty) {
                        yolcudatalist.clear()

                        val documents = snapshot.documents
                        for (document in documents) {
                            val adsoyad = document.getString("adsoyad") ?: ""
                            val koltukno = document.getString("koltukno") ?: ""
                            val nereden = document.getString("nereden") ?: ""
                            val nereye = document.getString("nereye") ?: ""
                            val seferno = document.getString("seferno") ?: ""
                            val userEmail = document.getString("userEmail") ?: ""
                            val date = document.get("date") as? com.google.firebase.Timestamp
                            val dateString = date?.toDate().toString() ?: ""

                            val yolcu = yolcudataclass(adsoyad, dateString, koltukno, nereden, nereye, seferno, userEmail)
                            yolcudatalist.add(yolcu)
                        }
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
    }
}


