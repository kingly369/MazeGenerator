import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.*;

public class Maze extends Canvas implements Runnable{

    private Thread thread;
    private boolean running;
    private int positionX;
    private int positionY;
    private handler handler;
    private int sleeper;

    public Maze()
    {
        handler = new handler();
        sleeper = 0;
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop()
    {
        try{
            thread.join();
            running = false;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }




    @Override
    public void run()
    {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1)
            {

                tick();
                delta--;
            }
            if(running)
            {
                render();
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
        stop();


    }

    private void tick()
    {
        if(sleeper%2 == 0) {
            handler.game();
        }
        sleeper++;
    }

    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,1000,1000);

        handler.render(g);
        g.dispose();
        bs.show();
    }

}
