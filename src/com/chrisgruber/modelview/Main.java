package com.chrisgruber.modelview;

import com.chrisgruber.modelview.graphics.DisplayManager;
import com.chrisgruber.modelview.graphics.ModelLoader;
import com.chrisgruber.modelview.graphics.RawModel;
import com.chrisgruber.modelview.graphics.Renderer;

public class Main implements Runnable {
    private Thread gameThread;

    public void start() {
        gameThread = new Thread(this, "GameThread");
        gameThread.start();
    }

    @Override
    public void run() {
        DisplayManager.createDisplay();
        DisplayManager.setShowFPSTitle(true);   // TODO: Debug only

        System.out.println("OpenGL: " + DisplayManager.getOpenGlVersionMessage());

        ModelLoader modelLoader = new ModelLoader();
        Renderer renderer = new Renderer();

        // TODO: temp model. Delete later
        float[] modelVertices = {
                // Left bottom triangle
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                // Right top triangle
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
                -0.5f, 0.5f, 0f
        };

        RawModel model = modelLoader.loadToVao(modelVertices);

        while(DisplayManager.shouldDisplayClose()) {
            renderer.prepare();

            // TODO: game logic

            renderer.render(model);

            DisplayManager.updateDisplay();
        }

        modelLoader.Destroy();
        DisplayManager.closeDisplay();
    }

    public static void main(String[] args) {
        new Main().start();
    }
}
