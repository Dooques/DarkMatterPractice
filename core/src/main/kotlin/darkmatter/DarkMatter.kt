package darkmatter

import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import darkmatter.screens.FirstScreen
import darkmatter.screens.SecondScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.Logger

private val log = Logger("Dark Matter")
class DarkMatter : KtxGame<KtxScreen>() {

    override fun create() {
        Gdx.app.logLevel = LOG_DEBUG
        log.debug { "Create game instance" }
        addScreen(FirstScreen(game = this))
        addScreen(SecondScreen(game = this))
        setScreen<FirstScreen>()
    }
}
