package com.example.recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.recycleview.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recipeNameLabel.text = intent.getStringExtra("NAME")
        binding.recipeDescriptionLabel.text = intent.getStringExtra("DESCRIPTION")
    }
}