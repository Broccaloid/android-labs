package com.example.recycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private val inflater: LayoutInflater,
    private val onClick: (MainActivity.Recipe) -> Unit
) : ListAdapter<MainActivity.Recipe, RecipeAdapter.ViewHolder>(UserDiffCallback) {


    class ViewHolder(view: View, onClick: (MainActivity.Recipe) -> Unit) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.recipeName)
        private val description = view.findViewById<TextView>(R.id.recipeDescription)
        private var recipe : MainActivity.Recipe? = null

        init {
            view.setOnClickListener{
                recipe?.let {
                    onClick(it)
                }
            }
        }

        fun bind(recipe: MainActivity.Recipe) {
            this.recipe = recipe
            name.text = recipe.name
            description.text = recipe.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.list_recipe, parent, false)
        return ViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    object UserDiffCallback : DiffUtil.ItemCallback<MainActivity.Recipe>(){
        override fun areItemsTheSame(
            oldItem: MainActivity.Recipe,
            newItem: MainActivity.Recipe
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MainActivity.Recipe,
            newItem: MainActivity.Recipe
        ): Boolean {
            return oldItem.name == newItem.name && oldItem.description == oldItem.description
        }

    }
}