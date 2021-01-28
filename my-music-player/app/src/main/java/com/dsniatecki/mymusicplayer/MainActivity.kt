package com.dsniatecki.mymusicplayer

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.CompletableFuture

@SuppressLint("HandlerLeak", "SetTextI18n")
class MainActivity : AppCompatActivity() {

    private val SONGS = listOf(
        Song(
            "Californication",
            "Red Hot Chili Peppers",
            R.drawable.californication,
            R.raw.californication
        ),
        Song("Faint", "Linkin Park", R.drawable.faint, R.raw.faint),
        Song("Numb", "Linkin Park", R.drawable.numb, R.raw.numb),
        Song("In the end", "Linkin Park", R.drawable.intheend, R.raw.intheend),
        Song("Zenit", "RafCamora", R.drawable.rafcamora, R.raw.rafcamora)
    )

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handler: Handler
    private var currentSongIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentSong = SONGS[currentSongIndex]
        songName.text = currentSong.name
        authorName.text = currentSong.author
        songCover.setImageResource(currentSong.imageId)
        mediaPlayer = MediaPlayer.create(this, currentSong.sourceId)
        songTimeBar.max = mediaPlayer.duration
        songTimeBar.setOnSeekBarChangeListener(createListener())
        mediaPlayer.isLooping = true
        handler = object : Handler() {
            override fun handleMessage(msg: Message) {
                songTimeBar.progress = msg.arg1
                elapsedTime.text = getMusicTime(msg.arg1)
                remainingTime.text = "-${getMusicTime(mediaPlayer.duration - msg.arg1)}"
            }
        }
        startHandlerMessenger()
    }

    private fun startHandlerMessenger() {
        CompletableFuture.runAsync {
            while (true) {
                try {
                    val handlerMessage = Message()
                    handlerMessage.arg1 = mediaPlayer.currentPosition
                    handler.sendMessage(handlerMessage)
                    Thread.sleep(250)
                } catch (e: InterruptedException) {
                }
            }
        }
    }

    private fun createListener(): SeekBar.OnSeekBarChangeListener {
        return object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        }
    }

    fun getMusicTime(time: Int): String {
        val millis = time / 1000
        val minute = millis / 60
        val second = millis % 60
        return if (second < 10) "$minute:0$second" else "$minute:$second"
    }


    fun moveBack(view: View) {
        mediaPlayer.seekTo(mediaPlayer.currentPosition - 10 * 1000)

    }

    fun moveForward(view: View) {
        mediaPlayer.seekTo(mediaPlayer.currentPosition + 10 * 1000)
    }

    fun changeSongToPrevious(view: View) {
        if (currentSongIndex > 0) {
            currentSongIndex--
            switchSong(SONGS[currentSongIndex])
        }
    }

    fun changeSongToNext(view: View) {
        if (currentSongIndex < (SONGS.size - 1)) {
            currentSongIndex++
            switchSong(SONGS[currentSongIndex])
        }

    }

    fun controlMusic(view: View) {
        if (mediaPlayer.isPlaying) {
            startButton.setBackgroundResource(R.drawable.start)
            mediaPlayer.pause()
        } else {
            startButton.setBackgroundResource(R.drawable.stop)
            mediaPlayer.start()
        }
    }

    private fun switchSong(song: Song) {
        val wasPlaying = mediaPlayer.isPlaying;
        songName.text = song.name
        authorName.text = song.author
        songCover.setImageResource(song.imageId)
        mediaPlayer.reset()
        mediaPlayer.setDataSource(this, loadSong(song))
        mediaPlayer.prepare()
        if (wasPlaying) {
            mediaPlayer.start()
        }
        songTimeBar.max = mediaPlayer.duration
    }

    private fun loadSong(song: Song): Uri =
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(this.packageName)
            .appendPath(java.lang.String.valueOf(song.sourceId))
            .build()


}