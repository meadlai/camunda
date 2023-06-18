package com.bpm.snake;


import java.util.Random;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MyReact {
    public int x;
    public int y;
    private int snakeBodyLen=10;
    private Canvas canvas;
    GraphicsContext context;
    public MyReact(Canvas canvas,int x,int y)
    {
        this.canvas=canvas;
        this.x=x;
        this.y=y;
        Random rand=new Random();
        context=canvas.getGraphicsContext2D();
        context.fillRect(x,y,snakeBodyLen,snakeBodyLen);
        context.setFill(Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
    }
    public void remove()
    {
        context.clearRect(x,y,snakeBodyLen,snakeBodyLen);
    }
    public void move(int xx,int yy)
    {
        Random random=new Random();
        context.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        context.fillRect(x+xx,y+yy,10,10);
        x=x+xx;
        y=y+yy;
    }
    public void setLocation(int x,int y)
    {
        Random random=new Random();
        context.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        context.fillRect(x,y,10,10);
        this.x=x;
        this.y=y;
    }
}
