package com.ghdev.moblieprogrammingpractice.eighth;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.ghdev.moblieprogrammingpractice.R;

public class EighthActivity extends AppCompatActivity {

	 ImageView zoom_in_btn, zoom_out_btn, rotation_btn, brightness_btn;
	 LinearLayout pikachu_view;
	MyView myView;
	static float scaleX = 1, scaleY = 1;
	static float angle = 0;
	static float satur = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eighth);

		init();
		onclickItem();
	}

	public void init() {
		zoom_in_btn = (ImageView) findViewById(R.id.zoom_in_eight_act);
		zoom_out_btn = (ImageView) findViewById(R.id.zoom_out_eight_act);
		rotation_btn = (ImageView)findViewById(R.id.rotation_eight_act);
		brightness_btn = (ImageView)findViewById(R.id.paint_eight_act);
		pikachu_view = (LinearLayout) findViewById(R.id.pikachu_view);
		myView = (MyView) new MyView(this);
		pikachu_view.addView(myView);

	}

	private static class MyView extends View {
		public MyView(Context context){
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas){
			super.onDraw(canvas);

			Bitmap pikachu = BitmapFactory.decodeResource(getResources(), R.drawable.pikachu);

			int cenX = this.getWidth() / 2;
			int cenY = this.getHeight() / 2;
			int picX = (this.getWidth() - pikachu.getWidth()) / 2;
			int picY = (this.getHeight() - pikachu.getHeight()) / 2;

			Paint paint = new Paint();
			ColorMatrix cm = new ColorMatrix();
			if (satur == 0) cm.setSaturation(satur);
			paint.setColorFilter(new ColorMatrixColorFilter(cm));


			canvas.scale(scaleX, scaleY, cenX, cenY);
			canvas.rotate(angle, cenX, cenY);

			canvas.drawBitmap(pikachu, picX, picY, paint);

			pikachu.recycle();

		}


	}

	private void onclickItem() {
		zoom_in_btn.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				scaleX = scaleX + 0.2f;
				scaleY = scaleY + 0.2f;
				myView.invalidate();
			}
		});

		zoom_out_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				scaleX = scaleX - 0.2f;
				scaleY = scaleY - 0.2f;
				myView.invalidate();
			}
		});

		rotation_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				angle = angle + 20;
				myView.invalidate();
			}
		});

		brightness_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(satur == 0) satur = 1;
				else satur = 0;
				myView.invalidate();
			}
		});
	}


}
