package com.fecap.ccp.seekbar_v2;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private SeekBar videoSeekBar;
    private TextView textTempo;
    private Handler handler = new Handler(); // Manipulador para o vídeo


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Vincular os objetos com os IDs:
        videoSeekBar = findViewById(R.id.videoSeekBar);
        videoView = findViewById(R.id.videoView);
        textTempo = findViewById(R.id.textTempo);

        // Configurar o caminho do video e carregar no VideoView
        Uri videoUri = Uri.parse("android.resource://" +
                getPackageName() + "/" + R.raw.videod); // Substituir pelo vídeo na pasta raw
        videoView.setVideoURI(videoUri);

        // Listener para saber quando o vídeo está preparado para reproduzir:
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoSeekBar.setMax(videoView.getDuration());

                // Caso adicionar, o vídeo vai começar automaticamente
                // Inícia uma thread para atualizar o SeekBar conforme o video play:
                handler.post(atualizarSeekBar);

                // Inícia o vídeo automaticamente
                videoView.start();
            }
        });

        // Listener para controlar o vídeo pelo SeekBar

        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b) {
                    // Permitir que o usuário pule para a parte do vídeo
                    videoView.seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Metodo para atualizar o tempo do vídeo e posição do SeekBar
    private Runnable atualizarSeekBar = new Runnable() {
        @Override
        public void run() {
            if(videoView.isPlaying()) {
                videoSeekBar.setProgress(videoView.getCurrentPosition());
                atualizarTextTempo();
            }

            handler.postDelayed(this, 1000); // Atualiza a cada 1s
        }
    };

    // Metodo para formatar o tempo e atualizar o rótulo de tempo do vídeo

    private void atualizarTextTempo() {
        int tempoAtual = videoView.getCurrentPosition();
        int tempoTotal = videoView.getDuration();

        String tempoFormatado = formataTempo(tempoAtual) + " / " +
                formataTempo(tempoTotal);
        textTempo.setText(tempoFormatado);
    }

    // Metodo para formatar o tempo em mm:ss

    private String formataTempo(int tempo) {
        int min = (tempo / 1000) / 60;
        int sec = (tempo / 1000) % 60;
        return String.format("%02d:%02d", min, sec);
    }

    // Implementação do metodo para btnPlay

    public void startVideo(View view) {
        videoView.start();

        // Manter o handler abaixo caso não deseje que o vídeo inicie com o app
        // handler.post(atualizarSeekBar);
    }

    // Implementação do metodo para btnPause

    public void pauseVideo(View view) {
        videoView.pause();
    }

    // Implementação do metodo para btnStop

    public void stopVideo(View view) {
        videoView.pause();
        videoView.seekTo(0);
        videoSeekBar.setProgress(0);
        atualizarTextTempo();

    }
}