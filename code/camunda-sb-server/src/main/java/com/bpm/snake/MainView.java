package com.bpm.snake;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MainView extends Application {
    private int width=400;
    private int height=400;
    private int initsize=5;
    private int foodx;
    private int foody;
    private List<MyReact> reacts;


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    private void initGame(Canvas canvas)
    {
        for (int i=0;i<initsize;i++)
        {
            reacts.add(new MyReact(canvas,10*i,0));

        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane pane=new AnchorPane();

        reacts=new ArrayList<>();
        Canvas canvas=new Canvas(width,height);
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        initGame(canvas);

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                canvas.requestFocus();
            }
        });

        addfood(reacts,canvas);
        pane.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {

                int tempx=0;
                int tempy=0;
                if (keyEvent.getCode().getName().equals(KeyCode.D.getName()))
                {
                    tempx=10;
                    tempy=0;
                }
                if (keyEvent.getCode().getName().equals(KeyCode.A.getName()))
                {
                    tempx=-10;
                    tempy=0;
                }
                if (keyEvent.getCode().getName().equals(KeyCode.S.getName()))
                {
                    tempx=0;
                    tempy=10;
                }
                if (keyEvent.getCode().getName().equals(KeyCode.W.getName()))
                {
                    tempx=0;
                    tempy=-10;
                }
                if (tempx!=0 || tempy!=0)
                {
                    Iterator iterator=reacts.iterator();
                    while (iterator.hasNext())
                    {
                        MyReact react=(MyReact) iterator.next();
                        react.remove();
                    }
                    iterator=reacts.iterator();
                    for (int i = 0; i <= reacts.size() - 2; i++) {
                        reacts.get(i).setLocation(reacts.get(i + 1).x, reacts.get(i + 1).y);
                    }

                    reacts.get(reacts.size() - 1).move(tempx, tempy);
                }
                if (check(reacts))
                {
                    int lastXIndex=reacts.get(reacts.size()-1).x;
                    int lastYIndex=reacts.get(reacts.size()-1).y;
                    if (tempx>0 && tempy==0)
                    {
                        reacts.add(new MyReact(canvas,lastXIndex+10,lastYIndex));
                    }
                    else if (tempx<0 && tempy==0)
                    {
                        reacts.add(new MyReact(canvas,lastXIndex-10,lastYIndex));
                    }
                    else if (tempx==0 && tempy>0)
                    {
                        reacts.add(new MyReact(canvas,lastXIndex,lastYIndex+10));
                    }
                    else if (tempx==0 && tempy<0)
                    {
                        reacts.add(new MyReact(canvas,lastXIndex,lastYIndex-10));
                    }

                    addfood(reacts,canvas);
                }
                failedCheck(reacts,stage,canvas);
            }
        });

        pane.getChildren().add(canvas);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Ã∞≥‘…ﬂ");
        stage.show();
        canvas.requestFocus();


    }
    private void FailurePopup(Stage stage,List<MyReact> reacts,Canvas canvas)
    {
        DialogPane pane=new DialogPane();
        pane.setContentText("”Œœ∑ ß∞‹");
        pane.getButtonTypes().add(ButtonType.CLOSE);
        Button button=(Button) pane.lookupButton(ButtonType.CLOSE);
        Stage stage1=new Stage();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage1.close();
                clearAll(reacts);
                initGame(canvas);
            }
        });
        Scene scene=new Scene(pane);
        stage1.initOwner(stage);
        stage1.initModality(Modality.WINDOW_MODAL);
        stage1.setScene(scene);
        stage1.show();
    }
    private void failedCheck(List<MyReact> reacts,Stage stage,Canvas canvas)
    {
        Iterator iterator=reacts.iterator();
        MyReact react=reacts.get(reacts.size()-1);
        for (int i=0;i<reacts.size()-1;i++)
        {
            if (react.x==reacts.get(i).x && react.y==reacts.get(i).y)
            {
                FailurePopup(stage,reacts,canvas);
            }
        }
        if (react.x<0 || react.x>390 || react.y<0 || react.y>390)
        {
            FailurePopup(stage,reacts,canvas);
        }

    }
    private void clearAll(List<MyReact> reacts)
    {
        Iterator iterator=reacts.iterator();
        while (iterator.hasNext())
        {
            MyReact react=(MyReact) iterator.next();
            react.remove();
        }
        reacts.clear();
    }
    private void addfood(List<MyReact> reacts,Canvas canvas)
    {
        Random random=new Random();
        int x=random.nextInt(38);
        int y=random.nextInt(38);
        while (true) {
            Iterator iterator = reacts.iterator();
            while (iterator.hasNext()) {
                MyReact react = (MyReact) iterator.next();
                if (react.x==x && react.y==y)
                {
                    x=random.nextInt(38);
                    y=random.nextInt(38);
                    iterator = reacts.iterator();
                }
            }
            break;
        }
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.fillRect(x*10,y*10,10,10);
        graphicsContext2D.setFill(Color.rgb(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
        this.foodx=x*10;
        this.foody=y*10;
    }
    private boolean check(List<MyReact> reacts)
    {
        MyReact react=reacts.get(reacts.size()-1);
        if (react.x==foodx && react.y==foody)
        {
            return true;
        }
        return false;
    }

	public static void main(String[] args) {
		launch(args);
	}
}
