# Kapoll a Voting Application

This project was done as a semester project in the cors DAT250 at Western Norway University of Applied Sciences.
From here we aspect you to have Java, Maven, Node.js, npm and Docker preinstalled. 
The walk-through assume that you use a IDEA, that support some type of automatic build, 
the command for this is not given her

## How to Run the Application

Open up a terminal and move to where you want to store the project.

```
git clone https://github.com/Tobbelobby/Kapoll_main.git
```

We have to install some packages for the front-end.

```
cd Kapoll_main/Kapoll/src/main/frontend/kapoller/ |  npm init 
```

Start by running the docker container either bye using the IDEA or with the command:

```
docker run hello-world &
```

We are now ready to start the back-end, in the IDEA run KapollApplication and MongodbApplication.
The last thing is to start the front-end, move to /Kapoll_main/Kapoll/src/main/frontend/kapoller, and run this command:

```
npm start
```







