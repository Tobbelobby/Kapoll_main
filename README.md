# Kapoll a Voting Application

This project was done as a semester project in the cors DAT250 at Western Norway University of Applied Sciences.
From here we expect you to have Java, Maven, Node.js, npm and Docker preinstalled. 
The walk-through assume that you use a IDEA, that support some type of automatic build, 
the command for this is not given her

## How to Run the Application

Open up a terminal and move to where you want to store the project.

```
git clone https://github.com/Tobbelobby/Kapoll_main.git
```

We have to install some packages for the front-end.

```
cd Kapoll_main/Kapoll/src/main/frontend/kapoller/
```
```
npm install
```
Start by running the docker container either bye using the IDEA or with by the CLI. 
We are now ready to start the back-end, in the IDEA run KapollApplication and MongodbApplication.
The last thing is to start the front-end, move to /Kapoll_main/Kapoll/src/main/frontend/kapoller, and run this command:

```
npm start
```


Start with trying to log in with google. If successful, you unfortunately have to log out and then log back in
in order to add a poll, as explained in section 4.8 and 5 in the report. If the "login" tab in the naviation bar 
is there, and not "my profile", that means that the system has not actually obtained your user id, which you need
in order to make a poll. If you cannot see "my profile", you will not be able to see the polls you add. If you cannot
see it, try refreshing the page and/or log out and in again. We hoped this would be resolved by the time of submission,
but sadly we did not figure it out. 

To vote on a poll, click on any poll of yours and press copy link. Paste this link in your browser, and from here you
can vote, reset and send your results.




