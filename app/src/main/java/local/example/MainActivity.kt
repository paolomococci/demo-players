/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.demoplayers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private var scorePlayerOne: Int = 0
    private var scorePlayerTwo: Int = 0

    private var scoreTextOne: TextView? = null
    private var scoreTextTwo: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreTextOne = findViewById(R.id.score_player_one)
        scoreTextTwo = findViewById(R.id.score_player_two)

        if (savedInstanceState != null) {
            scorePlayerOne = savedInstanceState.getInt(NAME_PLAYER_ONE)
            scorePlayerTwo = savedInstanceState.getInt(NAME_PLAYER_TWO)

            scoreTextOne!!.text = scorePlayerOne.toString()
            scoreTextTwo!!.text = scorePlayerTwo.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.sun_light_mode).setTitle(R.string.sun_light_mode)
        } else {
            menu.findItem(R.id.moon_light_mode).setTitle(R.string.moon_light_mode)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.moon_light_mode) {
            val nightMode = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(NAME_PLAYER_ONE, scorePlayerOne)
        outState.putInt(NAME_PLAYER_TWO, scorePlayerTwo)
        super.onSaveInstanceState(outState)
    }

    fun decreaseScore(view: View) {
        when (view.id) {
            R.id.decrease_player_one -> {
                scorePlayerOne--
                scoreTextOne!!.text = scorePlayerOne.toString()
            }
            R.id.decrease_player_two -> {
                scorePlayerTwo--
                scoreTextTwo!!.text = scorePlayerTwo.toString()
            }
        }
    }

    fun increaseScore(view: View) {
        when (view.id) {
            R.id.increase_player_one -> {
                scorePlayerOne++
                scoreTextOne!!.text = scorePlayerOne.toString()
            }
            R.id.increase_player_two -> {
                scorePlayerTwo++
                scoreTextTwo!!.text = scorePlayerTwo.toString()
            }
        }
    }

    companion object {
        internal const val NAME_PLAYER_ONE = "score player one"
        internal const val NAME_PLAYER_TWO = "score player two"
    }
}
