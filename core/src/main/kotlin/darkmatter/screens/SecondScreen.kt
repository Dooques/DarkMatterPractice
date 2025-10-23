package darkmatter.screens

import darkmatter.DarkMatter
import ktx.log.Logger

private val LOG = Logger("Second Screen")

class SecondScreen(game: DarkMatter) : DarkMatterScreen(game) {

    override fun show() {
        LOG.debug { "Second Screen is shown" }
    }
}
