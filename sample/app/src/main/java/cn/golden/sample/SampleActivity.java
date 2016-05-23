package cn.golden.sample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import cn.golden.pinchzoomcanvasview.PinchZoomCanvasView;


public class SampleActivity extends AppCompatActivity implements PinchZoomCanvasView.OnKeyBoardListener {


    private PinchZoomCanvasView mCanvasView;

    private FloatingActionMenu floatingActionMenu;
    private FloatingActionButton pen;
    private FloatingActionButton text;
    private FloatingActionButton rectangle;
    private FloatingActionButton move;
    private FloatingActionButton undo;
    private FloatingActionButton reset;
    private RelativeLayout inputLayout;
    private RelativeLayout.LayoutParams inputParams;

    private TextView confirm;
    private EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        mCanvasView = (PinchZoomCanvasView) findViewById(R.id.canvasview);
        mCanvasView.setImageResource(R.mipmap.test);

        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu);
        inputLayout = (RelativeLayout) findViewById(R.id.input_layout);
        confirm = (TextView)findViewById(R.id.confirm);
        input = (EditText) findViewById(R.id.input);

        floatingActionMenu.setClosedOnTouchOutside(true);

        pen = (FloatingActionButton) findViewById(R.id.pen);
        text = (FloatingActionButton) findViewById(R.id.text);
        rectangle = (FloatingActionButton) findViewById(R.id.rectangle);
        move = (FloatingActionButton) findViewById(R.id.move);
        undo = (FloatingActionButton) findViewById(R.id.undo);
        reset = (FloatingActionButton) findViewById(R.id.reset);

        pen.setOnClickListener(clickListener);
        text.setOnClickListener(clickListener);
        rectangle.setOnClickListener(clickListener);
        move.setOnClickListener(clickListener);
        undo.setOnClickListener(clickListener);
        reset.setOnClickListener(clickListener);

        floatingActionMenu.getMenuIconView().setImageResource(R.mipmap.move);
        mCanvasView.setOnKeyBoardListener(this);
        createCustomAnimation();

        initInputLayout();
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pen:
                    mCanvasView.setPenMode(PinchZoomCanvasView.PEN);
                    floatingActionMenu.close(true);
                    floatingActionMenu.getMenuIconView().setImageResource(R.mipmap.pen);
                    break;
                case R.id.text:
                    floatingActionMenu.close(true);
                    floatingActionMenu.getMenuIconView().setImageResource(R.mipmap.text);
                    mCanvasView.setPenMode(PinchZoomCanvasView.TEXT);
                    Toast.makeText(getApplicationContext(), "请点击输入的位置", Toast.LENGTH_LONG).show();
                    break;
                case R.id.rectangle:
                    floatingActionMenu.close(true);
                    floatingActionMenu.getMenuIconView().setImageResource(R.mipmap.rec);
                    mCanvasView.setPenMode(PinchZoomCanvasView.RECTANGLE);
                    break;
                case R.id.move:
                    floatingActionMenu.close(true);
                    floatingActionMenu.getMenuIconView().setImageResource(R.mipmap.move);
                    mCanvasView.setInteractionMode(PinchZoomCanvasView.SELECT_MODE);
                    break;
                case R.id.undo:
                    mCanvasView.undo();
                    break;
                case R.id.reset:
                    floatingActionMenu.close(true);
                    showCustomDialog();
                    break;
                case R.id.confirm:
                    mCanvasView.drawText(input.getText().toString());
                    disableEditText();
                    break;
                default:
                    break;
            }
        }
    };

    private void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleY", 1.0f, 0.2f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(floatingActionMenu.getMenuIconView(), "scaleY", 0.2f, 1.0f);

        scaleOutX.setDuration(50);
        scaleOutY.setDuration(50);

        scaleInX.setDuration(150);
        scaleInY.setDuration(150);


        set.play(scaleOutX).with(scaleOutY);
        set.play(scaleInX).with(scaleInY).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        floatingActionMenu.setIconToggleAnimatorSet(set);
    }

    private void showCustomDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("是否重置").setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mCanvasView.reset();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    @Override
    public void onShowKeyBoard(float x, float y) {
        mCanvasView.setTextExpectTouch(false);
        inputLayout.setVisibility(View.VISIBLE);

        inputParams.leftMargin = (int)x;
        inputParams.topMargin = (int)y;

        inputLayout.setLayoutParams(inputParams);
    }

    private void initInputLayout() {
        inputParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                ,RelativeLayout.LayoutParams.WRAP_CONTENT);
        confirm.setOnClickListener(clickListener);
    }

    private void disableEditText() {
        input.setText("");
        inputLayout.setVisibility(View.GONE);
        mCanvasView.setTextExpectTouch(true);
    }


}
