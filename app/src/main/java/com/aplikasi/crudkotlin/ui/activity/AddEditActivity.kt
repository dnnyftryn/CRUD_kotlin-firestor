package com.aplikasi.crudkotlin.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aplikasi.crudkotlin.databinding.ActivityAddEditBinding
import com.aplikasi.crudkotlin.model.User
import com.aplikasi.crudkotlin.ui.utils.Utils.PATH_COLLECTION
import com.google.firebase.firestore.FirebaseFirestore

class AddEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditBinding

    companion object {
        //key untuk intent data
        const val EXTRA_DATA = "extra_data"
        const val REQ_EDIT = "req_edit"
    }
    private var isEdit = false
    private var user: User? = null
    private val mFirestore = FirebaseFirestore.getInstance()
    private val mUsersCollection = mFirestore.collection(PATH_COLLECTION)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}