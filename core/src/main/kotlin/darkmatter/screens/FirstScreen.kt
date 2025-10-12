package darkmatter.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import darkmatter.DarkMatter
import ktx.app.KtxScreen
import ktx.log.Logger

private val log = Logger(name = "First Screen")

class FirstScreen(game: DarkMatter) : DarkMatterScreen(game) {
    override fun show() {
        log.debug { "First screen is shown" }
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.setScreen<SecondScreen>()
        }
    }
}
