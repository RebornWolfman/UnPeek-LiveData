/*
 * Copyright 2018-2020 KunMinX
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.kunminx.puremusic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.kunminx.puremusic.R;
import com.kunminx.puremusic.databinding.ActivityMainBinding;
import com.kunminx.puremusic.ui.base.BaseActivity;
import com.kunminx.puremusic.ui.event.SharedViewModel;
import com.kunminx.puremusic.ui.state.MainViewModel;

/**
 * Create by KunMinX at 19/10/16
 */

public class MainActivity extends BaseActivity {

  private MainViewModel mState;
  private SharedViewModel mEvent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mState = getActivityViewModel(MainViewModel.class);
    mEvent = getApplicationScopeViewModel(SharedViewModel.class);

    ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    binding.setClick(new ClickProxy());

    mEvent.getMoment().observe(this, moment -> {
      Toast.makeText(this, moment.getContent(), Toast.LENGTH_SHORT).show();
    });

    mEvent.getTestDelayMsg().observe(this, s -> {
      if (!TextUtils.isEmpty(s)) {
        showLongToast(s);
      }
    });
  }

  public class ClickProxy {

    public void toSecondActivity() {
      startActivity(new Intent(MainActivity.this, EditorActivity.class));
    }

    public void toMultiObserverTest() {
      startActivity(new Intent(MainActivity.this, MultiPageActivity.class));
    }
  }
}
