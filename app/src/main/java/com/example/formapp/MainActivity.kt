package com.example.formapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
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
        if (item?.itemId == R.id.action_send) {


            if (validField()) {

                val name: String =
                    findViewById<TextInputEditText>(R.id.editTextName).text.toString()
                val surname = binding.editTextLastName.text.toString()
                //Toast.makeText(this, "$name $surname", Toast.LENGTH_SHORT).show()


                //Instanciamos alertdialog
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.dialog_title))
                builder.setMessage("$name  $surname")
                builder.setPositiveButton(
                    getString(R.string.dialog_ok),
                    DialogInterface.OnClickListener { dialog, which ->
                        Toast.makeText(this, "Positive button", Toast.LENGTH_SHORT).show()
                    })
                builder.setNegativeButton(
                    getString(R.string.dialog_cancel), null
                )

                val dialog: AlertDialog = builder.create()
                dialog.show()
            }

        }

        return super.onOptionsItemSelected(item ?: return false)
    }


    /**
     * Esta función nos permite saber si el campo name y lasname estan vacio
     * En caso de estar vacio. Establece el foco en el campo y muestra una advertencia
     */
    private fun validField(): Boolean {
        var isValid = true

        if (binding.editTextLastName.text.toString().length == 0) {
            binding.tilLastName.run {
                error = getString(R.string.help_require)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilLastName.error = null
        }

        if (binding.editTextName.text.toString().length == 0) {
            binding.tilName.run {
                error = getString(R.string.help_require)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilName.error = null
        }

        return isValid
    }


}