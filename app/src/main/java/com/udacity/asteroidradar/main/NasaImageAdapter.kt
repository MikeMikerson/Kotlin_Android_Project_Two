package com.udacity.asteroidradar.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

// TODO: Create Javadoc
class NasaImageAdapter(val onClickListener: AsteroidClick) :
        RecyclerView.Adapter<NasaImageViewHolder>() {
    var asteroids: List<Asteroid> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaImageViewHolder {
        val withDataBinding: AsteroidItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                NasaImageViewHolder.LAYOUT,
                parent,
                false)
        return NasaImageViewHolder(withDataBinding)
    }

    override fun getItemCount(): Int {
        return asteroids.size
    }

    override fun onBindViewHolder(holder: NasaImageViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.asteroid = asteroids[position]
            it.asteroidClickCallback = onClickListener
        }
    }
}

class NasaImageViewHolder(val viewDataBinding: AsteroidItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.asteroid_item
    }
}

class AsteroidClick(val block: (Asteroid) -> Unit) {
    fun onClick(asteroid: Asteroid) = block(asteroid)
}