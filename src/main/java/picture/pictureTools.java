package picture;

import java.io.IOException;

import net.coobird.thumbnailator.Thumbnails;

public class pictureTools {

	public static void main(String[] args) {
		try {
			Thumbnails.of("D:/Media/图片/d30f905agy1ffua5n9nzxj21kw0w0e88.jpg").scale(1f).outputQuality(0.3f)
					.toFile("D:/Media/图片/d30f905agy1ffua5n9nzxj21kw0w0e88_compress.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
