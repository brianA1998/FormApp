package com.example.formapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        /**
         * Controlamos que elemento del menu se selecciona
         */
        if (item.itemId == R.id.action_send) {

            Toast.makeText(this, "Click en send", Toast.LENGTH_SHORT).show()
        }



        return super.onOptionsItemSelected(item)
    }


}