package com.example.timviec.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.timviec.App;
import com.example.timviec.R;
import com.example.timviec.Utils;
import com.example.timviec.components.CustomButton;
import com.example.timviec.services.StateManagerService;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class JobDetailScreen extends Utils.BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private StateManagerService stateManager = App.getContext().getStateManager();
    private boolean applyPost = false;
    private CustomButton applyButton;
    private CustomButton messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_detail);

        setUpScreen("Chi tiết việc làm");

        mTabLayout = findViewById(R.id.job_detail_tab);
        mViewPager = findViewById(R.id.job_detail_view_pager);
        View child = mViewPager.getChildAt(0);
        if (child instanceof RecyclerView) {
            child.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

        if (stateManager.getUser().getRoleId() == 2) {
            findViewById(R.id.job_detail_button_wrapper).setVisibility(View.GONE);
        } else {
            messageButton = findViewById(R.id.job_detail_message_button);
            applyButton = findViewById(R.id.job_detail_apply_button);

            handleApplyButton();

            messageButton.setHandleOnClick(new Runnable() {
                @Override
                public void run() {
                    sendEmail("test@gmail.com");
                }
            });

            applyButton.setHandleOnClick(new Runnable() {
                @Override
                public void run() {
                    applyPost = !applyPost;
                    handleApplyButton();
                }
            });
        }

        setUpTabView();
    }

    private void setUpTabView() {
        JobDetailAdapter adapter = new JobDetailAdapter(this);
        mViewPager.setAdapter(adapter);
        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Thông tin");
                    break;
                case 1:
                    tab.setText("Công ty");
                    break;
            }
        }).attach();
    }

    private void handleApplyButton() {
        if (applyPost) {
            applyButton.setColor(getResources().getColor(R.color.error));
            applyButton.setButtonType(1);
            applyButton.setButtonText("Huỷ ứng tuyển");
        } else {
            applyButton.setColor(getResources().getColor(R.color.primary));
            applyButton.setButtonType(0);
            applyButton.setButtonText("Ứng tuyển");
        }
    }

    public void sendEmail(String email) {
        String subject = "Thắc mắc về bài tuyển dụng";
        String body = String.format("Dear %s", email);
        Uri uri = Uri.parse("mailto:")
                .buildUpon()
                .appendQueryParameter("to", email)
                .appendQueryParameter("subject", subject)
                .appendQueryParameter("body", body)
                .build();

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
        startActivity(Intent.createChooser(emailIntent, "Gửi thắc mắc tới nhà tuyển dụng"));
    }
}

class JobDetailAdapter extends FragmentStateAdapter {

    public JobDetailAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new JobDetailInformation();
            case 1:
                return new JobDetailCompany();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}