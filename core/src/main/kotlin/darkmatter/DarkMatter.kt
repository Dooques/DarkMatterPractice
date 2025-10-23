package darkmatter

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.viewport.FitViewport
import darkmatter.ecs.systems.PlayerAnimationSystem
import darkmatter.ecs.systems.PlayerInputSystem
import darkmatter.ecs.systems.RenderSystem
import darkmatter.screens.DarkMatterScreen
import darkmatter.screens.GameScreen
import darkmatter.screens.SecondScreen
import ktx.app.KtxGame
import ktx.log.Logger

const val UNIT_SCALE = 1/16f
private val LOG = Logger("Dark Matter")
class DarkMatter : KtxGame<DarkMatterScreen>() {

    val gameViewport = FitViewport(9f, 16f)
    val batch: Batch by lazy { SpriteBatch() }

    val shipAtlas by lazy { TextureAtlas(Gdx.files.internal("graphics/ship.atlas")) }

    val engine: Engine by lazy { PooledEngine().apply {
        addSystem(PlayerInputSystem(gameViewport))
        addSystem(PlayerAnimationSystem(
            shipAtlas.findRegion("ship_base"),
            shipAtlas.findRegion("ship_left"),
            shipAtlas.findRegion("ship_right")
        ))
        addSystem(RenderSystem(batch, gameViewport))
    } }

    override fun create() {
        Gdx.app.logLevel = LOG_DEBUG
        LOG.debug { "Create game instance" }
        addScreen(GameScreen(game = this))
        addScreen(SecondScreen(game = this))
        setScreen<GameScreen>()
    }

    override fun dispose() {
        super.dispose()
        LOG.debug { "Sprites in batch: ${(batch as SpriteBatch).maxSpritesInBatch}" }
        batch.dispose()

        shipAtlas.dispose()
    }
}
