package com.example.lichaoqiang.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelfDialog extends Dialog {
    private Button yes;
    private Button no;
    private TextView titleTv;   //消息标题文本
    private TextView messageTv; //消息提示文本
    private String titleStr;    //从外界设置的title文本
    private String messageStr;  //从外界设置的消息文本
    //确定文本和取消文本的显示内容
    private String yesStr,noStr;

    private onNoOnclickListener noOnclickListener;  //监听器
    private onYesOnclickListener yesOnclickListener;

    //取消按钮的显示内容和监听
    public void setNoOnclickListener(String str,onNoOnclickListener onNoOnclickListener){
        if (str != null){
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    //确定按钮的显示和监听
    public void setYesOnclickListener(String str,onYesOnclickListener onYesOnclickListener){
        if (str != null){
            yesStr = str;
        }
        this.yesOnclickListener = onYesOnclickListener;
    }

    public SelfDialog(Context context){
        super(context,R.style.MyDialog);//在构造函数中引用自定义的主题
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mydialog);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);

        //初始化界面控件
        initView();
        //初始化界面数据
        initData();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private void initEvent(){
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yesOnclickListener != null){
                    yesOnclickListener.onYesClick();
                }
            }
        });
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (noOnclickListener != null){
                    noOnclickListener.onNoClick();
                }
            }
        });
    }
    /**
     * 初始化界面控件的显示数据
     */
    private void initData(){
        //如果用户自定义了title和message
        if (titleStr != null){
            titleTv.setText(titleStr);
        }
        if (messageStr != null){
            messageTv.setText(messageStr);
        }
        //如果设置按钮的文字
        if (yesStr != null){
            yes.setText(yesStr);
        }
        if (noStr != null){
            no.setText(noStr);
        }
    }
    /**
     *初始化界面控件
     */
    private void initView(){
        yes = (Button)findViewById(R.id.yes);
        no = (Button)findViewById(R.id.no);
        titleTv = (TextView)findViewById(R.id.title);
        messageTv = (TextView)findViewById(R.id.message);
    }
    /**
     * 从外界Activity为Dialog设置标题
     *
     * @param title
     */
    public void setTitle(String title){
        titleStr = title;
    }

    /**
     * 从外界Activity为Dialog设置dialog的message
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }
    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onYesOnclickListener{
        public void onYesClick();
    }
    public interface onNoOnclickListener{
        public void onNoClick();
    }
}
