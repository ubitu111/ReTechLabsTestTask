package ru.kireev.retechlabstesttask.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import ru.kireev.retechlabstesttask.R

class MainActivity : AppCompatActivity() {

    private val imageFragment: ImageFragment by inject()
    private val factsFragment: FactsFragment by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            replaceFragments()
        }
    }

    private fun replaceFragments() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container_image, imageFragment)
            .replace(R.id.container_facts, factsFragment)
            .commit()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.refresh_menu) {
            imageFragment.loadImage()
            factsFragment.loadFact()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}