package darkmatter.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.viewport.FitViewport
import darkmatter.DarkMatter
import darkmatter.UNIT_SCALE
import darkmatter.ecs.components.FacingComponent
import darkmatter.ecs.components.GraphicComponent
import darkmatter.ecs.components.PlayerComponent
import darkmatter.ecs.components.TransformComponent
import ktx.ashley.entity
import ktx.ashley.get
import ktx.ashley.with
import ktx.graphics.use
import ktx.log.Logger

private val LOG = Logger(name = "First Screen")

class GameScreen(game: DarkMatter) : DarkMatterScreen(game) {

    override fun show() {
        LOG.debug { "First screen is shown" }

            engine.entity {
                with<TransformComponent> {
                    position.set(4f, 8f, 0f)
                }

                with<GraphicComponent>()
                with<PlayerComponent>()
                with<FacingComponent>()

            }

        engine.entity {
                with<TransformComponent> {
                    position.set(0f, 1f, 0f)
                }

                with<GraphicComponent> {
                    setSpriteRegion(game.shipAtlas.findRegion("ship_left"))
                }

            }

        engine.entity {
                with<TransformComponent> {
                    position.set(8f, 1f, 0f)
                }

                with<GraphicComponent> {
                    setSpriteRegion(game.shipAtlas.findRegion("ship_right"))
                }
            }
    }

    override fun render(delta: Float) {
        val spriteBatch = (game.batch as SpriteBatch)
        spriteBatch.renderCalls = 0
        engine.update(delta)
        LOG.debug { "Render Calls: ${spriteBatch.renderCalls}" }
    }

    override fun dispose() = Unit
}
