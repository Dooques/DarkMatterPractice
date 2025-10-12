package lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import darkmatter.DarkMatter

/** Launches the desktop (LWJGL3) application.  */
fun main() {
    Lwjgl3Application(DarkMatter(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("DarkMatterPracticeGame")
        /* Vsync limits the frames per second to what your hardware can display, and helps eliminate
        * / screen tearing. This setting doesn't always work on Linux, so the line after is a safeguard. */
        useVsync(true)
        /* Limits FPS to the refresh rate of the currently active monitor, plus 1 to try to match fractional
        * / refresh rates. The Vsync setting above should limit the actual FPS to match the monitor. */
        setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate + 1)

        /* If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
        * / useful for testing performance, but can also be very stressful to some hardware.
        * / You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.* /
                configuration.setWindowedMode(640, 480)
        /**/ You can change these files; they are in lwjgl3 / src / main / resources / .
        * / They can also be loaded from the root of assets/ . */
        setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")

        /* This should improve compatibility with Windows machines with buggy OpenGL drivers, Macs
        * / with Apple Silicon that have to emulate compatibility with OpenGL anyway, and more.
        * / This uses the dependency `com.badlogicgames.gdx:gdx-lwjgl3-angle` to function.
        * / You can choose to remove the following line and the mentioned dependency if you want; they
        * / are not intended for games that use GL30 (which is compatibility with OpenGL ES 3.0). */
        setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.ANGLE_GLES20, 0, 0)

    })
}
