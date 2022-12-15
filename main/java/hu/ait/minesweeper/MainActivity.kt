package hu.ait.minesweeper

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.ait.minesweeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.minesweeper.resetGame()

        binding.btnReset.setOnClickListener{
            binding.minesweeper.resetGame()
        }
        binding.flagCheck.setOnClickListener{
            binding.minesweeper.revealFlag(Canvas())
        }
    }

    public fun setLoserText(message: String) {
        binding.tvWinLose.text = message
    }

    public fun isFlagModeOn() : Boolean {
        return binding.flagCheck.isChecked
    }
}