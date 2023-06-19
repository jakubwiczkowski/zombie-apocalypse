package pl.edu.pwr.student.zombiesim.simulation.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Class used to store all fonts that are used in the simulation.
 */
public class Fonts {

    /**
     * MAin font used in the simulation.
     * Pixeloid Mono.
     */
    public static BitmapFont MAIN_FONT;

    static {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixeloid-mono.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.BLACK;
        MAIN_FONT = generator.generateFont(parameter);
        generator.dispose();
    }

    private Fonts(){
    }
}
