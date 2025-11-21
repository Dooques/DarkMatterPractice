package darkmatter.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import darkmatter.DarkMatter
import darkmatter.UNIT_SCALE
import darkmatter.V_WIDTH
import darkmatter.ecs.components.*
import darkmatter.ecs.event.GameEvent
import darkmatter.ecs.event.GameEventListener
import darkmatter.ecs.systems.DAMAGE_AREA_HEIGHT
import ktx.ashley.entity
import ktx.ashley.with
import ktx.log.Logger
import kotlin.math.min

private val LOG = Logger(name = "First Screen")
private const val MAX_DELTA_TIME = 1 / 20f

class GameScreen(game: DarkMatter): DarkMatterScreen(game), GameEventListener {

    override fun show() {
        LOG.debug { "First screen is shown" }
        gameEventManager.addListener(GameEvent.PlayerDeath::class, this)

        spawnPlayer()

        engine.entity {
            // Dark Matter
            with<TransformComponent> {
                size.set(V_WIDTH.toFloat(), DAMAGE_AREA_HEIGHT)
            }
            with<AnimationComponent> { type = AnimationType.DARK_MATTER }
            with<GraphicComponent>()
        }
    }

    private fun spawnPlayer() {
        val playerShip = engine.entity {
            // Player Ship
            with<TransformComponent> {
                setInitialPosition(4.5f, 8f, -1f)
            }

            with<MoveComponent>()
            with<GraphicComponent>()
            with<PlayerComponent>()
            with<FacingComponent>()
        }

        engine.entity {
            // Fire Effect
            with<TransformComponent>()
            with<AttachComponent> {
                entity = playerShip
                offset.set(1f * UNIT_SCALE, -6f * UNIT_SCALE)
            }
            with<GraphicComponent>()
            with<AnimationComponent> { type = AnimationType.FIRE }
        }
    }

    override fun render(delta: Float) {
        val spriteBatch = (game.batch as SpriteBatch)
        spriteBatch.renderCalls = 0
        engine.update(min(MAX_DELTA_TIME, delta))
        LOG.debug { "Render Calls: ${spriteBatch.renderCalls}" }
    }

    override fun onEvent(event: GameEvent) {
            val playerDeath = event as GameEvent.PlayerDeath
            spawnPlayer()
    }

    override fun hide() {
        super.hide()
        gameEventManager.removeListener(this)
    }
}
