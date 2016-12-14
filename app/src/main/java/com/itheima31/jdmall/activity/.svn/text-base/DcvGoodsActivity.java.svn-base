package com.itheima31.jdmall.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.Toast;

import com.itheima31.jdmall.R;
import com.itheima31.jdmall.view.AutoHorizontalScrollTextView;
import com.itheima31.jdmall.view.AutoVerticalScrollTextView;
import com.itheima31.jdmall.view.MyLotteryWheel;

public class DcvGoodsActivity extends Activity
{
	private int number =0;
	private boolean isRunning=true;

	//================抽奖名单结果
	private String[] strings  ={"阿默斯特.雷活动一台ipad","安东尼.赵四获得一个女朋友","江西.老表获得一个女朋友","人在塔在!","你跑错片场啦!","x获得10圆话费","我的大刀早已饥渴难耐了!"};
	private String[] strings2 ={"尼古拉斯.雷活动一台单反","阿尔.帕西若...获得一个女朋友","杰克.思琪获得一个ipad","北河汉姆..获得一个女朋友!"
			,"赵活动一台单反","王...活动一台单反","张西若获得一个女朋友"};
	private String[] strings3 ={"张西若获得一个女朋友","阿尔.帕西若获得一个女朋友","杰克.思琪获得一个ipad","江西.老表获得一个女朋友"
			,"赵活动一台单反","王活动一台单反","阿尔.帕西若获得一个女朋友"};
	private String[] strings4 ={"张西若获得一个女朋友","阿默斯特.雷活动一台ipad","杰克.思琪获得一个ipad","江西.老表获得一个女朋友"
			,"赵活动一台单反","安东尼.赵四获得一个女朋友","阿尔.帕西若获得一个女朋友"};//private String string="我的剑，就是你的剑!   俺也是从石头里蹦出来得!    我用双手成就你的梦想!    人在塔在!    犯我德邦者，虽远必诛!    我会让你看看什么叫残忍!    我的大刀早已饥渴难耐了!";


	//============跑马灯部分
	private String string ="京东双11大转盘抽奖活动,期待您的参与.     什么天猫双11都弱爆了.........." +
			"我们有抽奖送手机送苹果,**********88重要的是我们还抽奖送女朋友,送女朋友,送女朋友........." +
			"重要的事情要说三遍"+"                京东双11大转盘抽奖活动,期待您的参与.     什么天猫双11都弱爆了.........." +
			"我们有抽奖送手机送苹果,**********88重要的是我们还抽奖送女朋友,送女朋友,送女朋友........." +
			"重要的事情要说三遍"+"                京东双11大转盘抽奖活动,期待您的参与.     什么天猫双11都弱爆了.........." +
			"我们有抽奖送手机送苹果,**********88重要的是我们还抽奖送女朋友,送女朋友,送女朋友........." +
			"重要的事情要说三遍";

	private AutoVerticalScrollTextView verticalScrollTV;
	private AutoHorizontalScrollTextView horizontalScrollTV;
	private AutoVerticalScrollTextView        verticalScrollTV2;
	private AutoVerticalScrollTextView        verticalScrollTV3;
	private AutoVerticalScrollTextView        verticalScrollTV4;

    MyLotteryWheel lotterywheel;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dcv_goods);
		initView();
		
		//控件的使用
		lotterywheel = (MyLotteryWheel) this.findViewById(R.id.lotterywheel);
		lotterywheel.setOnSelectListener(new MyLotteryWheel.SelectListener() {
            @Override
            public void onSelect(String str) {
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
            }
        });
		//控件的使用
	}

	private void initView() {

		horizontalScrollTV = (AutoHorizontalScrollTextView) findViewById(R.id.textview);
		horizontalScrollTV.setText(string);

		verticalScrollTV = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll);
		verticalScrollTV.setText(strings[0]);

		verticalScrollTV2 = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll2);
		verticalScrollTV2.setText(strings2[0]);

		verticalScrollTV3 = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll3);
		verticalScrollTV3.setText(strings3[0]);

		verticalScrollTV4 = (AutoVerticalScrollTextView) findViewById(R.id.textview_auto_roll4);
		verticalScrollTV4.setText(strings4[0]);

		new Thread(){
			@Override
			public void run() {
				while (isRunning){
					SystemClock.sleep(3000);
					handler.sendEmptyMessage(199);
				}
			}
		}.start();
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == 199) {
				verticalScrollTV.next();
				verticalScrollTV2.next();
				verticalScrollTV3.next();
				verticalScrollTV4.next();
				number++;
				verticalScrollTV.setText(strings[number%strings.length]);
				verticalScrollTV2.setText(strings2[number%strings.length]);
				verticalScrollTV3.setText(strings3[number%strings.length]);
				verticalScrollTV4.setText(strings4[number%strings.length]);
			}

		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
		isRunning=false;
	}
}
