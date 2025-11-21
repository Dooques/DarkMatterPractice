package darkmatter

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.viewport.FitViewport
import darkmatter.ecs.event.GameEventManager
import darkmatter.ecs.systems.AnimationSystem
import darkmatter.ecs.systems.AttachSystem
import darkmatter.ecs.systems.DamageSystem
import darkmatter.ecs.systems.DebugSystem
import darkmatter.ecs.systems.MoveSystem
import darkmatter.ecs.systems.PlayerAnimationSystem
import darkmatter.ecs.systems.PlayerInputSystem
import darkmatter.ecs.systems.PowerUpSystem
import darkmatter.ecs.systems.RemoveSystem
import darkmatter.ecs.systems.RenderSystem
import darkmatter.screens.DarkMatterScreen
import darkmatter.screens.GameScreen
import darkmatter.screens.SecondScreen
import ktx.app.KtxGame
import ktx.log.Logger

const val UNIT_SCALE = 1/16f
const val V_WIDTH = 9
const val V_HEIGHT = 16
const val V_WIDTH_PIXELS = 135
const val V_HEIGHT_PIXELS = 240
private val LOG = Logger("Dark Matter")


class DarkMatter : KtxGame<DarkMatterScreen>() {

    val gameViewport = FitViewport(V_WIDTH.toFloat(), V_HEIGHT.toFloat())
    val uiViewport = FitViewport(V_WIDTH_PIXELS.toFloat(), V_HEIGHT_PIXELS.toFloat())
    val batch: Batch by lazy { SpriteBatch() }
    val gameEventManager = GameEventManager()

    val graphicsAtlas by lazy { TextureAtlas(Gdx.files.internal("graphics/graphics.atlas")) }
    val backgroundTexture by lazy { Texture("graphics/background.png") }

    val engine: Engine by lazy { PooledEngine().apply {
        // Add Engines in order of priority
        addSystem(PlayerInputSystem(gameViewport))
        addSystem(MoveSystem())
        addSystem(PowerUpSystem(gameEventManager))
        addSystem(DamageSystem(gameEventManager))
        addSystem(PlayerAnimationSystem(
            graphicsAtlas.findRegion("ship_base"),
            graphicsAtlas.findRegion("ship_left"),
            graphicsAtlas.findRegion("ship_right")
        ))
        addSystem(AttachSystem())
        addSystem(AnimationSystem(graphicsAtlas))
        addSystem(RenderSystem(batch, gameViewport, uiViewport, backgroundTexture, gameEventManager))
        addSystem(RemoveSystem())
        addSystem(DebugSystem())
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

        graphicsAtlas.dispose()
        backgroundTexture.dispose()
    }
}
