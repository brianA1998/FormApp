package com.example.formapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.formapp.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editTextDateBirth.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val picker = builder.build()
            picker.show(supportFragmentManager, picker.toString())
        }


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
                val height = binding.editTextHeight.text.toString().trim()
                val dateBirth = binding.editTextDateBirth.text.toString().trim()
                val country = binding.actvCountries.text.toString().trim()
                val placeBirth = binding.etPlaceBirth.text.toString().trim()
                val notes = binding.etNotes.text.toString().trim()


                //Toast.makeText(this, "$name $surname", Toast.LENGTH_SHORT).show()


                //Instanciamos alertdialog
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.dialog_title))
                builder.setMessage(
                    joinData(
                        name,
                        surname,
                        height,
                        dateBirth,
                        country,
                        placeBirth,
                        notes
                    )
                )
                builder.setPositiveButton(
                    getString(R.string.dialog_ok),
                    DialogInterface.OnClickListener { dialog, which ->
                        with(binding) {
                            editTextName.text?.clear()
                            editTextLastName.text?.clear()
                            editTextHeight.text?.clear()
                            editTextDateBirth.text?.clear()
                            actvCountries.text?.clear()
                            etPlaceBirth.text?.clear()
                            etNotes.text?.clear()
                        }

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
     * Permite formatear los datos recolectados del formulario
     */
    private fun joinData(vararg fields: String): String {
        var result: String = ""

        fields.forEach { field ->
            if (field.isNotBlank()) {
                result += "$field\n"
            }

        }

        return result
    }


    /**
     * Esta función nos permite saber si el campo name y lasname estan vacio
     * En caso de estar vacio. Establece el foco en el campo y muestra una advertencia
     */
    private fun validField(): Boolean {
        var isValid = true

        if (binding.editTextHeight.text.toString().length == 0) {
            binding.tilHeight.run {
                error = getString(R.string.help_require)
                requestFocus()
            }
            isValid = false
        } else if (binding.editTextHeight.text.toString().toInt() < 50) {
            binding.tilHeight.run {
                error = getString(R.string.help_min_height_valid)
                requestFocus()
            }
            isValid = false
        } else {
            binding.tilHeight.error = null
        }

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