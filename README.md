# HW5
This is an app that allows a user to take a picture from the camera and paint over the picture and add icons to make the picture more stylish. 

---

## HW5

#### REQUIRED (10pts)
- [x] (20pts) App can take a picture and show it in the second activity
- [x] (20pts) App enables multi-touch drawing, with the selected color
- [x] (15pts) App supports adding different icons by double-tapping and long-pressing gesture
- [x] (15pts) App supports the undoing the line-drawing and clearing the canvas
- [x] (10pts) After finishing one picture, the user can take another picture and edit it again
- [x] (10pts) Bug free
- [x] (10pts) Submission follows the guidelines, and README file is complete

### App Walkthough GIF

<img src="<blockquote class="imgur-embed-pub" lang="en" data-id="a/Hkt07Qo"  ><a href="//imgur.com/a/Hkt07Qo">HW5</a></blockquote>

### Notes
When building this app, there didn't come much in the way of challenges for building the finished product. However, there was one major challenge and that came with allowing the 
user to draw multiple paths without the previous path being deleted. The way I was able to solve this was by using an ArrayList to hold all of the finished paths when the user
lifts their finger, that way every path would be able to be drawn. 
