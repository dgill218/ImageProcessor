# ImageProcessor
This is an image processor that supports multiple operations on a ppm file. The user can type in commands as command line arguments in order to to things such as brighten, darken, greyscale, or flip an image. More specifics on the syntax of the arguments is at the bottom.
# Class overviews
# Model
**-- ITextImageProcessingModel**   
This is an interface to represent an image processing model that does not need to take in image id's in order to keep track of images. More suited for the text version of the processor since it doesn't have to keep track of the images that are being hidden/shown.
**-- IImageProcessorInstance**.  
Interface to represent the model of an image program that can edit multiple images. Model can hold exactly one multi-image at a time, and can add, remove, or replace images. Each layer is represented with a string name and an image interface image. Images can also be marked as either invisible or visible. Useful in a GUI images editor becasue it allows the user to switch between images and hide/show them in the GUI.
**-- ImageProcessingModel**     
Class to represent an image processing model that applies transformations to a list of pixels. This version of the model doesn't need to worry about having multiple images, where some can be hidden and some are shown, so it doesn't have to keep track of the image id's as a field and which images area hidden/shown. Used in the text version of the program, where we don't have to hide images since the user doesn't have to see the images.   
**-- ImageProcessorTextInstance**   
Represents an image processing instance where multiple images are loaded and manipulated. Keeps track of the images that are being manipulated in the processor. Used in the text based version of the program since we don;t have to keep track of the images that are hidden/shown.
**-- MultipleImageProcessorInstance**
Class representing the implementation of an image processing model for when we want to load multiple images at once, and hide/show them in something like a GUI. One model contains only one multi layer image, and can have as many layers as it wants. Layers are given names, and the names are then assigned to images in the delegate. The ids of invisible layers are stored in another list.
## Images
**-- Color**.  
The color interface represents either red, green, or blue, and contains methods to retrieve these colors from whatever pixel they are in.   
**-- ColorImpl**   
The ColorImpl class implements the Color interface, and it overrides the equal and hashcode methods so that we can check for equality between two colors.  
**-- Posn**
Represents a (x,y) coordinate  
**-- IPixel**
The Pixel interface represents a pixel of an image, and contains methods that can retreive the RGB color of the pixel and the posn of the pixel.  
**-- PixelImpl**
Implements the Pixel interface, and in addition to getting the posn and color of a pixel, equals and hashcode have been overridden in order to be able to compare pixels.   
**-- PixelImage**.   
PixelImage is an interface that represens an Image that is made up of pixels. The singular method in this interface gets a 2D list of the pixels that are contained in the image.  
**-- Image**.  
This class implemenets the PixelImage interface, and it takes a deep copy of the pixels it is given and sets it to the pixels field.  
**-- HighestColor**  
A class that has one method which finds the highest RGB component value in the image.
## Transformations
**-- Transformation** An interface that is implemented by all of the transformation classes. Contains a single method called transform() that executes a transformation on an image.
### Color Transformations.
**-- AbstractMatrix transformation**   
An abstract class that allows for matrix transformations for processes such as greyscaling and sepia transformations.
**-- AbstractColorTransformation**  
-- This is an abstract class that represents the many transformations we can do on the color of an image/pixel. Includes things like greyscaling an image, visualizing the red, green, and blue components of an image, or visualizing the intensity of an image.  
**-- RedTransformation**.    
-- Represents a greyscale transformation that visualizes the red component of an image. Sets each pixel's RGB value in the image to the Red component's value.   
**-- BlueTransformation**.     
-- Represents a greyscale transformation that visualizes the green component of an image. Sets each pixel's RGB value in the image to the green component's value.
**--GreenTransformation**.    
-- Represents a greyscale transformation that visualizes the blue component of an image. Sets each pixel's RGB value in the image to the blue component's value.   
**-- IntensityTransformation**    
-- A class that represents an intensity transformation, which just sets each pixel to the average of the three components for that pixel.
**-- ValueTransformation**   
-- This is a class that represents a value transformation, which sets each rgb value to the maximum value of the three components for each pixel.  
**-- GreyscaleTransformation**   
-- This is a class that represents a greyscale transformation, which sets each rgb value to the weighted sum: 0.2126r + 0.7152g + 0.0722b. The result of an image that undergoes this transformation is a greyscaled image.
### Intensity Transformations
**-- AbstractIntensityTransformation**   
-- Represents some transformation on the brightness/darkness of an image, and implements the Transformation interface.   
**-- BrightnessTransformation**.    
-- Represents a transformation on an image that increases the brightness of an image by a certain value, which is field of the class.  
**-- DarknessTransformation**.   
-- Represents a transformation on an image that decreases the brightness of an image by a certain value, which is field of the class.
### Translate Transformations.
**-- HorizontalFlip**   
Represents a reflection of the image along a vertical line in the middle of the image. Results in an image that looks reflected over the middle column.  
**-- VerticalFlip**.
Represents a reflection of the image along a horizontal line in the middle of the image. Results in an upside-down image of the original.
### Filters.
**-- AbstractImageFilter**.   
Represents a filter such as blurring or sharpening an image, and the methods in this class make use of a kernel to apply the sharpening and blurring transformations.    
**-- Sharpen**   
A class that represents a sharpen transformation on an image. This transformation can be applied many times to repeadedly sharpen an image.    
**-- Blur**  
A class that represents a blurring transformation on an image. This transformation can be applied many times to repeadedly blur an image.
## Graph
**-- Histogram**
Class that contains methods that populate the frequencies of the RGB values so that a graph can be made.
**-- Line**   
A line class that represents a simple straight line. 
**-- Position2D**   
Class to represent a position on a 2D graph
# View
**-- ImageProcessorView**.  
-- This is our interface for the view for our Image Processor. For this version, the view is just a simple text view.   
**-- ImageProcessorGUIView**
-- Interface to represent the view for an image processing programs that uses an interactive GUI to receive user inputs. GUI exposes all features of the program to the user, which can be activated using these buttons.
**-- IViewListener**.   
-- Interface to represent a listener to the GUI view for an image processing program that can reactto high level events received by the view.    
**-- ImageProcessorTextView**.    
-- This class implements the ImageProcessorView interface and contains a single method that transmits output to the console. Used in the text based version of the program.   
**-- ImageProcessorGUIViewImpl**.     
-- Class to represent the implementation og a GUI view for and image processing program. The view uses a listener to handle the high level events that are received from hitting buttons. Any errors are represented as pop up boxes that the user sees.
**-- GraphPanel**   
Main image histogram class that renders the histogram panel on the GUI panel. 
# Controller
**-- ImageController**.  
--  This is the main controller interface of the Image Processor. Contains a single method called start(), which begins the Image Processing application and accepts user input.  
**-- ImageControllerImpl**.  
-- The main implemtnation of the controller, implements imageController. This class allows us to proccess all of the user imputs and delegates to the model depening on what text commands are inputted.
**-- ImageProcessorGUIController**.  
Class representing a controller for an image processing program that uses a GUI view. This controller supports the multi layer model and uses the ImageProcessorGUIView in order to display the required information. This also implements the IViewListener, allowing the controller to receive high level events from the view and act on them.
### FileReader.
**-- Package that contains classes and interfaces related to reading in/loading an image.   
**-- IFileReader**.   
Interface that contains a single method which reads a file with the given filename.  
**-- ImageIOFileReader**.   
This is a class that implements the FileReader interface, and it handles the reading of jpeg and png files. Can be extened to support the reading of any of the file types supported by the built in ImageIO interface.   
**-- PPMFileReader**.  
This class also implements the FileReader interface, and it handles the reading of ppm image files.
**-- ImageUtil**  
Contains a method to get a 2d list of the pixels in the given image.
### FileWriter
**-- Package that contains classes and interfaces relateed to writing/saving an image.
**-- IImageFileWriter**.  
Interface to represent a function object that writes an image file type from the ImageInterface data.    
**-- AbstractImageIOWriter**.  Abstract class representing a file writer that uses file types from the ImageIO library. Holds the file type to designate what kind of file to write.   
**-- JPEGImageIOWriter**.  
Function object to facilitate the writing of jpeg images.
**-- PNGImageIOWriter**
Function object to facilitate the writing of png files.
**-- PPMFileWriter**.   
Class to write PPM ASCII image files from ImageInterface data.

## Changes from last version:
In our latest version of this image processor, we now support an option to interact with the prorgam through a GUI, where the user can load, save, and manipualte an image from. There is also an image histogram that has been added, which displays the distribution of color or intensities in the image that is being operated on in the GUI. This allows the user to hget a visual depiction of the image and its respective compoenents. There are 4 histograms that are being displayed, with each one representing either the red, green, blue, or intensity components of the image.


## How to use this program:
Prorgram can be used with either a GUI or a simple text based interaction throught the terminal.    
To open the GUI version, navigate to where the jar file is thriugh the terminal, then type: java -jar Program.jar.  
To run the prorgam with a premade script file, type: java -jar Program.jar -file path-of-script-file.    
To use the text based interactive version, type: java -jar Program.jar -text.

load boston.jpeg and call it 'boston' --> load res/boston.jpeg boston

brighten boston by adding 10 --> brighten 10 boston boston-brighter

flip boston vertically --> vertical boston boston-vertical

flip the vertically flipped boston horizontally --> horizontal boston-vertical boston-vertical-horizontal

create a greyscale using only the value component, as an image boston-greyscale  --> value-component boston boston-greyscale
Alternative: value blocks blocks-greyscale

save boston-brighter as bright --> save res/bright.ppm boston-brighter

save boston-vertical as boston-vert --> save res/boston-vert.ppm boston-vertical

blur boston as blurred --> blur boston blurred

If you want to use .ppm or .png files, just use the appropriate extenstion when loading and saving.

overwrite boston with another file --> load res/upper.ppm boston

Image sources:
https://filesamples.com/formats/ppm
https://www.bostonmagazine.com/arts-entertainment/best-views-around-boston/





