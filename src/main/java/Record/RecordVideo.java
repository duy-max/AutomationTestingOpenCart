package Record;
import org.monte.media.Format;
import static org.monte.media.FormatKeys.*;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import static org.monte.media.VideoFormatKeys.*;

import java.awt.*;
import java.io.File;
public class RecordVideo {
    private static ScreenRecorder screenRecorder;

    public static void startRecording(String videoName) throws Exception {
        File videoDir = new File("./recordings"); // Thư mục lưu video
        if (!videoDir.exists()) {
            videoDir.mkdirs();
        }

        screenRecorder = new ScreenRecorder(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration(),
                new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()),
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
                null,
                videoDir
        );

        screenRecorder.start();
        System.out.println("Recording started...");
    }

    public static void stopRecording() throws Exception {
        screenRecorder.stop();
        System.out.println("Recording stopped...");
    }
}
