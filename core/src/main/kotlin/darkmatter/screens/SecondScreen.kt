package darkmatter.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import darkmatter.DarkMatter
import ktx.app.KtxScreen
import ktx.log.Logger

private val log = Logger("Second Screen")

class SecondScreen(game: DarkMatter) : DarkMatterScreen(game) {
    override fun show() {
        log.debug { "Second Screen is shown" }
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.setScreen<FirstScreen>()
        }
    }
}
