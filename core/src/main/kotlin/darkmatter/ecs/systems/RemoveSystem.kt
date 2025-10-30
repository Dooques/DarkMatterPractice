package darkmatter.ecs.systems

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import darkmatter.ecs.components.RemoveComponent
import ktx.ashley.allOf
import ktx.ashley.get

class RemoveSystem : IteratingSystem(allOf(RemoveComponent::class).get()) {
    override fun processEntity(entity: Entity, deltaTime: Float) {
        val remove = entity[RemoveComponent.mapper]
        require(remove != null) {"Entity |entity| must have a RemoveComponent. entity = $entity" }

        remove.delay -= deltaTime
        if (remove.delay < 0) { engine.removeEntity(entity) }
    }
}
