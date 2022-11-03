package com.aplikasi.crudkotlin.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.aplikasi.crudkotlin.databinding.ActivityMainBinding
import com.aplikasi.crudkotlin.model.User
import com.aplikasi.crudkotlin.ui.adapter.Adapter
import com.google.firebase.firestore.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val TAG: String = javaClass.simpleName

    private lateinit var adapter: Adapter
    private lateinit var db: FirebaseFirestore
    private lateinit var users : ArrayList<User>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = FirebaseFirestore.getInstance()
        writeData(db)
        readData(db)

        adapter = Adapter()
        binding.rvFiredb.adapter = adapter
        binding.rvFiredb.layoutManager = LinearLayoutManager(this)
        binding.rvFiredb.setHasFixedSize(true)


        users = arrayListOf()

        eventChangeListener(db)


        binding.fabFiredb.setOnClickListener{
            startActivity(Intent(this, ActivityCrud::class.java))
        }
    }

    private fun eventChangeListener(db: FirebaseFirestore) {
        db.collection("users")
            .addSnapshotListener(object : EventListener<QuerySnapshot>{
                @SuppressLint("NotifyDataSetChanged")
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("firestore error: ", error.message.toString())
                        return
                    }
                    for (dc : DocumentChange in value?.documentChanges!!) {
                        if (dc.type == DocumentChange.Type.ADDED) {
                            users.add(dc.document.toObject(User::class.java))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            })
    }

    private fun writeData(db: FirebaseFirestore) {
        val user = hashMapOf(
            "strName" to "Rizky",
            "strAddress" to "jawa",
            "intAge" to 12
        )
        Log.d(TAG, "writeData: $user")
//        db.collection("users")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Toast.makeText(this, "DocumentSnapshot added with ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
//            }
//            .addOnFailureListener { e ->
//                Toast.makeText(this, "Error adding document", Toast.LENGTH_SHORT).show()
//            }
    }


    private fun readData(db: FirebaseFirestore) {
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "cek data = ${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

}