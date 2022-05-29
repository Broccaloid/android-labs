package com.example.recycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleview.databinding.ActivityMainBinding
import com.github.javafaker.Faker

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val list: MutableList<Recipe> = mutableListOf()
    private val faker: Faker = Faker()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = RecipeAdapter(layoutInflater) {
            val intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("NAME", it.name)
            intent.putExtra("DESCRIPTION", it.description)
            startActivity(intent)
        }
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(this)
        adapter.submitList(null)

        binding.addRecipe.setOnClickListener {
            val recipe = Recipe(faker.food().dish(), faker.lorem().sentence())
            list.add(recipe)
            adapter.submitList(list.toList())
        }

        binding.removeRecipe.setOnClickListener {
            if (list.isNotEmpty()){
                list.removeLast()
            }
            adapter.submitList(list.toList())
        }

        binding.clear.setOnClickListener {
            list.clear()
            adapter.submitList(list.toList())
        }


    }



    data class Recipe(val name: String, val description: String) {
    }
}