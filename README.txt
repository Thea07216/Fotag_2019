Please do not change the directory structure.

When app boot, it will have a toolbar and five rating star.

ToolBar

Load: First button on the top of the screen, After clicking the button, the app will clear the previous rating, and then load 10 pictures from the provided website, loading is a little bit slow, please wait until load finished and then do other operations.

Clear: Second button on the top of the screen. After clicking the button, the app will clear all images and current global filter rating.

FiveStar RatingBar: By clicking any star (from 1-5), the app will filter the images according to images ratings(global rating >= image ratings). To set the filter to "any", first you need to select a star and drag it to left, and then release, it will set the global filter to "any". (Same for individual image ratings)


Enlarge:

By touching the image in the grid will enlarge the image to full-screen. And by touching it a second time will dismiss this window, and return to the image grid.

Layout:

Support two layouts, by default it is vertical orientation(1*10), once the user change the orientation, it will adjust to horizontal layout(2*5)

Note: Please make sure your simulator is connected to WIFI. If not, load won't work.

Note:
For use of AsyncTask, Gridview idea, use of Adapter and onConfigurationChanged(for two layout), I learned it from Google, Stackoverflow and Youtube video.

