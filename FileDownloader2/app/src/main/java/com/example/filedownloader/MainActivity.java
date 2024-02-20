package com.example.filedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.RatingBar;
import android.widget.SeekBar;

import com.example.filedownloader.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        RatingBar.OnRatingBarChangeListener, View.OnClickListener
{
    ActivityMainBinding binding;
    int progressVal = 0;
    Thread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //  intializing seekBar
        binding.skbarFileSize.setMax(50);
        binding.txtSizeSelected.setText(String.valueOf(binding.skbarFileSize.getMin()));

        // initializing RatingBar
        binding.rtApp.setNumStars(5);
        binding.rtApp.setRating(0);
        binding.txtSizeSelected.setText(String.valueOf(binding.rtApp.getRating()));

        // initializing progressBar
        binding.prgFileDownload.setProgress(0);
        // setting listeners
        setListeners();
    }

    private void setListeners () {
        binding.skbarFileSize.setOnSeekBarChangeListener(this);
        binding.rtApp.setOnRatingBarChangeListener(this);
        binding.btnDownload.setOnClickListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        binding.txtSizeSelected.setText((seekBar.getProgress())+"GB");
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        binding.txtRatingSelected.setText(String.valueOf(rating));
    }

    @Override
    public void onClick(View v) {
        binding.prgFileDownload.setMax(binding.skbarFileSize.getProgress());
        initDownload();
    }

    private void  initDownload () {
        String uri = "https://drive.google.com/uc?id=1jGbXnFsGmIh517aKKbOlvXmohxURJnqE&export=download";
        downloadFile(getApplicationContext(),"welcome",".pdf", uri.trim());
    }

    private void downloadFile (Context applicationContext,String fileName, String extension, String url) {
        DownloadManager downloadManager = (DownloadManager) applicationContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(applicationContext, Environment.DIRECTORY_DOWNLOADS.toString(), fileName + extension);
        assert downloadManager != null;
        downloadManager.enqueue(request);
        loadProgressBar(progressVal);
        Snackbar.make(binding.lytDownload,"Downloading...",Snackbar.LENGTH_LONG)
                .show();
    }

    private void loadProgressBar(int progressValue) {
        binding.prgFileDownload.setProgress(progressValue);
        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    if (progressValue < binding.skbarFileSize.getProgress()) {
                        loadProgressBar(progressValue + 10);
                    } else {
                        thread1.interrupt();
                    }
                } catch (InterruptedException e) {

                }
            }
        });
        thread1.start();
    }
}
