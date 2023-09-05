package com.otarbakh.andersen_task__1


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.otarbakh.andersen_task__1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {

            val editText = binding.edittext.text.toString()

            if (isValidUrl(editText)){

                Glide.with(this)
                    .load(editText)
                    .into(binding.image)
            }else
            {
                Toast.makeText(this,"This is Not LINK ! ! !", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun isValidUrl(url: String): Boolean {

        return url.startsWith("http://") || url.startsWith("https://")
    }
}





