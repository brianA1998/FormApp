package com.example.formapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.formapp.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        //Controlamos que elemento del menu se selecciona
        if (item.itemId == R.id.action_send) {
            val name: String = findViewById<TextInputEditText>(R.id.editTextName).text.toString()
            val surname = binding.editTextLastName.text.toString()
            //Toast.makeText(this, "$name $surname", Toast.LENGTH_SHORT).show()


            //Instanciamos alertdialog
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.dialog_title))
            builder.setMessage("$name  $surname")

            val dialog: AlertDialog = builder.create()
            dialog.show()

        }

        return super.onOptionsItemSelected(item)
    }


}