package com.example.intent

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intent.MoveForResultActivity.Companion.RESULT_CODE

class MainActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("SuspiciousIndentation")

    private lateinit var tvResult: TextView

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMoveActivity: Button =
            findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        val btnMoveWithDataActivity: Button =
            findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        val btnDialPhone: Button =
            findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        val btnMoveWithObjectActivity: Button =
            findViewById(R.id.btn_move_activity_object)
        btnMoveWithObjectActivity.setOnClickListener(this)

        val btnMoveWithResult: Button =
            findViewById(R.id.btn_move_activity_result)
        btnMoveWithResult.setOnClickListener(this)

        tvResult = findViewById(R.id.tv_result)


    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MoveActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val moveWithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Tegar")
                moveWithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 17)
                startActivity(moveWithDataIntent)
            }

            R.id.btn_move_activity_object -> {
                val person = Person(name = "Tegar", email = "tegar00@gmail.com", city = "Kediri")
                val moveActivityWithObject =
                    Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveActivityWithObject.putExtra(
                    MoveWithObjectActivity.EXTRA_PERSON, person
                )
                startActivity(moveActivityWithObject)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "089668311342"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_activity_result -> {
                val moveForResultIntent =
                    Intent(this@MainActivity, MoveForResultActivity::class.java)
                getResult.launch(moveForResultIntent)

            }
        }
    }

    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_CODE) {
                val value = it.data?.getIntExtra(
                    MoveForResultActivity.EXTRA_SELECTED_VALUE, 0
                )
                tvResult.setText("Hasil $value")
            }


        }
}