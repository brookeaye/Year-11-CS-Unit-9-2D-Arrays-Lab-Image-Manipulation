package code;

import image.APImage;
import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
       /* APImage photo = new APImage("cyberpunk2077.jpg");
        photo.draw();
        //challenge 1:
        grayScale("cyberpunk2077.jpg");
        //challenge 2:
        blackAndWhite("cyberpunk2077.jpg");
        //challenge 3:
        edgeDetection("cyberpunk2077.jpg", 20);
        //challenge 4:
        reflectImage("cyberpunk2077.jpg");*/
        //challenge 5:
        rotateImage("cyberpunk2077.jpg");
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        APImage photo = new APImage("cyberpunk2077.jpg");
        for (int i = 0; i < photo.getHeight(); i++){
            for (int j = 0; j < photo.getWidth(); j++){
                Pixel pixel = photo.getPixel(j, i);
                int average = getAverageColour(pixel);
                pixel.setRed(average);
                pixel.setGreen(average);
                pixel.setBlue(average);
                photo.setPixel(j, i, pixel);
            }
        }
        photo.draw();
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        int red = pixel.getRed();
        int green = pixel.getGreen();
        int blue = pixel.getBlue();
        double average = (double) (red + green + blue) /3;
        return (int) average;
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        APImage photo = new APImage(pathOfFile);
        for (int i = 0; i < photo.getHeight(); i++){
            for (int j = 0; j < photo.getWidth(); j++){
                Pixel pixel = photo.getPixel(j, i);
                int average = getAverageColour(pixel);
                if (average < 128){
                    pixel.setRed(0);
                    pixel.setGreen(0);
                    pixel.setBlue(0);
                }
                else{
                    pixel.setRed(255);
                    pixel.setGreen(255);
                    pixel.setBlue(255);
                }
            }
        }
        photo.draw();
    }

    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        APImage originalPhoto = new APImage(pathToFile);
        APImage photo = new APImage(originalPhoto.getWidth(), originalPhoto.getHeight());
        for (int x = 1; x < photo.getWidth(); x++){
            for (int y = 0; y < photo.getHeight()-1; y++){
                Pixel pixel = originalPhoto.getPixel(x, y);
                int average = getAverageColour(pixel);
                Pixel leftPixel = originalPhoto.getPixel(x-1, y);
                int leftAverage = getAverageColour(leftPixel);
                Pixel downPixel = originalPhoto.getPixel(x, y+1);
                int downAverage = getAverageColour(downPixel);
                if (Math.abs(leftAverage-average) >= threshold || Math.abs(downAverage - average) >= threshold){
                    photo.getPixel(x, y).setRed(0);
                    photo.getPixel(x,y).setBlue(0);
                    photo.getPixel(x,y).setGreen(0);
                }
                else{
                    photo.getPixel(x, y).setRed(255);
                    photo.getPixel(x,y).setBlue(255);
                    photo.getPixel(x,y).setGreen(255);
                }
            }
        }
        photo.draw();
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        APImage oldPhoto = new APImage(pathToFile);
        APImage photo = new APImage(oldPhoto.getWidth(), oldPhoto.getHeight());
        for (int x = 0; x < oldPhoto.getWidth(); x++){
            for (int y = 0; y < oldPhoto.getHeight(); y++){
                Pixel pixel = new Pixel(oldPhoto.getPixel(x, y).getRed(), oldPhoto.getPixel(x, y).getGreen(), oldPhoto.getPixel(x, y).getBlue());
                photo.setPixel(photo.getWidth()-1-x, y, pixel);
            }
        }
        photo.draw();
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        APImage oldPhoto = new APImage(pathToFile);
        APImage photo = new APImage(oldPhoto.getHeight(), oldPhoto.getWidth());
        for (int x = 0; x < oldPhoto.getWidth()-1; x++){
            for (int y = 0; y < oldPhoto.getHeight()-1; y++){
                photo.setPixel(884-y, x, oldPhoto.getPixel(x, y));
            }
        }
        photo.draw();
    }

}
