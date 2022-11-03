package com.aplikasi.crudkotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.aplikasi.crudkotlin.databinding.ActivityCrudBinding
import com.aplikasi.crudkotlin.ui.utils.Utils.PATH_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore

class ActivityCrud : AppCompatActivity() {
    private lateinit var binding: ActivityCrudBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val name = binding.tiName.text.toString()
            val adrs = binding.tiAddress.text.toString()
            val age = binding.tiAge.text.toString().toInt()

            saveData(name, adrs, age)
        }
        val db = FirebaseFirestore.getInstance()
        getData(db)
    }

    private fun getData(db: FirebaseFirestore) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("TAG", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("TAG", "Error getting documents.", exception)
            }
    }


    private fun saveData(users: String, adrs: String, age: Int) {
        val db = FirebaseFirestore.getInstance()
        val user : MutableMap<String, Any> = HashMap()
        user["name"] = users
        user["address"] = adrs
        user["age"] = age


        db.collection(PATH_COLLECTION).add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Data gagal disimpan", Toast.LENGTH_SHORT).show()
            }
    }
}