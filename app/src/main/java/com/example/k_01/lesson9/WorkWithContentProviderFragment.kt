package com.example.k_01.lesson9

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.k_01.databinding.FragmentWorkWithContentProviderBinding

class WorkWithContentProviderFragment : Fragment() {

    private var _binding: FragmentWorkWithContentProviderBinding? = null
    private val binding: FragmentWorkWithContentProviderBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkWithContentProviderBinding.inflate(inflater, container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    private fun checkPermission(){
        //проверка разрешение на получение контактов
        if(ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_CONTACTS
            )==PackageManager.PERMISSION_GRANTED){
            getContacts()
        }else if(shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)){
            //просим пользователя разрешение
            explain()
        }else{
            mRequestPermission()
        }

    }

    fun explain(){
        AlertDialog.Builder(requireContext())
            .setTitle("Доступ к контактам")
            .setMessage("Объяснение")
            .setPositiveButton("Предоставить доступ"){ _, _ ->
                mRequestPermission()
            }
            .setNegativeButton("Не надо"){ dialog, _ -> dialog.dismiss()}
            .create()
            .show()
    }

    val REQUEST_CODE= 999
    private fun mRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS),REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode==REQUEST_CODE){

            for (i in permissions.indices){
                if(permissions[i]== Manifest.permission.READ_CONTACTS&&grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    getContacts()
                }else{
                    explain()
                }
            }

        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }


    }

    private fun getContacts() {
        TODO("Not yet implemented")
    }

    class MyTheads:Thread(){
        var mHandler:Handler?=null
        override fun run() {
            Looper.prepare()
            mHandler = Handler(Looper.myLooper()!!)
            Looper.loop()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WorkWithContentProviderFragment()
    }


    }