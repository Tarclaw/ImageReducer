package lets.doit.reducer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageReducer {

    private String inputImagePath = "/home/tarclaw/Pictures/painting1";
    private String outputImagePath = "/home/tarclaw/Pictures/result/";
    private float reduceCoefficient = 0.5F;

    private List<String> extensions = new ArrayList<>();

    public ImageReducer() {
        init();
    }

    public void reduceImageSize() throws IOException {
        File directory = new File(inputImagePath);
        File[] files = directory.listFiles((file, name) -> {
            for (final String extension : extensions) {
                if (name.endsWith("." + extension)) {
                    return true;
                }
            }
            return false;
        });

        if (directory.isDirectory() && files != null && files.length > 0) {
            for (File image : files) {
                BufferedImage inputImage = ImageIO.read(image);
                float percent = reduceCoefficient / 2364875 * image.length();
                int scaledWidth = (int) (inputImage.getWidth() * percent);
                int scaledHeight = (int) (inputImage.getHeight() * percent);
                BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

                Graphics2D g2d = outputImage.createGraphics();
                g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
                g2d.dispose();

                String extension = getExtension(image.getName());
                File outputFile = new File(outputImagePath + image.getName());
                ImageIO.write(outputImage, extension, outputFile);
            }
        }
        if (files != null && files.length == 0) throw new IOException("Array with images empty. Add Extensions!");
    }

    private String getExtension(String name) throws IOException {
        for (String extension : extensions) {
            if (name.endsWith("." + extension)) {
                return extension;
            }
        }
        throw new IOException("ADD EXTENSION");
    }

    private void init() {
        this.extensions.add("jpg");
        this.extensions.add("jpeg");
        this.extensions.add("gif");
        this.extensions.add("png");
        this.extensions.add("bmp");
    }

    public String getInputImagePath() {
        return inputImagePath;
    }

    public void setInputImagePath(String inputImagePath) {
        this.inputImagePath = inputImagePath;
    }

    public String getOutputImagePath() {
        return outputImagePath;
    }

    public void setOutputImagePath(String outputImagePath) {
        this.outputImagePath = outputImagePath;
    }

    public float getReduceCoefficient() {
        return reduceCoefficient;
    }

    public void setReduceCoefficient(float reduceCoefficient) {
        this.reduceCoefficient = reduceCoefficient;
    }

    public void addExtension(String extension) {
        this.extensions.add(extension);
    }

    public List<String> getExtensions() {
        return extensions;
    }
}
