package uz.gita.dictionaryuzen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import uz.gita.dictionaryuzen.databinding.ActivityMainBinding
import uz.gita.dictionaryuzen.ui.screen.FavoriteScreen
import uz.gita.dictionaryuzen.ui.screen.SearchScreen

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.container, SearchScreen()).commit()
        binding.bottomNavigationMain.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, SearchScreen())
                        .commit()
                    return@OnItemSelectedListener true
                }
                R.id.action_favourite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, FavoriteScreen()).commit()
                    return@OnItemSelectedListener true
                }
            }
            false
        })
    }
}