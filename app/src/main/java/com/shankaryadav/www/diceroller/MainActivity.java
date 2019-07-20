package com.shankaryadav.www.diceroller;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    List<Integer> player_one_list = new ArrayList<> ();
    List<Integer> player_two_list = new ArrayList<> ();



    private Random random;
    ImageView image_big_one;
    ImageView image_big_two;
    ImageView imgv_one;
    ImageView imgv_two;
    TextView txt_one;
    TextView txt_two;
    private int last = 0;

    boolean winvisibility = false;

    int player = 0;

    // 0 for player 1
    // 1 for player 2


    int player_one_val = 0;
    int player_two_val = 0;


    TextView textView;
    int val = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        player_one_list.add (R.drawable.dice_one);
        player_one_list.add (R.drawable.dice_two);
        player_one_list.add (R.drawable.dice_three);
        player_one_list.add (R.drawable.dice_four);
        player_one_list.add (R.drawable.dice_five);
        player_one_list.add (R.drawable.dice_six);


        player_two_list.add (R.drawable.dice_one_pink);
        player_two_list.add (R.drawable.dice_two_pink);
        player_two_list.add (R.drawable.dice_three_pink);
        player_two_list.add (R.drawable.dice_four_pink);
        player_two_list.add (R.drawable.dice_five_pink);
        player_two_list.add (R.drawable.dice_six_pink);

         image_big_one = findViewById (R.id.player_one);
        image_big_two = findViewById (R.id.player_two);
         textView = findViewById (R.id.abc);
         imgv_one = findViewById (R.id.img_one);
         imgv_two = findViewById (R.id.img_two);
         txt_one = findViewById (R.id.text_one);
         txt_two = findViewById (R.id.text_two);

         textView.setVisibility (View.INVISIBLE);


txt_one.setBackgroundResource (R.drawable.cover_imageview);


//        GradientDrawable shape =  new GradientDrawable();
//        shape.setCornerRadius( 8 );
//
//        // add some color
//        // You can add your random color generator here
//        // and set color
//        if (i % 2 == 0) {
//            shape.setColor(Color.RED);
//        } else {
//            shape.setColor(Color.BLUE);
//        }
//
//        // now find your view and add background to it
//        View view = (LinearLayout) findViewById( R.id.my_view );
//        view.setBackground(shape);




//        imageView.setOnClickListener (new View.OnClickListener () {
//            @Override
//            public void onClick(View v) {
//                textView.setVisibility (View.VISIBLE);
//                YoYo.with(Techniques.Tada)
//                        .duration(700)
//                        .repeat(5)
//                        .playOn(findViewById(R.id.abc));
//            }
//        });



    }

    public void setBack(){
        GradientDrawable  shape =  new GradientDrawable();
        shape.setCornerRadius( 8 );
        shape.setColor(Color.LTGRAY);
        View view = (ImageView) findViewById( R.id.player_one);
        view.setBackground(shape);
    }





    public void spin(View view) {


        if (winvisibility){
            textView.setVisibility (View.INVISIBLE);
            winvisibility = false;
        }

        if (player == 0){
            rotate (imgv_one);
            player_one_val = val;
            txt_one.setBackgroundResource (0);
            txt_two.setBackgroundResource (R.drawable.cover_imageview);
            player = 1;


        }else {
            rotate (imgv_two);
            player_two_val = val;
            txt_two.setBackgroundResource (0);
            txt_one.setBackgroundResource (R.drawable.cover_imageview);
            player = 0;



        }





    }


    public void rotate(ImageView imgv){

        random = new Random ();
        int angle =  (3600);
        val = (random.nextInt (6)+1);

        int h = imgv.getHeight ()/2;
        int w = imgv.getWidth ()/2;

        Animation animation = new RotateAnimation (last,angle,w,h);

        animation.setDuration (2000);

        animation.setFillAfter (true);

        animation.setAnimationListener (new Animation.AnimationListener () {
            @Override
            public void onAnimationStart(Animation animation) {
                if (player == 1){
                    rotate_big_dice (image_big_one);


                }else {
                    rotate_big_dice (image_big_two);


                }


            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (player == 1){

                    for (int i = 1;i<=6;i++){
                        if (val == i){
                            image_big_one.setImageResource (player_one_list.get (val-1));
                        }
                    }



                }else {



                    for (int i = 1;i<=6;i++){
                        if (val == i){


                            image_big_two.setImageResource (player_two_list.get (val-1));

                        }
                    }



                    if (player_one_val == player_two_val){
                        textView.setText ("Player Two \n wins");
                        textView.setTextColor (Color.GREEN);
                        textView.setVisibility (View.VISIBLE);
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .repeat(5)
                                .playOn(findViewById(R.id.abc));

                        winvisibility = true;
                    }else {
                        textView.setText ("Player One \n wins");
                        textView.setTextColor (Color.GREEN);
                        textView.setVisibility (View.VISIBLE);
                        YoYo.with(Techniques.Tada)
                                .duration(700)
                                .repeat(5)
                                .playOn(findViewById(R.id.abc));

                        winvisibility = true;
                    }


                }




            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        imgv.startAnimation (animation);

    }

    public void rotate_big_dice(ImageView imageview){
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext (), R.animator.flip_animator);
        anim.setTarget(imageview);
        anim.setDuration(3000);
        anim.start();
    }

}
